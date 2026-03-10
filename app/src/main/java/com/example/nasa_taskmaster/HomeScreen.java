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
    private static  ArrayList<Task> taskFragments =new ArrayList<>();
    private static  ArrayList<Task> dataList =new ArrayList<>();

    private  RecyclerView accordian;
    private HomeScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        if (savedInstanceState == null) {

            String taskName = getIntent().getStringExtra("NEW_TASK_NAME");
            String taskDescript = getIntent().getStringExtra("NEW_TASK_DESCRIPTION");
            String taskLocation = getIntent().getStringExtra("NEW_TASK_LOCATION");
            boolean addedTask = false;
        }




        Button addTaskBtn = findViewById(R.id.addTaskbtn);
        Button accordian1Btn = findViewById(R.id.accordian1Btn);
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
                LinearLayout accordian = findViewById(R.id.accordianLayout);
                if(accordian.getVisibility() == View.VISIBLE){
                    accordian.setVisibility(View.GONE);
                }else{
                    accordian.setVisibility(View.VISIBLE);
                }
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
        taskFragments.add(task);
        Log.d("TASK LIST AFTER","" + taskFragments.size());
    }

}