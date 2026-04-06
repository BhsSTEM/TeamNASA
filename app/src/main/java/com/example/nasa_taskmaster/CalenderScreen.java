package com.example.nasa_taskmaster;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CalenderScreen extends AppCompatActivity {

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

        CalendarView calendarView = findViewById(R.id.calendarView);
        ImageView imageView = findViewById(R.id.imageView2);

        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY); // Set dot color
        paint.setStyle(Paint.Style.FILL); // Fill the circle
        paint.setAntiAlias(true); // Smooth the edges

        Bitmap bitmap = Bitmap.createBitmap(300, 190, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawIcons(canvas, calendarView, paint);
        imageView.setImageBitmap(bitmap);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
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

    private void drawIcons(Canvas  canvas, CalendarView calendarView, Paint paint){
        LocalDate localDate = LocalDate.now();

        int firstDayofMonth = 3;
        int weeksInMonth = 4;

        for(int i = 0; i < 7 - firstDayofMonth; i++){
            canvas.drawCircle((float)((canvas.getWidth()/7) * (i + firstDayofMonth + 0.6)), ((float)((canvas.getHeight() - 1)/weeksInMonth)), 5, paint);
        }
        for(int j = 1; j < weeksInMonth; j++){
            for(int i = 0; i < 7; i++){
                canvas.drawCircle((float)((canvas.getWidth()/7) * (i + 0.6)), ((float)((canvas.getHeight() - 1)/weeksInMonth)), 5, paint);

            }
        }
    }



}