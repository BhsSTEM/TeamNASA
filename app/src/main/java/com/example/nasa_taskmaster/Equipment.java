package com.example.nasa_taskmaster;

public class Equipment {
    private String name;
    private int year;
    private String status;
    private String description;

    public Equipment(String name, int year, String status, String description) {
        this.name = name;
        this.year = year;
        this.status = status;
        this.description = description;
    }

    //will remove later js to test how input works
    public Equipment(String name, int year) {
        this.name = name;
        this.year = year;
        this.status = "Available";
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

    @Override
    public String toString() {
        return name;
    }

}
