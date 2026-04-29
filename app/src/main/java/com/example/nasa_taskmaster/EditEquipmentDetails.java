package com.example.nasa_taskmaster;

import static java.lang.Integer.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditEquipmentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String[] locationNames = {};
    private ArrayList<Locations> locationList;
    private Locations selectedLocation = null;
    private Equipment EquipmentEditied = null;
    private ArrayList<Equipment> equipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_equipment_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Location Spinner Code

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

        Spinner spinner = findViewById(R.id.addLocationSpinner2);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        String equipmentName = intent.getStringExtra("equipmentName");
        int equipmentYear = intent.getIntExtra("equipmentYear", 0);
        String location = intent.getStringExtra("location");

        //find what equipment is being edited
        EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();
        equipmentList = equipmentMainActivity.getEquipmentList();

        for(Equipment equipment : equipmentList) {
           // Log.d("EquipmentDetails", "Equipment: " + equipment.getName() + " == " + equipmentName + "? : " + (equipment.getName().equals(equipmentName)));
            if(equipment.getName().equals(equipmentName) && equipment.getYear() == equipmentYear)
            {
                EquipmentEditied = equipment;
                //Log.d("Equipment Details", "Equipment found: " + equipment.getName() + " " + equipment.getYear());
                break;
            }
        }

        //set fields to have the equipment values
        TextView equipmentDetailsEquipmentName = findViewById(R.id.taskName);
        equipmentDetailsEquipmentName.setText(EquipmentEditied.getName()); //set Name

        TextView equipmentDetailsEquipmentYear = findViewById(R.id.equipmentYearInputBoxBox);
        equipmentDetailsEquipmentYear.setText(String.valueOf(EquipmentEditied.getYear())); //set Year

        TextView equipmentDetailsEquipmentStatus = findViewById(R.id.taskDescript);
        equipmentDetailsEquipmentStatus.setText(EquipmentEditied.getStatus()); //set Status

        TextView equipmentDetailsEquipmentDescription = findViewById(R.id.equipmentDescriptionInputBoxBox);
        equipmentDetailsEquipmentDescription.setText(EquipmentEditied.getDescription()); //set Description

        //CODE TO SHOW LOCATION ON MAP FRAGMENT
        //create fragment with location
        getSupportFragmentManager().beginTransaction().add(R.id.mapFragmentEquipmentDetails, ShowLocationMapFragment.newInstance(location)).commit();

        findViewById(R.id.addTaskBtn).setOnClickListener(v -> {

            String equipmentName1 = "";
            TextInputEditText equipmentNameInput = findViewById(R.id.taskName);
            if (equipmentNameInput.getText() != null && !equipmentNameInput.getText().toString().isEmpty()) {
                equipmentName1 = equipmentNameInput.getText().toString().trim();
            }

            String equipmentYearStr = "";
            TextInputEditText equipmentYearInput = findViewById(R.id.equipmentYearInputBoxBox);
            if (equipmentYearInput.getText() != null && !equipmentYearInput.getText().toString().isEmpty()) {
                equipmentYearStr = equipmentYearInput.getText().toString().trim();
            }

            int equipmentYear1 = 0;
            try {
                equipmentYear1 = parseInt(equipmentYearStr);
            } catch (NumberFormatException e) {
                // Handle invalid year input
            }

            String status = "";
            TextInputEditText statusInput = findViewById(R.id.taskDescript);
            if (statusInput.getText() != null && !statusInput.getText().toString().isEmpty()) {
                status = statusInput.getText().toString().trim();
            }

          /*  Intent intent1 = new Intent(this, EquipmentMainActivity.class);
            startActivity(intent1);*/
        });
        
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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