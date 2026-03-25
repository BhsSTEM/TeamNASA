package com.example.nasa_taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    public static  ArrayList<TaskFragment> taskFragments = new ArrayList<>();
    private static  ArrayList<TaskFragment> dataList = new ArrayList<>();

    private  RecyclerView accordian;
    private HomeScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);


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
        Log.d("WORKS at", "" + 65);
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

    }

    public static void addTasktoList(Task task){
        Log.d("TASK LIST BEFORE","" +  taskFragments.size());
        TaskFragment newtask = new TaskFragment();
        newtask.setTask(task);
        taskFragments.add(newtask);
        Log.d("TASK LIST AFTER","" + taskFragments.size());
    }

    public static ArrayList<TaskFragment> getTaskFragments(){
        Log.d("TASK LIST AFTER","" + taskFragments.size());
        return taskFragments;
    }

}