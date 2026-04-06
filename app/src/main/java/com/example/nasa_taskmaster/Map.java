package com.example.nasa_taskmaster;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Map extends AppCompatActivity {

    private static ArrayList<Locations> locations = new ArrayList<>();

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

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerViewXX, MainMapFragment.newInstance(true)).commit();

    }

    public static ArrayList<Locations> getLocations() {
        return locations;
    }

    public static void setLocations(ArrayList<Locations> newLocations) {
        locations = newLocations;
    }
}