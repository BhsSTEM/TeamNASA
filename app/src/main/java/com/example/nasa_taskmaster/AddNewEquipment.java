package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddNewEquipment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] locationNames = {};
    private ArrayList<Locations> locationList;
    private Locations selectedLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_equipment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Use static method to get locations
        locationList = Map.getLocations();
        if(locationList.isEmpty())
        {
            //so there is no null errors if the user currently has no locations
            locationNames = new String[1+1];
            locationNames[0] = "No Location Selected";
            locationNames[1] = "Add New Location";
        }
        else //if(!(locationList.isEmpty()))
        {
            locationNames = new String[locationList.size()+1];
            for (int i = 0; i < locationList.size(); i++) {
                locationNames[i] = locationList.get(i).getName();
            }
            locationNames[locationList.size()] = "Add New Location";
        }


        // code for adding spinner for locations
        Spinner spinner = findViewById(R.id.addLocationSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //get all info once button is pressed
        findViewById(R.id.addNewEquipmentButton).setOnClickListener(v -> {

            String equipmentName = "";
            TextInputEditText equipmentNameInput = findViewById(R.id.equipmentNameInputBoxBox);
            if (equipmentNameInput.getText() != null && !equipmentNameInput.getText().toString().isEmpty()) {
                equipmentName = equipmentNameInput.getText().toString().trim();
            }

            String equipmentYearStr = "";
            TextInputEditText equipmentYearInput = findViewById(R.id.equipmentYearInputBoxBox);
            if (equipmentYearInput.getText() != null && !equipmentYearInput.getText().toString().isEmpty()) {
                equipmentYearStr = equipmentYearInput.getText().toString().trim();
            }

            int equipmentYear = 0;
            try {
                equipmentYear = Integer.parseInt(equipmentYearStr);
            } catch (NumberFormatException e) {
                // Handle invalid year input if necessary
            }

            String status = "";
            TextInputEditText statusInput = findViewById(R.id.equipmentStatusInputBoxBox);
            if (statusInput.getText() != null && !statusInput.getText().toString().isEmpty()) {
                status = statusInput.getText().toString().trim();
            }

            //if they enter nothing - tell them to enter stuff and dont make object
            Log.d("Spinner", "Equipmemt Name is empty" + equipmentName.isEmpty());
            Log.d("Spinner", "Equipmemt Year is empty" + equipmentYearStr.isEmpty());
            Log.d("Spinner", "Equipmemt Status is empty" + status.isEmpty());
            Log.d("Spinner", "Selected position: " + selectedLocation);
            if(equipmentName.isEmpty() && equipmentYearStr.isEmpty() && status.isEmpty() && selectedLocation == null)
            {
                Toast.makeText(this, "Please enter a field", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create new equipment object using the selectedLocation from the spinner if they entered something
            Equipment equipment = new Equipment(equipmentName, equipmentYear, status, "No Description", selectedLocation);

            // Replicating your original logic to avoid changing EquipmentMainActivity
            EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();
            ArrayList<Equipment> equipmentList = equipmentMainActivity.getEquipmentList();
            equipmentList.add(equipment);
            equipmentMainActivity.setEquipmentList(equipmentList);

            Intent intent = new Intent(this, EquipmentMainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Spinner", "Selected position: " + position);

        //Toast.makeText(getApplicationContext(), locationNames[position], Toast.LENGTH_SHORT).show();

        if(!locationNames[position].equals("No Location Selected") && !locationNames[position].equals("Add New Location"))
        {
            selectedLocation = locationList.get(position);
        }

        if(locationNames[position].equals("Add New Location")){
            Intent intent = new Intent(this, Map.class);
            startActivity(intent);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedLocation = null;
    }
}