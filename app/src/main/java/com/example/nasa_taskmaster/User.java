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
import com.google.firebase.firestore.QuerySnapshot;
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
    public User(String uID){
        Log.d("UID: ", this.uID);
        this.tasksList = getTasksFromDataBase(uID);
        this.uID = uID;


    }

    public static User getUserfromUID(String uID){
        if(dataBase == null){
            dataBase = FirebaseFirestore.getInstance("parkerptestbase");
            dataBase.enableNetwork();
        }

        return new User(uID);
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
        updateTasktoDBHelper();
    }
    private void updateTasktoDBHelper(){
        if (tasksList.size() > 0 && dataBase != null) {;
            Map<String, Task> taskMap = new HashMap<>();
            for (int i = 0; i < tasksList.size(); i++) {
                taskMap.put(uID + " tasks", tasksList.get(i));
            }

        }
    }

    private void updateTaskstoFireBase(Map<String, Task> taskMap){
        updateTasktoDBHelper(taskMap);
    }
    private void updateTasktoDBHelper(Map<String, Task> taskMap){
        if (tasksList.size() > 0 && dataBase != null) {
            for(int i = 0; i < tasksList.size(); i++) {
                dataBase.collection(uID).add(taskMap.get(i));
            }

        }
    }

    public ArrayList<Task> getTasksFromDataBase(){
        ArrayList<Task> outList = new ArrayList<>();
        DocumentReference docRef = dataBase.collection(uID).document(  "Tasks");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                Map<String, Object> result = task.getResult().getData();
                Log.d("Task Got", "Success!!");
                if(result != null){
                    Log.d("Task Type", result.get(0).getClass() + "");
                }
                Log.d("Task Type", "null" );
            }
        });



        if(tasksList.size() > 0){
                for(int i = 0; i < outList.size(); i++){
                    outList.add(tasksList.get(i));
                }
            }

        /*

        if(results.size() > 0){
                for(int i = 0; i < results.size(); i++){
                    outList.add((Task)(results.get(i)));
                }
        }

         */

        return outList;


    }

    public Map<String,Task> getTasksFromDataBase(String uID){
        Map<String,Task> outList = new HashMap<>();
        if(dataBase.collection(uID).getPath() != null ) {
            dataBase.collection(uID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                    Log.d("Task Size: ", task.getResult().size() + "");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Task Get Failed: ", e.getMessage() + "");
                }
            });
        }





        /*

        if(results.size() > 0){
                for(int i = 0; i < results.size(); i++){
                    outList.add((Task)(results.get(i)));
                }
        }

         */

        return outList;


    }






}
