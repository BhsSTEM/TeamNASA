package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EquipmentDetails extends AppCompatActivity {

    private ArrayList<Locations> locations;
    private Locations selectedLocation = null;
    private String location = "No Location";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_equipment_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String equipmentName = intent.getStringExtra("equipmentName");
        String equipmentStatus = intent.getStringExtra("equipmentStatus");
        int equipmentYear = intent.getIntExtra("equipmentYear", 0);
        String equipmentDescription = intent.getStringExtra("equipmentDescription");
        String location = intent.getStringExtra("location");
        Log.d("Equipment Details", "Location:" + location);
        this.location = location;

        TextView equipmentDetailsEquipmentName = findViewById(R.id.equipmentNameView);
        TextView equipmentDetailsEquipmentStatus = findViewById(R.id.equipmentStatusView);
        TextView equipmentDetailsEquipmentYear = findViewById(R.id.equipmentYearView);
        TextView equipmentDetailsEquipmentDescription = findViewById(R.id.equipmentDetailsEquipmentDescriptionView);
        TextView equipmentDetailsEquipmentLocation = findViewById(R.id.equipmentDetailsEquipmentLocationView);

        if(equipmentName != null) equipmentDetailsEquipmentName.setText(equipmentName);

        if(equipmentStatus != null) equipmentDetailsEquipmentStatus.setText(equipmentStatus);

        if(equipmentYear != 0) equipmentDetailsEquipmentYear.setText(String.valueOf(equipmentYear));

        if(equipmentDescription != null)  equipmentDetailsEquipmentDescription.setText(equipmentDescription);
        
        if(location != null) equipmentDetailsEquipmentLocation.setText(location);

        //CODE TO SHOW LOCATION ON MAP FRAGMENT
        //create fragment with location
        getSupportFragmentManager().beginTransaction().add(R.id.mapFragmentEquipmentDetails, ShowLocationMapFragment.newInstance(location)).commit();

        findViewById(R.id.saveDetailsButton).setOnClickListener(v -> {
            Intent intent1 = new Intent(this, EditEquipmentDetails.class);
            intent1.putExtra("equipmentName", equipmentName);
            intent1.putExtra("equipmentYear", equipmentYear);
            intent1.putExtra("location", location);
            startActivity(intent1);
        });

    }
}
