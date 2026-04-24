package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TaskDetailScreen extends AppCompatActivity {
    private static Task displayTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_detail_screen);

        TextView taskText = findViewById(R.id.taskView);
        TextView taskHeader = findViewById(R.id.taskHeader);
        String taskInfo = getIntent().getStringExtra("TaskInfo");
        String taskName = getIntent().getStringExtra("TaskName");

        Button completeBtn1 = findViewById(R.id.completeBtn);
        completeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTask.settoComplete();
                if(displayTask != null){
                    taskText.setText(displayTask.getTaskInfo());
                    taskHeader.setText(displayTask.getTaskName());
                }
            }
        });
        if(displayTask != null){
            taskText.setText(displayTask.getTaskInfo());
            taskHeader.setText(displayTask.getTaskName());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}