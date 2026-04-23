package com.example.nasa_taskmaster;



import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import java.util.Map;

public class User {

    private String username = "None";
    private String uID = "None";
    private ArrayList<Task> tasksList;
    public static FirebaseAuth mAuth;
    public static FirebaseFirestore dataBase;
    private boolean taskedUpdatedToDB = false;
    public User(String uID){
        this.uID = uID;
        Log.d("Does user have a collection:", (dataBase.collection(uID).getPath() != null) + "");
        if(dataBase.collection(uID).getPath() != null) {
            Log.d("Does user have a collection:", dataBase.collection(uID).getPath());
            this.tasksList = getTasksFromDataBase(uID);
        }

        Log.d("UID: ", this.uID);



    }

    public static User getUserfromUID(String uID){
        if(dataBase == null || mAuth == null){
            dataBase = FirebaseFirestore.getInstance("parkerptestbase");
            dataBase.enableNetwork();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            Log.d("Mauth is null 93", (mAuth.getCurrentUser() == null) + "");
            if(mAuth.getCurrentUser() == null){
                mAuth.signInWithEmailAndPassword("fakeuser@gmail.com", "password")
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> authResult) {
                                if (authResult.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("sign in", "signInWithEmail:success");
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // If sign in fails, display a message to the user.
                                Log.w("signInWithEmail:failure", e.getMessage() + "");
                            }
                        });
            }

        }

        return new User(uID);
    }

    public static FirebaseAuth getMAuthh(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword("fakeuser@gmail.com", "password")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> authResult) {
                            if (authResult.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("sign in", "signInWithEmail:success");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // If sign in fails, display a message to the user.
                            Log.w("signInWithEmail:failure", e.getMessage() + "");
                        }
                    });

            return mAuth;
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

    public ArrayList<Task> getTasksFromDataBase(){
        ArrayList<Task> outList = new ArrayList<>();
        CollectionReference collRef = dataBase.collection(uID);

        collRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                Log.d("Gets collection", "true");
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

    public ArrayList<Task> getTasksFromDataBase(String uID){
        ArrayList<Task> outList = new ArrayList<>();
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
