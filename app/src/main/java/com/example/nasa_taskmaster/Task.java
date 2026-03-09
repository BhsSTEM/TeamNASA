package com.example.nasa_taskmaster;



public class Task {
    private String taskName = "None";
    private String ownerName = "None";
    private String taskDescription = "None";
    private double taskTime = 1;
    private String taskLocation = "None";


    public Task(String name, String taskDescription, String ownerName){
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.ownerName = ownerName;
    }


    // Constructor for creating the object from a Parcel

    public String getTaskName(){
        return taskName;
    }
    public String getTaskInfo(){
        String info = "";
        info += "Task Name:   " +  taskName;
        info += "Task Description:   " +  taskDescription;
        info += "Task Location:   " +  taskLocation;
        info += "Task Time:   " +  taskTime;
        return info;
    }


}
