package com.example.nasa_taskmaster;

import android.content.Intent;
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

public class HomeScreen extends AppCompatActivity {
    boolean[] taskArray = new boolean[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        if (savedInstanceState == null) {
            String taskName = getIntent().getStringExtra("NEW_TASK_NAME");
            String taskDescript = getIntent().getStringExtra("NEW_TASK_DESCRIPTION");
            String taskLocation = getIntent().getStringExtra("NEW_TASK_LOCATION");
            Task task = new Task(taskName, taskDescript, taskLocation);
            boolean addedTask = false;
            for(int i = 0; i < 3; i++) {


                if (taskName != null && taskDescript != null && taskLocation != null) {
                    Log.d("ADDTASK","" + (taskArray[i]) );
                    if(!taskArray[i] && !addedTask ){
                        addFrag(i,task);
                        taskArray[i] = true;
                        addedTask =  true;
                    }else{
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.TaskFragmentConainterView, new TaskFragment())
                                .commit();
                    }

                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.TaskFragmentConainterView, new TaskFragment())
                            .commit();
                }
            }
        }


        Button addTaskBtn = findViewById(R.id.addTaskbtn);
        Button accordian1Btn = findViewById(R.id.accordian1Btn);

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

    private void addFrag(int index, Task task){
        Log.d("ADDFRAG", "" + index);

        TaskFragment fragment = new TaskFragment();
        fragment.setTask(task);
        if(index == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.TaskFragmentConainterView, fragment)
                    .commit();
        }else if(index == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.TaskFragmentConainterView2, fragment)
                    .commit();
        }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.TaskFragmentContainer3, fragment)
                        .commit();
        }
    }
}