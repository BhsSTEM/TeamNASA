package com.example.nasa_taskmaster;



import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String username = "None";
    private String password = "None";
    private ArrayList<Task> tasksList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseFirestore dataBase;
    private boolean taskedUpdatedToDB = false;
    public User(String username){
        this.username = username;
        mAuth  = FirebaseAuth.getInstance();
        dataBase = FirebaseFirestore.getInstance("parkerptestbase");
        dataBase.enableNetwork();
    }

    public static void createUserAccount(String username, String password){
        FirebaseAuth mAuth  = FirebaseAuth.getInstance();
        FirebaseFirestore dataBase = FirebaseFirestore.getInstance("parkerptestbase");
        dataBase.enableNetwork();
        Map<String, String> passMap = new HashMap<>();
        passMap.put(username, password);

        dataBase.collection(username).document("UserPass").set(passMap);

    }



    public void addTask(Task task){
        tasksList.add(task);
        taskedUpdatedToDB = false;
    }
    public void addTasks(ArrayList<Task> tasks){
        tasksList.addAll(tasks);
    }

    public void updateTaskstoFireBase(){
        if(!taskedUpdatedToDB){
            updateTasktoDBHelper();
            taskedUpdatedToDB = true;
        }else{
            Log.d("Task already updated", "");
        }
    }
    private void updateTasktoDBHelper(){
        if (tasksList.size() > 0 && dataBase != null) {;
            Map<String, Task> taskMap = new HashMap<>();
            for (int i = 0; i < tasksList.size(); i++) {
                taskMap.put(username+ i, tasksList.get(i));
            }
            dataBase.collection(username).document(username + " Tasks").set(taskMap, SetOptions.merge());

        }
    }

    public ArrayList<Task> getTasksFromDataBase(){

        DocumentReference docRef = dataBase.collection(username).document(username + " Tasks");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }});

        return tasksList;


    }






}
