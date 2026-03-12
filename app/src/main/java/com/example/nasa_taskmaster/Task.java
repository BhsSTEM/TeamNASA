package com.example.nasa_taskmaster;



public class Task {
    private String taskName = "None";
    private String ownerName = "None";
    private String taskDescription = "None";
    private double taskTime = 0;
    private String taskLocation = "None";
    private String taskDeadline = "None";
    private String taskStartDate = "None";


    public Task(String name, String taskDescription, String ownerName){
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.ownerName = ownerName;
    }

    public Task(String name, String taskDescription, String ownerName, String taskLocation, String taskDeadline, String taskStartDate){
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.ownerName = ownerName;
        this.taskLocation = taskLocation;
        this.taskDeadline = taskDeadline;
        this.taskStartDate = taskStartDate;
    }



    // Constructor for creating the object from a Parcel

    public String getTaskName(){
        return taskName;
    }
    public String getTaskInfo(){
        String info = "";
        info += "Task Name:   " +  taskName;
        info +=  "\n";
        info +=  "\n";
        info += "Task Description:   " +  taskDescription;
        info +=  "\n";
        info +=  "\n";
        info += "Task Location:   " +  taskLocation;
        info +=  "\n";
        info +=  "\n";
        info += "Task Time:   " +  taskTime;
        info +=  "\n";
        info +=  "\n";
        info += "To Do on:   " +  taskDeadline;
        info +=  "\n";
        info +=  "\n";
        info += "Created on :   " +  taskStartDate;
        info +=  "\n";
        info +=  "\n";
        info += "Created by :   " +  ownerName;
        return info;
    }


}
