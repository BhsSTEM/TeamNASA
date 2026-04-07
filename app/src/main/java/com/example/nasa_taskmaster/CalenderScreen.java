package com.example.nasa_taskmaster;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.Button;
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
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
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
        Button backBtn = findViewById(R.id.backBtnCalendar);
        Button forwardtn = findViewById(R.id.forwardBtnCalendar2);

        WindowMetrics windowMetrics =getWindow().getWindowManager().getCurrentWindowMetrics();


        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY); // Set dot color
        paint.setStyle(Paint.Style.FILL); // Fill the circle
        paint.setAntiAlias(true); // Smooth the edges


        int width =  windowMetrics.getBounds().width();
        int height =  windowMetrics.getBounds().height();
        Log.d("Width: ", "" + width);
        Log.d("Height: ", "" + height);
        int[] canvasSize = getCanvasSize(width, height);
        Bitmap bitmap = Bitmap.createBitmap(canvasSize[0], canvasSize[1], Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawIcons(canvas, calendarView, paint);
        imageView.setImageBitmap(bitmap);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                calendarView.setDate(calendar.getTimeInMillis());
                ArrayList<TaskFragment> tasksFrags = getTaskFrags(month, dayOfMonth, year);

                if(tasksFrags.size() > 0){
                    Intent intent = new Intent(CalenderScreen.this, CalendarDayView.class);
                    CalendarDayView.setTaskFrags(tasksFrags);
                    startActivity(intent);
                }


            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = LocalDate.ofEpochDay((long)(calendarView.getDate()/1000/60/60/24));
                Log.d("Old Date: ", calendarView.getDate() + "");
                localDate = localDate.minusMonths(1);
                calendarView.setDate((long)(localDate.toEpochDay() * 24 * 60 * 60 * 1000));
                Log.d("New Date: ", calendarView.getDate() + "");
                drawIcons(canvas, calendarView, paint);
                imageView.setImageBitmap(bitmap);
            }
        });

        forwardtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = LocalDate.ofEpochDay((long)(calendarView.getDate()/1000/60/60/24));
                Log.d("Old Date: ", calendarView.getDate() + "");
                localDate = localDate.plusMonths(1);
                calendarView.setDate((long)(localDate.toEpochDay() * 24 * 60 * 60 * 1000));
                Log.d("New Date: ", calendarView.getDate() + "");
                drawIcons(canvas, calendarView, paint);
                imageView.setImageBitmap(bitmap);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void drawIcons(Canvas  canvas, CalendarView calendarView, Paint paint){

        int[] date = convertToDate(calendarView.getDate());
        int firstDayofMonthWeek = getFirstDayofWeek(calendarView.getDate());
        Log.d("Get Date Raw: " , "" + calendarView.getDate());
        Log.d("Get Date New: " , "" + date[0] + " / "+ date[1] + " / "+ date[2]);
        int firstDayofMonth = getFirstDayofWeek(calendarView.getDate());
        int monthLength = geMonthLength(calendarView.getDate());
        int weeksInMonth = 5;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        /*

        canvas.drawLine((float)(0), (float)(10), (float)(canvasWidth), (float)(10), paint);
        canvas.drawLine((float)(0), (float)(canvasHeight - 10), (float)(canvasWidth), (float)(canvasHeight - 10), paint);
        canvas.drawLine((float)(canvasWidth-10), (float)(10), (float)(canvasWidth-10), (float)(canvasHeight - 10), paint);
        canvas.drawLine((float)(10), (float)(10), (float)(10), (float)(canvasHeight - 10), paint);

        for(int i = 0; i < weeksInMonth; i++){
            float y = (float)((canvasHeight * 0.3 + (canvasHeight * 0.7 / weeksInMonth) * (i+1)) - (canvasHeight * 0.7 / weeksInMonth)/4);

            canvas.drawLine((float)(0), y, (float)(canvasWidth), y, paint);
        }


         */


        /*
        canvas.drawLine((float)(0), (float)(canvasHeight * 0.4), (float)(canvasWidth), (float)(canvasHeight * 0.4), paint);
        canvas.drawLine((float)(0), (float)(canvasHeight * 0.9), (float)(canvasWidth), (float)(canvasHeight * 0.9), paint);
        canvas.drawLine((float)(canvasWidth*0.12), (float)(canvasHeight * 0.25), (float)(canvasWidth*0.12), (float)(canvasHeight * 0.9), paint);
        canvas.drawLine((float)(canvasWidth*0.88), (float)(canvasHeight * 0.25), (float)(canvasWidth*0.88), (float)(canvasHeight * 0.9), paint);


         */





        if(HomeScreen.getTaskFragments().size() > 0) {
            ArrayList<TaskFragment> tasksFrags = HomeScreen.getTaskFragments();

            for (int j = 1; j < weeksInMonth-1; j++) {
                float y = ((float) ((canvasHeight * 0.6 / weeksInMonth) * (j) + canvasHeight * 0.4));
                for (int i = 0; i < 7; i++) {
                    float x = (float) ((canvasWidth * 0.88 / 7) * (i) + canvasWidth * 0.12);

                    canvas.drawCircle(x, y, 10, paint);
                }
            }
        }
    }


    private int[] getCanvasSize(int width, int height){
        int[] output = new int[]{0,0};
        output[0] = width - 64;
        output[1] = width - 64;
        return output;
    }

    private int[] convertToDate(long date){
        int[] out = new int[3];
        LocalDate localDate = LocalDate.ofEpochDay((long)(date/1000/60/60/24));
        out[0] = localDate.getMonth().getValue();
        out[1] = localDate.getDayOfMonth();
        out[2] = localDate.getYear();
        return out;
    }

    private ArrayList<TaskFragment> getTaskFrags(int month, int dayOfMonth, int year){
        ArrayList<TaskFragment> tasksFrags = new ArrayList<>();
        String targetDate = "" + month + " - " + dayOfMonth + " - " + year;
        if( HomeScreen.getTaskFragments().size() > 0) {
            for (int i = 0; i < HomeScreen.getTaskFragments().size(); i++) {
                if (HomeScreen.getTaskFragments().get(i).getTask().compareDate(targetDate)) {
                    tasksFrags.add(HomeScreen.getTaskFragments().get(i));
                }
            }
        }

        return tasksFrags;
    }

    private int getFirstDayofWeek(long date){
        LocalDate localDate = LocalDate.ofEpochDay((long)(date/1000/60/60/24));
        localDate = localDate.minusDays(localDate.getDayOfMonth());
        int out = localDate.getDayOfWeek().getValue();
        out += 1;
        out %= 7;
        Log.d("day of year: ", out + "" );

        return out;
    }


    private int geMonthLength(long date){
        LocalDate localDate = LocalDate.ofEpochDay((long)(date/1000/60/60/24));
        return localDate.getMonth().length(localDate.isLeapYear());
    }


}