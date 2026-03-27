package com.example.nasa_taskmaster;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.autofill.AutofillValue;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.rpc.context.AttributeContext;

import java.util.ArrayList;

public class CalenderScreen extends AppCompatActivity {
    ArrayList<View> calendarChild = new ArrayList<>();
    Drawable icon;
    Drawable calendarForeground;
    Bitmap combinedBitmap;
    Canvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calender_screen);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navigationBar6, new Navigation_Bar())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.topNavBar8, new TopNavBar())
                    .commit();
        }
        icon = ResourcesCompat.getDrawable(this.getResources(), R.drawable.baseline_radio_button_checked_24, getTheme());

        CalendarView calendarView = findViewById(R.id.calendarView);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Log.d("Child Count: ","" +  view.getForeground().getIntrinsicHeight());
                ArrayList<TaskFragment> tasksFrags = new ArrayList<>();
                String targetDate = "" + month + " - " + dayOfMonth + " - " + year;
                if( HomeScreen.getTaskFragments().size() > 0) {
                    for (int i = 0; i < HomeScreen.getTaskFragments().size(); i++) {
                        if (HomeScreen.getTaskFragments().get(i).getTask().compareDate(targetDate)) {
                            tasksFrags.add(HomeScreen.getTaskFragments().get(i));
                        }
                    }
                }

                if(tasksFrags.size() > 0){
                    Intent intent = new Intent(CalenderScreen.this, CalendarDayView.class);
                    CalendarDayView.setTaskFrags(tasksFrags);
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
}