package com.example.nasa_taskmaster;



import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class User {

    private String username = "None";
    private String password = "None";
    private ArrayList<Task> tasksList = new ArrayList<>();
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }



    public void addTask(Task task){
        tasksList.add(task);
    }
    public void addTasks(ArrayList<Task> tasks){
        tasksList.addAll(tasks);
    }






}
