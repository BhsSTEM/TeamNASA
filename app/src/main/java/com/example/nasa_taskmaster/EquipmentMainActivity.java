package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EquipmentMainActivity extends AppCompatActivity {

    private ArrayList<Equipment> equipmentList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_equipmentmain);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // DELETE LATER
        equipmentList.add(new Equipment("Drill Press", 2022, "Operational", "Heavy Duty"));
        equipmentList.add(new Equipment("Soldering Iron", 2023, "In Use", "Precision Tool"));
        equipmentList.add(new Equipment("3D Printer", 2021, "Maintenance", "FDM Printer"));
        equipmentList.add(new Equipment("Oscilloscope", 2024, "New", "Digital Storage"));

        //Code for Equipment dropdown
        AutoCompleteTextView equipmentDropdown = findViewById(R.id.categoryDropdown);

        ArrayAdapter<Equipment> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                equipmentList
        );

        equipmentDropdown.setAdapter(adapter);

        equipmentDropdown.setOnItemClickListener((parent, view, position, id) -> {
            String selected = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Selected: " + selected, Toast.LENGTH_SHORT).show();
        });

        //Clicking Add Equipment Button
        findViewById(R.id.addNewEquipmentButtonOnEquipmentPage).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewEquipment.class);
            startActivity(intent);
        });
    }

    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(ArrayList<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}