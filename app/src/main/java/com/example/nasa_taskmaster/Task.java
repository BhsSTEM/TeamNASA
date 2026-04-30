package com.example.nasa_taskmaster;


import static java.lang.Integer.parseInt;

import android.util.Log;

public class Task {
    private String taskName = "None";
    private String ownerName = "None";
    private String taskDescription = "None";
    private String taskTime = "0";
    private String taskLocation = "None";
    private String taskDeadline = "None";
    private String taskStartDate = "None";
    private boolean isCompplete = false;


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

    public Task(String name, String taskDescription, String ownerName, String taskLocation, String taskDeadline, String taskStartDate, String taskTime){
        this.taskName = name;
        this.taskDescription = taskDescription;
        this.ownerName = ownerName;
        this.taskLocation = taskLocation;
        this.taskDeadline = taskDeadline;
        this.taskStartDate = taskStartDate;
        this.taskTime = taskTime;
    }



    // Constructor for creating the object from a Parcel

    public String getTaskName(){
        return taskName;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public String getTaskDeadline(){
        return taskDeadline;
    }
    public String getTaskDescription(){
        return taskDescription;
    }

    public String getTaskLocation(){
        return taskLocation;
    }
    public String getTaskTime(){
        return taskTime + "";
    }
    public String getTaskStartDate(){
        return taskStartDate;
    }
    private boolean isTaskComplete(){
        return isCompplete;
    }

    public void setTaskTime(double time){
        taskTime = "" + time;
    }

    public void settoComplete(){
        isCompplete = true;
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
        info +=  "\n";
        info +=  "\n";
        info += "Status :   " +  ((isCompplete) ? "Done":"Not Done");
        return info;
    }
    public boolean compareDate(String targetDate){
        boolean theSameDate = true;
        for(int i = 0; i < taskDeadline.length(); i++){
            theSameDate = (theSameDate) && (taskDeadline.charAt(i) == targetDate.charAt(i));
        }
      //  Log.d("Task dates deadline: ", ""+ taskDeadline);
       // Log.d("Target Task dates deadline: ", ""+ targetDate);
      //  Log.d("Task dates the same: ", ""+ theSameDate);
        return theSameDate;
    }



}
