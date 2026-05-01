package com.example.nasa_taskmaster;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowLocationMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowLocationMapFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String location;
    //private Locations location;
    private GoogleMap mMap;

    public ShowLocationMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param location Parameter 2.
     * @return A new instance of fragment ShowLocationMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowLocationMapFragment newInstance(String location) {
        ShowLocationMapFragment fragment = new ShowLocationMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, location);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            location = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_location_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map2);


        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
            Log.d("ShowLocationMapFragment", "Map fragment is not null");
        } else {
            Log.d("ShowLocationMapFragment", "Map fragment is null");
        }

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        Map map = new Map();

        Log.d("ShowLocationMapFragment", "On Map Ready runs:");

        //convert location string to locations object
        Locations selectedLocation = new Locations("Null Location", 0, 0);
        ArrayList<Locations> locations;
        locations = map.getLocations();

        //Log.d("ShowLocationMapFragment", "On Map Ready runs:");

        if(locations.isEmpty()) {
            locations.add(new Locations("Chicago", 41.8781, -87.6298));
        }

        for(Locations location1 : locations)
        {
            if(location1.getName().equals(location))
            {
                selectedLocation = location1;
                Log.d("ShowLocationMapFragment", "Location:" + selectedLocation.getName());
                break;
            }
        }

        //add code that shows the location of a thing
        LatLng latLng = new LatLng(selectedLocation.getLat(), selectedLocation.getLon());
        String name = selectedLocation.getName();

        MarkerOptions marker = new MarkerOptions()
                .position(latLng)
                .title(name);

        mMap.addMarker(marker);

    }
}