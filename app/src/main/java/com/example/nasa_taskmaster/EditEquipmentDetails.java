package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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


       /* locationList = Map.getLocations();
        locationNames = new String[locationList.size()+1];
        for (int i = 0; i < locationList.size(); i++) {
            locationNames[i] = locationList.get(i).getName();
        }
        locationNames[locationList.size()] = "Add New Location";

        //location spinner - add code to add new location
        // code for adding spinner for locations
        Spinner spinner = findViewById(R.id.addLocationSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        String equipmentName = intent.getStringExtra("equipmentName");
        int equipmentYear = intent.getIntExtra("equipmentYear", 0);
        String location = intent.getStringExtra("location");
        Log.d("Equipment Details", "Location:" + location);

        //find what equipment is being edidted
        EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();
        equipmentList = equipmentMainActivity.getEquipmentList();

        for(Equipment equipment : equipmentList) {
            if(equipment.getName().equals(equipmentName) && equipment.getYear() == equipmentYear)
            {
                EquipmentEditied = equipment;
                break;
            }
        }

        //set fields to have the equipment values
        TextView equipmentDetailsEquipmentName = findViewById(R.id.equipmentNameInputBoxBox);
        equipmentDetailsEquipmentName.setText(EquipmentEditied.getName()); //set Name

        TextView equipmentDetailsEquipmentYear = findViewById(R.id.equipmentYearInputBoxBox);
        equipmentDetailsEquipmentYear.setText(EquipmentEditied.getYear()); //set Year

        TextView equipmentDetailsEquipmentStatus = findViewById(R.id.equipmentStatusInputBoxBox);
        equipmentDetailsEquipmentStatus.setText(EquipmentEditied.getStatus()); //set Status

        //ADD A DESCRIP
        //TextView equipmentDetailsEquipmentDescription = findViewById(R.id.equipmentDescriptionInputBoxBox);

        //change the equipment

        findViewById(R.id.saveDetailsButton).setOnClickListener(v -> {
            Intent intent1 = new Intent(this, EquipmentMainActivity.class);
            startActivity(intent1);
        }); */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedLocation = locationList.get(position);
        Toast.makeText(getApplicationContext(), locationNames[position], Toast.LENGTH_SHORT).show();

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