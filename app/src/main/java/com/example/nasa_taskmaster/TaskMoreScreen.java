package com.example.nasa_taskmaster;

import static com.example.nasa_taskmaster.HomeScreen.taskFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskMoreScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewPast;
    private ArrayList<TaskFragment> todoTaskFrags = new ArrayList<>();
    private ArrayList<TaskFragment> doneTaskFrags = new ArrayList<>();
    private taskMoreAdapter adapter;
    private taskMoreAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_more_screen);

        if(taskFragments.size() > 0) {
            for (int i = 0; i < taskFragments.size(); i++){
                if(taskFragments.get(i).getTask().isComplete()){
                    doneTaskFrags.add(taskFragments.get(i));
                }else{
                    todoTaskFrags.add(taskFragments.get(i));
                }
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPast = findViewById(R.id.recyclerviewPast);
        recyclerViewPast.setLayoutManager(new LinearLayoutManager(this));



        adapter = new taskMoreAdapter(todoTaskFrags);
        adapter2 = new taskMoreAdapter(doneTaskFrags);
        recyclerView.setAdapter(adapter);
        recyclerViewPast.setAdapter(adapter2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}