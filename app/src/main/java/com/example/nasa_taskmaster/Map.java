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

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static ArrayList<Locations> locations = new ArrayList<>();

    private static ArrayList<MarkerOptions> currMarkers = new ArrayList<>();

    private String[] options = {"Nothing", "Equipment", "Tasks for Today", "Locations", "All Tasks"};

    private static ArrayList<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        if(locations.isEmpty())
        {
            locations.add(new Locations("Chicago", 41.8781, -87.6298));
            locations.add(new Locations("New York", 40.7128, -74.0060));
            locations.add(new Locations("Los Angeles", 34.0522, -118.2437));
            locations.add(new Locations("Houston", 29.7604, -95.3698));
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(false)).commit();


        //
        Spinner spinner = findViewById(R.id.showStuffSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position == 0)
        {
            //do nothing, print nothing
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(false)).commit();

        } else if (position == 1) {
            EquipmentMainActivity equipmentMainActivity = new EquipmentMainActivity();
            equipmentList = equipmentMainActivity.getEquipmentList();
            currMarkers.clear();

            for(Equipment equipment : equipmentList)
            {
                if(equipment.getLocation() != null)
                {
                    Locations locations = equipment.getLocation();
                    LatLng latLng = new LatLng(locations.getLat(), locations.getLon());
                    String name = locations.getName();
                    currMarkers.add(new MarkerOptions().position(latLng)
                            .title(name));
                }
            }

            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(false, currMarkers)).commit();


        } //equipment
        else if (position == 2) {

            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(false)).commit();

        } else if (position == 3) { //locations

            currMarkers.clear();

            for(Locations location : locations) {
                LatLng latLng = new LatLng(location.getLat(), location.getLon());
                String name = location.getName();
                Log.d("Map", "Location name" + location.getName());
                currMarkers.add(new MarkerOptions().position(latLng)
                        .title(name));
            }

            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(true, null)).commit();

        } else if (position == 4) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public static ArrayList<Locations> getLocations() {
        return locations;
    }

    public static void setLocations(ArrayList<Locations> newLocations) {
        locations = newLocations;
    }
}