package com.example.nasa_taskmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class HomeScreen extends AppCompatActivity {

    TaskFragment taskFrag = new TaskFragment();
    Task testTask = new Task("test", "1 2 3", "owned");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        taskFrag.setTask(testTask);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.TaskFragmentConainterView, taskFrag)
                    .commit();
            taskFrag.setTask(testTask);
        }

        Button addTaskBtn = findViewById(R.id.addTaskbtn);

        addTaskBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("Works");

            }

        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}