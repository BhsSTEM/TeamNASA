package com.example.nasa_taskmaster;

public class Locations {

    private String name;
    private double lat;
    private double lon;

    public Locations(String name, double lat, double lon)
    {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName()
    {
        return name;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }
}
