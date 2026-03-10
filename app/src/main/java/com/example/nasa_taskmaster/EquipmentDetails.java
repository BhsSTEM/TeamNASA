package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EquipmentDetails extends AppCompatActivity {

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

        TextView equipmentDetailsEquipmentName = findViewById(R.id.equipmentDetailsEquipmentName);
        TextView equipmentDetailsEquipmentStatus = findViewById(R.id.equipmentDetailsEquipmentStatus);
        TextView equipmentDetailsEquipmentYear = findViewById(R.id.equipmentDetailsEquipmentYear);
        TextView equipmentDetailsEquipmentDescription = findViewById(R.id.equipmentDetailsEquipmentDescription);

        if(equipmentName != null) equipmentDetailsEquipmentName.setText(equipmentName);

        if(equipmentStatus != null) equipmentDetailsEquipmentStatus.setText(equipmentStatus);

        if(equipmentYear != 0) equipmentDetailsEquipmentYear.setText(String.valueOf(equipmentYear));

        if(equipmentDescription != null)  equipmentDetailsEquipmentDescription.setText(equipmentDescription);

    }
}