package com.example.nasa_taskmaster;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nasa_taskmaster.databinding.ActivityRepeatTaskScreenBinding;

public class RepeatTaskScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repeat_task_screen);

        Button startRangeBtn1 = findViewById(R.id.startRangeBtn);
        Button endRangeBtn1 = findViewById(R.id.setEndDateBtn);
        Button yearlyBtn1 = findViewById(R.id.yearlyBtn);
        Button montlyBtn1 = findViewById(R.id.monthlyBtn);
        Button weeklyBtn1 = findViewById(R.id.weeklyBtn);

        CalendarView calendarView2 = findViewById(R.id.calendarView3);
        CalendarView calendarView3 = findViewById(R.id.calendarView4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}