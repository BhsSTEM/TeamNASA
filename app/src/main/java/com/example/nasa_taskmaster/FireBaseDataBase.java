package com.example.nasa_taskmaster;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireBaseDataBase {
    public FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public FireBaseDataBase(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance("parkerptestbase");
        db.enableNetwork();
    }

    public void addData(ArrayList<Task> tasks) {

        if (tasks.size() > 0 && db != null) {
            Map<String, Task> taskMap = new HashMap<>();
            for (int i = 0; i < tasks.size(); i++) {
                Log.d("Works at 28", "");
                taskMap.put(tasks.get(i).getOwnerName(), tasks.get(i));
            }
                db.collection(tasks.get(0).getOwnerName()).document("Tasks").set(taskMap);

        }
    }
}
