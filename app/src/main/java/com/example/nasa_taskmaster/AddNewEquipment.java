package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

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
            TextInputEditText equipmentNameInput = findViewById(R.id.equipmentNameInputBoxBox);
            if (!(equipmentNameInput).getText().toString().isEmpty()) {
                equipmentName = equipmentNameInput.getText().toString().trim();
                //System.out.println("name worked");
            }

            //ADD CODE FOR INPUT VALIDATION SO USERS CAN ONLY PUT IN NORMAL YEAR
            String equipmentYear = null;
            TextInputEditText equipmentYearInput = findViewById(R.id.equipmentYearInputBoxBox);
            if (!(equipmentYearInput).getText().toString().isEmpty()) {
                equipmentYear = equipmentYearInput.getText().toString().trim();
                //System.out.println("year worked");
            }


            String status = null;
            TextInputEditText statusInput = findViewById(R.id.equipmentStatusInputBoxBox);
            if (!(statusInput).getText().toString().isEmpty()) {
                status = statusInput.getText().toString().trim();
                //System.out.println("status worked");
            }

            Equipment equipment = new Equipment(equipmentName, Integer.parseInt(equipmentYear), status);

            EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();

            ArrayList<Equipment> equipmentList = equipmentMainActivity.getEquipmentList();

            //adding new item
            equipmentList.add(equipment);
            equipmentMainActivity.setEquipmentList(equipmentList);

            Intent intent = new Intent(this, EquipmentMainActivity.class);
            startActivity(intent);
        });
    }
}