package com.example.nasa_taskmaster;

public class Equipment {
    private String name;
    private int year;
    private String status;
    private String description;

    private Locations location;

    public Equipment(String name, int year, String status, String description, Locations location) {
        this.name = name;
        this.year = year;
        this.status = status;
        this.description = description;
        this.location = location;
    }

    //will remove later js to test how input works
    public Equipment(String name, int year, String status) {
        this.name = name;
        this.year = year;
        this.status = status;
        this.description = "No Description";
    }

    // Getters and setters for the fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return name;
    }

}
