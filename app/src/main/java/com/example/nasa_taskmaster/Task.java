package com.example.nasa_taskmaster;

public class Task {
    private String taskName = "None";
    private String ownerName = "None";
    private String taskDescription = "None";
    private double taskTime = 1;

    public Task(String name, String taskDescription, String ownerName){
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.ownerName = ownerName;
    }

    public String getTaskName(){
        return taskName;
    }


}
