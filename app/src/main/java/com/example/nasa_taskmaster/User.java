package com.example.nasa_taskmaster;



import static android.content.ContentValues.TAG;

import static com.example.nasa_taskmaster.HomeScreen.addTasktoList;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String username = "None";
    public static boolean isSetup = false;
    private String uID = "None";
    private ArrayList<Task> tasksList;
    public static FirebaseAuth mAuth;
    public static FirebaseFirestore dataBase;
    public boolean gettingTasks = false;
    public User(String uID){
        this.uID = uID;
        Log.d("Does user have a collection:", (dataBase.collection(uID).getPath() != null) + "");
        if(dataBase.collection(uID).getPath() != null) {
            Log.d("Does user have a collection:", dataBase.collection(uID).getPath());
            this.tasksList = new ArrayList<>();
            Log.d("User task amount:", this.tasksList.size() + "");

        }

        Log.d("UID: ", this.uID);



    }

    public static User getUserfromUID(String uID){
        if(dataBase == null || !isSetup){
            isSetup = true;
            dataBase = FirebaseFirestore.getInstance("parkerptestbase");
            dataBase.enableNetwork();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            Log.d("Mauth is null 93", (mAuth.getCurrentUser() == null) + "");

        }

        return new User(uID);
    }

    public static FirebaseAuth getMAuthh(){
        if(!isSetup){
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    public String getUsername(){
        return mAuth.getCurrentUser().getDisplayName();
    }





    public void addTask(Task task){
        Log.d("adding data 113:",dataBase.collection(uID) + "");
            dataBase.collection(uID).document(task.getTaskName()).set(task);
    }
    public void addTasks(ArrayList<Task> tasks){
            ArrayList<Task>   taskMap = new ArrayList<>();
            for(int i = 0; i < tasks.size(); i++){
                taskMap.add(tasks.get(i));
            }
            updateTaskstoFireBase(taskMap);

    }


    private void updateTaskstoFireBase(ArrayList<Task> taskMap){
        updateTasktoDBHelper(taskMap);
    }
    public void updateTasktoFireBase(Task task){
        if(task != null){
            dataBase.collection(uID).document(task.getTaskName()).set(task);
        }
    }
    private void updateTasktoDBHelper(ArrayList<Task>  taskMap){
        Log.d("adding data:",dataBase.collection(uID) + "");
        Log.d("data size:",tasksList.size() + "");
        if (tasksList.size() > 0) {
            for(int i = 0; i < tasksList.size(); i++) {
                dataBase.collection(uID).document(taskMap.get(i).getTaskName()).set(taskMap.get(i));
                Log.d("adding data:",dataBase.collection(uID).getId() + "");
            }

        }
    }

    public ArrayList<Task> getTasksFromCollection(CollectionReference collRef, Activity activity){

        ArrayList<Task> outList = new ArrayList<>();

        collRef.get().addOnCompleteListener(activity, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                Log.d("Gets collection 342", "true");

                if(task.getResult().size() > 0){
                    List<DocumentSnapshot> docRefs = task.getResult().getDocuments();
                    Log.d("Collection size 343", "" + docRefs.size() );
                    for(int i = 0; i < docRefs.size();i++){
                        DocumentSnapshot docRef = docRefs.get(i);
                        String taskName = docRef.get("taskName") + "";
                        String ownerName = docRef.get("ownerName") + "";
                        String taskLocation= docRef.get("taskLocation") + "";
                        String taskDescription= docRef.get("taskDescription") + "";
                        String taskDeadline= docRef.get("taskDeadline") + "";
                        String taskStartDate=docRef.get("taskStartDate") + "";
                        String taskTime= docRef.get("taskTime") + "";

                        Task newTask = new Task(taskName, taskDescription, ownerName, taskLocation, taskDeadline, taskStartDate, taskTime);
                        HomeScreen.taskssList.add(newTask);


                    }


                    HomeScreen.updateTaskFrags();
                    Log.d("Task list size: ", HomeScreen.taskssList.size() + "");

                }

            }
        });






        /*

        if(results.size() > 0){
                for(int i = 0; i < results.size(); i++){
                    outList.add((Task)(results.get(i)));
                }
        }

         */

        Log.d("SIZE 307", "" + outList.size());
        return outList;
    }

    public ArrayList<Task> getTasksList(){
        return tasksList;
    }







}
