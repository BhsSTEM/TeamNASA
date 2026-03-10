package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EquipmentMainActivity extends AppCompatActivity {

    private static ArrayList<Equipment> equipmentList = new ArrayList<>();

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
        if (equipmentList.isEmpty()) {
            equipmentList.add(new Equipment("Drill Press", 2022, "Operational", "Heavy Duty"));
            equipmentList.add(new Equipment("Soldering Iron", 2023, "In Use", "Precision Tool"));
            equipmentList.add(new Equipment("3D Printer", 2021, "Maintenance", "FDM Printer"));
            equipmentList.add(new Equipment("Oscilloscope", 2024, "New", "Digital Storage"));
        }

        //Code for Equipment dropdown - this will be for filters
        AutoCompleteTextView equipmentDropdown = findViewById(R.id.categoryDropdown);

        //recyclerView - scrollable list of equipment
        RecyclerView recyclerView = findViewById(R.id.equipmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(equipmentList, this);
        recyclerView.setAdapter(adapter);


        //Clicking Add Equipment Button
        findViewById(R.id.addNewEquipmentButtonOnEquipmentPage).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewEquipment.class);
            startActivity(intent);
        });
    }

    public ArrayList<Equipment> getEquipmentList() {    return equipmentList;   }

    public void setEquipmentList(ArrayList<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}