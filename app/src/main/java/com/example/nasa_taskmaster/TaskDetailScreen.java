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

        TextView taskOwnerText = findViewById(R.id.taskOwnerNameTextView);
        TextView taskDescript = findViewById(R.id.TaskDescriptionTextView);
        TextView taskTime = findViewById(R.id.taskTimeTextView);
        TextView taskLocation = findViewById(R.id.taskLocationTextView);
        TextView taskDueDate = findViewById(R.id.taskDueDtaTextView);
        TextView taskStartDate = findViewById(R.id.taskStartDate);
        TextView taskHeader = findViewById(R.id.taskHeaderDetail);

        Button completeBtn1 = findViewById(R.id.completeBtn);
        completeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(TaskDetailScreen.this, CompleteTaskScreen.class);
                    CompleteTaskScreen.setTask(displayTask);
                    startActivity(intent);
            }
        });
        if(displayTask != null){
            taskOwnerText.setText(displayTask.getTaskName());
            taskDescript.setText(displayTask.getTaskDescription());
            taskDueDate.setText(displayTask.getTaskDeadline());
            taskLocation.setText(displayTask.getTaskLocation());
            taskTime.setText(displayTask.getTaskTime());
            taskStartDate.setText(displayTask.getTaskStartDate());
            taskHeader.setText(displayTask.getTaskName());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static void setDetailedTask(Task task){
        displayTask = task;
    }
}