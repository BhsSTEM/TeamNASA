package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EquipmentMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static ArrayList<Equipment> equipmentList = new ArrayList<>();

    private String[] options = {};

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
        if (equipmentList.isEmpty())
        {
            equipmentList.add(new Equipment("Drill Press", 2022, "Operational", "Heavy Duty", new Locations("Chicago", 41.8781, -87.6298)));
            equipmentList.add(new Equipment("Soldering Iron", 2023, "In Use", "Precision Tool", null));
            equipmentList.add(new Equipment("3D Printer", 2021, "Maintenance", "FDM Printer", null));
            equipmentList.add(new Equipment("Oscilloscope", 2024, "New", "Digital Storage", null));
        }

        //Code for Equipment dropdown - this will be for filters
        Spinner spinner = findViewById(R.id.categoryDropdown);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //recyclerView - scrollable list of equipment
        RecyclerView recyclerView = findViewById(R.id.equipmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter1 = new Adapter(equipmentList, this);
        recyclerView.setAdapter(adapter1);


        //Clicking Add Equipment Button
        findViewById(R.id.addNewEquipmentButtonOnEquipmentPage).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewEquipment.class);
            startActivity(intent);
        });
    }

    public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public ArrayList<Equipment> getEquipmentList() {    return equipmentList;   }

    public void setEquipmentList(ArrayList<Equipment> equipmentList)
    {
        this.equipmentList = equipmentList;
    }
}