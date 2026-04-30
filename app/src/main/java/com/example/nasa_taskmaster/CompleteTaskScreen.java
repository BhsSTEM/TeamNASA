package com.example.nasa_taskmaster;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CompleteTaskScreen extends AppCompatActivity {
    private static Task displayTask;
    private String endTime;
    private boolean taskJustDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complete_task_screen);
        TextView taskHeader = findViewById(R.id.taskHeader2);
        Log.d("has task:", "" + (displayTask != null));
        taskHeader.setText(displayTask.getTaskName());

        TextView startTimeTextViewHour = findViewById(R.id.editTextTime2);
        TextView startTimeTextViewMin = findViewById(R.id.editTextTime4);
        TextView endTimeTextViewHour = findViewById(R.id.editTextTime5);
        TextView endTimeTextViewMin = findViewById(R.id.editTextTime2);
        Button justDoneBtn = findViewById(R.id.button5);
        Button completeBtn = findViewById(R.id.button6);


        justDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE).toString();
                taskJustDone = true;

            }
        });
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTask.settoComplete();
                String oldTime = displayTask.getTaskTime();
                String startTime = startTimeTextViewHour.getText().toString() + " : " + startTimeTextViewMin.getText().toString();
                int newTime = 0;

                if(!taskJustDone) {
                    endTime = endTimeTextViewHour.getText().toString() + " : " +  endTimeTextViewMin.getText().toString();
                }
                Log.d("Start time", startTime + "");
                Log.d("End time", endTime + "");
                Scanner scannerStart = new Scanner(startTime);
                Scanner scannerEnd = new Scanner(endTime);
                newTime = (scannerEnd.nextInt() + scannerEnd.nextInt()*60) - (scannerStart.nextInt() + scannerStart.nextInt()*60);

                Log.d("task time", newTime + "");
                displayTask.setTaskTime(newTime);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static void setTask(Task task){
        displayTask = task;
    }


}