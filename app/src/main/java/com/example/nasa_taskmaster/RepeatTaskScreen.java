package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nasa_taskmaster.databinding.ActivityRepeatTaskScreenBinding;

import java.time.LocalDate;

public class RepeatTaskScreen extends AppCompatActivity {
    int[] startRange = new int[3];
    int[] endRange = new int[3];
    int amount = 0;
    boolean isYearly = false;
    boolean isweekly = false;
    boolean isMonthly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repeat_task_screen);

        Button yearlyBtn1 = findViewById(R.id.yearlyButton);
        Button montlyBtn1 = findViewById(R.id.montlyBtn);
        Button weeklyBtn1 = findViewById(R.id.weeklyBtn);
        Button goBackTOAddTaskBtn1 = findViewById(R.id.backToAddTaskBtn);
        Switch switchBtn1 = findViewById(R.id.switch1);

        CalendarView calendarView2 = findViewById(R.id.calendarView2);
        CalendarView calendarView3 = findViewById(R.id.calendarView3);
        CalendarView calendarView4 = findViewById(R.id.calendarView4);

        yearlyBtn1.setVisibility(View.GONE);
        montlyBtn1.setVisibility(View.GONE);
        weeklyBtn1.setVisibility(View.GONE);
        calendarView3.setVisibility(View.GONE);
        calendarView4.setVisibility(View.GONE);

        switchBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yearlyBtn1.setVisibility(View.VISIBLE);
                    montlyBtn1.setVisibility(View.VISIBLE);
                    weeklyBtn1.setVisibility(View.VISIBLE);
                    calendarView3.setVisibility(View.VISIBLE);
                    calendarView4.setVisibility(View.VISIBLE);
                    findViewById(R.id.repeatHeader).setVisibility(View.VISIBLE);
                    findViewById(R.id.repeatHeader1).setVisibility(View.VISIBLE);
                    findViewById(R.id.repeatHeader2).setVisibility(View.VISIBLE);

                    calendarView2.setVisibility(View.GONE);
                    findViewById(R.id.repeatHeader5).setVisibility(View.GONE);


                }else {
                    yearlyBtn1.setVisibility(View.GONE);
                    montlyBtn1.setVisibility(View.GONE);
                    weeklyBtn1.setVisibility(View.GONE);
                    calendarView3.setVisibility(View.GONE);
                    calendarView4.setVisibility(View.GONE);
                    findViewById(R.id.repeatHeader).setVisibility(View.GONE);
                    findViewById(R.id.repeatHeader1).setVisibility(View.GONE);
                    findViewById(R.id.repeatHeader2).setVisibility(View.GONE);

                    calendarView2.setVisibility(View.VISIBLE);
                    findViewById(R.id.repeatHeader5).setVisibility(View.VISIBLE);
                }
            }
        });

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                startRange = new int[] {month, dayOfMonth, year};
                endRange = new int[] {month, dayOfMonth, year};
                amount = 0;
            }
        });


        calendarView3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                startRange = new int[] {month, dayOfMonth, year};
            }
        });

        calendarView4.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                endRange = new int[] {month, dayOfMonth, year};
            }
        });

        yearlyBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYearly = true;
            }
        });
        montlyBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMonthly = true;
            }
        });
        weeklyBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isweekly = true;
            }
        });

        goBackTOAddTaskBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isweekly){
                    int days = (int)(convertToDays(startRange) - convertToDays(endRange));
                    int weeks = (int)(Math.floor(days/7.0));
                    amount = weeks;
                }else if(isMonthly){
                    LocalDate startLocal1 = LocalDate.of(startRange[2], startRange[0] , startRange[1]);
                    LocalDate endLocal1 = LocalDate.of(startRange[2], startRange[0] , startRange[1]);


                    int days = (int)(startLocal1.datesUntil(endLocal1).count());
                    int weeks = (int)(Math.floor(days/7.0));
                    amount = weeks;
                }else{
                    amount = 0;
                }
                if(startRange != null && endRange != null) {
                    AddTaskScreen.setRange(startRange, endRange, amount);
                    Intent intent = new Intent(RepeatTaskScreen.this, AddTaskScreen.class);
                    startActivity(intent);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private long convertToDays(int[] date){
        LocalDate localDate = LocalDate.of(date[2], date[0], date[1]);
        return (int)(localDate.toEpochDay());
    }
}