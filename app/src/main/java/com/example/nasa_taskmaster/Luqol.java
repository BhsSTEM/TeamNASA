package com.example.nasa_taskmaster;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Luqol {
    private static FirebaseAuth mAuth;
    public ArrayList<String> processes = new ArrayList<>();
    public Luqol() {
        mAuth = FirebaseAuth.getInstance();
    }
    public String log(String m) {
        Log.d(TAG, m);
        return m;
    }
    public String log(Object m) {
        Log.d(TAG, m.toString());
        return m.toString();
    }
    public long log(long m) {
        Log.d(TAG, String.valueOf(m));
        return m;
    }
    public String warn(String warning) {
        Log.w(TAG, "WARN ENCOUNTERED: " + warning);
        return warning;
    }
    public long warn(String warning, long dataCheck) {
        Log.w(TAG, "WARN ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: " + dataCheck);
        return dataCheck;
    }
    public long[] warn(String warning, long[] dataCheck) {
        Log.w(TAG, "WARN ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: ");
        for (long l : dataCheck) {
            Log.w(TAG, String.valueOf(l));
        }
        return dataCheck;
    }
    public String[] warn(String warning, String[] dataCheck) {
        Log.w(TAG, "WARN ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: ");
        for (String l : dataCheck) {
            Log.w(TAG, String.valueOf(l));
        }
        return dataCheck;
    }
    public String warn(String warning, String dataCheck) {
        Log.w(TAG, "WARN ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: " + dataCheck);
        return dataCheck;
    }
    public String error(String warning) {
        Log.e(TAG, "ERROR ENCOUNTERED: " + warning);
        return warning;
    }
    public long error(String warning, long dataCheck) {
        Log.e(TAG, "ERROR ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: " + dataCheck);
        return dataCheck;
    }
    public long[] error(String warning, long[] dataCheck) {
        Log.e(TAG, "ERROR ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: ");
        for (long l : dataCheck) {
            Log.w(TAG, String.valueOf(l));
        }
        return dataCheck;
    }
    public String[] error(String warning, String[] dataCheck) {
        Log.e(TAG, "ERROR ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: ");
        for (String l : dataCheck) {
            Log.w(TAG, String.valueOf(l));
        }
        return dataCheck;
    }
    public String error(String warning, String dataCheck) {
        Log.e(TAG, "ERROR ENCOUNTERED: " + warning + " | CHECK RELEVANT DATA: " + dataCheck);
        return dataCheck;
    }
    public ArrayList<String> getProcesses() {
        return processes;
    }
    public ArrayList<String> logProcesses() {
        for(int i = 0; i < processes.size(); i++) { //Make sure your measurements are correct!
            log(processes.get(i));
        }
        return processes;
    }
    public void startProcess(String process) {
        processes.add(process);
        log("Started process: " + process);
    }
    public void endProcess(String process) {
        log("Ended process: " + process);
        processes.remove(process);
    }
    public void interruptProcess(String interruptedProcess) {
        error("Process " + interruptedProcess + " was interrupted by program request. Below is a log of all information we have on the process:");
        warn("This process is process #" + String.valueOf(processes.indexOf(interruptedProcess)) + ".");
        warn("There are " + processes.size() + " total processes currently active.");
        List<String> duplicates = findDuplicates(processes);
        if(duplicates.isEmpty()) {
            log("There appears to be no leaking processes.");
        } else {
            error("There appears to be 1 or more leaking processes, meaning either some processes are not ending or they are triggering multiple times. Here are the identified leaks: ");
            for(int i = 0; i < duplicates.size(); i++) {
                error("Leak identified at: " + duplicates.get(i));
            }
            error("It is advised you address the leaks as soon as possible to verify your code is running as intended.");
        }
        processes.remove(interruptedProcess);
    }
    public static <T> List<T> findDuplicates(ArrayList<T> processes) {
        Set<T> seen = new HashSet<>();
        List<T> duplicates = new ArrayList<>();

        for (T process : processes) {
            if (!seen.add(process)) {
                if (!duplicates.contains(process)) {
                    duplicates.add(process);
                }
            }
        }
        return duplicates;
    }
    public void logout() {
        mAuth.getInstance()
                .signOut();
        log("User has been logged in.");
    }
}
