package com.example.nasa_taskmaster;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.nasa_taskmaster.User.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeScreen extends AppCompatActivity {
    public static String userID;
    public static  ArrayList<TaskFragment> taskFragments = new ArrayList<>();
    public static  ArrayList<Task> taskssList= new ArrayList<>();
    private static  ArrayList<TaskFragment> dataList = new ArrayList<>();

    public static User user;

    private  static RecyclerView accordian;
    private static HomeScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

/*
        if(taskFragments.size() <= 0) {


            TaskFragment taskFrag1 = new TaskFragment();
            TaskFragment taskFrag2 = new TaskFragment();
            TaskFragment taskFrag3 = new TaskFragment();

            taskFrag1.setTask(new Task("Task 1",
                    "Test Task",
                    "user1",
                    "Somewhere",
                    "4 - 10 - 2026",
                    "3 - 7 - 2026"));
            taskFrag2.setTask(new Task("Task 2",
                    "Test Task",
                    "user1",
                    "Somewhere",
                    "3 - 10 - 2026",
                    "1 - 7 - 2026"));
            taskFrag3.setTask(new Task("Task 3",
                    "Test Task",
                    "user1",
                    "Somewhere",
                    "4 - 25 - 2026",
                    "3 - 7 - 2026"));
            taskFragments.add(taskFrag1);
            taskFragments.add(taskFrag2);
            taskFragments.add(taskFrag3);
        }
        */


        if(user == null){
            getMAuthh();
            Log.d("Mauth is null", (mAuth.getCurrentUser() == null) + "");
            userID = mAuth.getCurrentUser().getUid();
            user = User.getUserfromUID(userID);
            //FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
            // if(fireUser == null) {


            //}



        }

        taskssList = user.getTasksFromCollection(dataBase.collection(userID), this);







        if (savedInstanceState == null) {
            /*
            String taskName = getIntent().getStringExtra("NEW_TASK_NAME");
            String taskDescript = getIntent().getStringExtra("NEW_TASK_DESCRIPTION");
            String taskLocation = getIntent().getStringExtra("NEW_TASK_LOCATION");
            if(taskName != null &&
                    taskDescript != null &&
                    taskLocation != null){
                TaskFragment newFrag = new TaskFragment();
                newFrag.setTask(new Task(taskName, taskDescript, taskLocation));
                taskFragments.add(newFrag);
            }
            boolean addedTask = false;

             */

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navigationBar4, new Navigation_Bar())
                    .commit();
           /* getSupportFragmentManager().beginTransaction()
                    .replace(R.id.topNavBar6, new TopNavBar())
                    .commit();

            */



        }




        Button addTaskBtn = findViewById(R.id.addTaskbtn);
        Button accordian1Btn = findViewById(R.id.accordian1Btn);
        Button calenderBtn = findViewById(R.id.calendarBtn);
        accordian = findViewById(R.id.accordianLayout);
        accordian.setLayoutManager(new LinearLayoutManager(this));

        dataList = taskFragments;
        adapter = new HomeScreenAdapter(dataList);
        accordian.setAdapter(adapter);



        addTaskBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this, AddTaskScreen.class);
                startActivity(intent);
            }
        });


        accordian1Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this, TaskMoreScreen.class);
                startActivity(intent);
            }

        });

        calenderBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this, CalenderScreen.class);
                startActivity(intent);
            }

        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            Log.d("SIZE2: ",  taskFragments.size() + "");


    }

    public static void addTasktoList(Task task){
        TaskFragment newtask = new TaskFragment();
        newtask.setTask(task);
        taskFragments.add(newtask);
    }

    public static ArrayList<TaskFragment> getTaskFragments(){
        return taskFragments;
    }

    public static void updateTaskFrags(){
        taskFragments.clear();
        Log.d("taskssList size", taskssList.size() + "");
        for(int i = 0; i < taskssList.size(); i++){
            addTasktoList(taskssList.get(i));
            Log.d("Task", taskssList.get(i).getTaskName());
        }

        Log.d("dataList size", dataList.size() + "");
        adapter = new HomeScreenAdapter(dataList);
        accordian.setAdapter(adapter);
    }

}