package com.example.nasa_taskmaster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarDayView extends AppCompatActivity {
    static ArrayList<TaskFragment> taskFrags = new ArrayList<>();

    private RecyclerView taskRecycler;
    private CalendarDayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar_day_view);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navigationBar7, new Navigation_Bar())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.topNavBar7, new TopNavBar())
                    .commit();

        }

        taskRecycler = findViewById(R.id.taskRecyclerView);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CalendarDayAdapter(taskFrags);
        taskRecycler.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static void setTaskFrags(ArrayList<TaskFragment> newFrags){
        taskFrags = newFrags;
    }
}