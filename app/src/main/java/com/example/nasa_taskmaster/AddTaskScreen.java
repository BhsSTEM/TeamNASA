package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTaskScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task_screen);

        Button addTaskbtn = findViewById(R.id.addTaskBtn);

        EditText newTaskName = findViewById(R.id.taskName);
        EditText newTaskDescription = findViewById(R.id.taskName);
        EditText newTaskLocation = findViewById(R.id.taskName);

        addTaskbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("DEBUG_ADD_TASK_BTN", "button works" );
                Intent intent = new Intent(AddTaskScreen.this, HomeScreen.class);
                intent.putExtra("NEW_TASK_NAME", newTaskName.getText().toString());
                intent.putExtra("NEW_TASK_DESCRIPTION", newTaskDescription.getText().toString());
                intent.putExtra("NEW_TASK_LOCATION", newTaskLocation.getText().toString());
                Task newTask = new Task(newTaskName.getText().toString(),
                        newTaskDescription.getText().toString(),
                        newTaskLocation.getText().toString());
                startActivity(intent);
            }

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}