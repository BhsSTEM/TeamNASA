package com.example.nasa_taskmaster;



import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String username = "None";
    private String uID = "None";
    private Map<String, Task> tasksList;
    private FirebaseAuth mAuth;
    public static FirebaseFirestore dataBase;
    private boolean taskedUpdatedToDB = false;
    public User(String uID, String username, Map<String, Task> tasksList){
        this.username = username;
        this.tasksList = tasksList;
        this.uID = uID;

    }

    public static User getUserfromUID(String uID){
        if(dataBase == null){
            dataBase = FirebaseFirestore.getInstance("parkerptestbase");
            dataBase.enableNetwork();
        }



        String userName = "None";
        Map<String, Task> taskArray = new HashMap<>();
        DocumentSnapshot document;

        DocumentReference docRef = dataBase.collection(uID).document("Tasks");
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
            }
        });





        return new User(uID, "Guest",taskArray );
    }





    public void addTask(Task task){
        if(task != null){
            Map<String, Task>  taskMap = new HashMap<>();
            taskMap.put(task.getTaskName(), task);
            updateTaskstoFireBase(taskMap);
        }
    }
    public void addTasks(ArrayList<Task> tasks){
        if(tasks.size() > 0){
            Map<String, Task>  taskMap = new HashMap<>();
            for(int i = 0; i < tasks.size(); i++){
                taskMap.put(tasks.get(i).getTaskName(), tasks.get(i));
            }
            updateTaskstoFireBase(taskMap);
        }

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
                taskMap.put(uID + " tasks", tasksList.get(i));
            }
            dataBase.collection(uID).document( "Tasks").set(taskMap, SetOptions.merge());

        }
    }

    private void updateTaskstoFireBase(Map<String, Task> taskMap){
        if(!taskedUpdatedToDB){
            updateTasktoDBHelper(taskMap);
            taskedUpdatedToDB = true;
        }else{
            Log.d("Task already updated", "");
        }
    }
    private void updateTasktoDBHelper(Map<String, Task> taskMap){
        if (tasksList.size() > 0 && dataBase != null) {
            dataBase.collection(uID).document( " Tasks").set(taskMap, SetOptions.merge());

        }
    }

    public ArrayList<Task> getTasksFromDataBase(){
        ArrayList<Task> outList = new ArrayList<>();
        DocumentReference docRef = dataBase.collection(uID).document(  "Tasks");
        Map<String, Object> results = docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                Log.d("Task Got", "Success!!");
            }
        }).getResult().getData();

        if(tasksList.size() > 0){
                for(int i = 0; i < outList.size(); i++){
                    outList.add(tasksList.get(i));
                }
            }


        if(results.size() > 0){
                for(int i = 0; i < results.size(); i++){
                    outList.add((Task)(results.get(i)));
                }
        }

        return outList;


    }






}
