package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddNewEquipment extends AppCompatActivity {

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

        findViewById(R.id.addNewEquipmentButton).setOnClickListener(v -> {

            String equipmentName = null;
            if (!((com.google.android.material.textfield.TextInputEditText) findViewById(R.id.equipmentNameInputBox)).getText().toString().isEmpty()) {
                equipmentName = ((com.google.android.material.textfield.TextInputEditText) findViewById(R.id.equipmentNameInputBox)).getText().toString();
            }

            String equipmentYear = null;
            if (!((com.google.android.material.textfield.TextInputEditText) findViewById(R.id.equipmentYearInputBoxtextInputLayout)).getText().toString().isEmpty()) {
                equipmentYear = ((com.google.android.material.textfield.TextInputEditText) findViewById(R.id.equipmentYearInputBoxtextInputLayout)).getText().toString();
            }

            Equipment equipment = new Equipment(equipmentName, Integer.parseInt(equipmentYear));

            EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();
            ArrayList<Equipment> equipmentList = equipmentMainActivity.getEquipmentList();
            equipmentList.add(equipment);
            equipmentMainActivity.setEquipmentList(equipmentList);

            Intent intent = new Intent(this, EquipmentMainActivity.class);
            startActivity(intent);
        });
    }
}