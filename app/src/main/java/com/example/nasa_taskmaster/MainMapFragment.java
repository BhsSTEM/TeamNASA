package com.example.nasa_taskmaster;

import android.app.AlertDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SupportMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMapFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<MarkerOptions> currMarkers = new ArrayList<>();
    private GoogleMap mMap;
    private boolean allowAddLocations;

    public MainMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SupportMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMapFragment newInstance(boolean allowAddLocations) {
        MainMapFragment fragment = new MainMapFragment();
        Bundle args = new Bundle();
        args.putBoolean("allowAddLocations", allowAddLocations);
        fragment.setArguments(args);
        return fragment;
    }

    public static MainMapFragment newInstance(boolean allowAddLocations, ArrayList<MarkerOptions> currMarkers) {
        MainMapFragment fragment = new MainMapFragment();
        Bundle args = new Bundle();
        args.putBoolean("allowAddLocations", allowAddLocations);
        args.putParcelableArrayList("currMarkers", currMarkers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            ArrayList<MarkerOptions> markers = getArguments().getParcelableArrayList("currMarkers");
            if (markers != null) {
                currMarkers = markers;
            }
            allowAddLocations = getArguments().getBoolean("allowAddLocations");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
            Log.d("MapFragment", "Map fragment is not null");
            System.out.println("Map is not null and work");
        } else {
            System.out.println("Map is null");
            Log.d("MapFragment", "Map fragment is null");
        }

        return view;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("MapFragment", String.valueOf(allowAddLocations));

        Map map = new Map();


        if(currMarkers != null)
        {
            printMarkers(mMap, currMarkers);
        }

        //Turn current locations into markers and add to lis that prints current markers
        for(Locations locations : Map.getLocations()) {
            LatLng latLng = new LatLng(locations.getLat(), locations.getLon());
            String name = locations.getName();
            
            currMarkers.add(new MarkerOptions().position(latLng)
                    .title(name));
        }

        if (allowAddLocations) //adding a new pin on the map screen
        {
            //PRINT ALL CURRENT markers in locations array
            printMarkers(mMap, currMarkers);

            mMap.setOnMapClickListener(latLng -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Name new pin");
                builder.setMessage("Please enter name:");

                EditText input = new EditText(getContext());
                builder.setView(input);

                builder.setPositiveButton("Save", (dialog, which) -> {
                    String name = input.getText().toString();
                    Toast.makeText(getContext(), "Input: " + name, Toast.LENGTH_SHORT).show();

                    //adding to main locations array
                    Locations NewLocation = new Locations(name, latLng.latitude, latLng.longitude);
                    ArrayList<Locations> locations = Map.getLocations();
                    locations.add(NewLocation);
                    Map.setLocations(locations);

                    //adding to curr markers so it will display
                    MarkerOptions marker = new MarkerOptions()
                            .position(latLng)
                            .title(name);

                    currMarkers.add(marker);

                    printMarkers(mMap, currMarkers);

                    /**Log.d("Map Fragment", "This is where the list has been modified");
                    int i = 0;
                    for(Locations location : Map.getLocations())
                    {

                        LatLng latLng1 = new LatLng(location.getLat(), location.getLon());
                        String name1 = location.getName();
                        Log.d("MapFragment", "List:" + location.getName()+ i);
                        i++;
                    }*/
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

            });
        }

        /*Log.d("Map Fragment Code", "this is the loop that accesses the new list after smth has been added");
        int i = 0;
        for(Locations location : Map.getLocations())
        {

            LatLng latLng1 = new LatLng(location.getLat(), location.getLon());
            String name1 = location.getName();
            Log.d("MapFragment", "List:" + location.getName()+ i);
            i++;
        }*/

    }

    private static void printMarkers(GoogleMap mMap, ArrayList<MarkerOptions> markers)
    {
        for (MarkerOptions marker : markers)
        {
            mMap.addMarker(marker);
        }
    }
}
