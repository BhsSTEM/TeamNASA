package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddTaskScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String dueDate = "None";
    private String[] locationNames = {};
    private ArrayList<Locations> locationList;
    private Locations selectedLocation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task_screen);

        CalendarView calendarView = findViewById(R.id.calendarView2);
       calendarView.setVisibility(View.GONE);

       Button addTaskbtn = findViewById(R.id.addTaskBtn);
       Button dueDateButton = findViewById(R.id.dueDateBtn);

       EditText newTaskName = findViewById(R.id.taskName);
      EditText newTaskDescription = findViewById(R.id.taskDescript);
        Spinner spinner = findViewById(R.id.addLocationSpinner2);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
            }
        });



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dueDate = "" + month + " - " + dayOfMonth + " - " + year;
            }
        });
        addTaskbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.d("DEBUG_ADD_TASK_BTN", "button works" );
                Intent intent = new Intent(AddTaskScreen.this, HomeScreen.class);
                intent.putExtra("NEW_TASK_NAME", newTaskName.getText().toString());
                intent.putExtra("NEW_TASK_DESCRIPTION", newTaskDescription.getText().toString());
                intent.putExtra("NEW_TASK_LOCATION", "");




                Intent intent1 = new Intent(AddTaskScreen.this, HomeScreen.class);
                String  taskName = newTaskName.getText().toString();
                String  taskDescription = newTaskDescription.getText().toString();
                String  taskLocation = "";
                String  taskDeadLine = dueDate;
                LocalDate localDate = LocalDate.now();
                String  taskstartDate = "";
                int dayNum = localDate.getDayOfMonth();
                if(dayNum%10 == 1 && dayNum != 11 ){
                    taskstartDate ="" +  localDate.getMonth() + " " +dayNum + "st, " + localDate.getYear() ;
                }else if(dayNum%10  == 2 && dayNum != 12){
                    taskstartDate ="" +  localDate.getMonth() + " " +dayNum + "nd, " + localDate.getYear() ;
                }else if(dayNum%10  == 3 && dayNum != 13){
                    taskstartDate ="" +  localDate.getMonth() + " " +dayNum+ "rd, " + localDate.getYear() ;
                }else{
                    taskstartDate ="" +  localDate.getMonth() + " " +dayNum+ "th, " + localDate.getYear() ;
                }
                Task newTask = new Task(taskName,
                        taskDescription,
                        HomeScreen.user.getUsername(),
                        taskLocation,
                        taskDeadLine,
                        taskstartDate, "0");
                HomeScreen.addTasktoList(newTask);
                HomeScreen.user.addTask(newTask);
                startActivity(intent1);
            }


        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!locationNames[position].equals("No Location Selected") && !locationNames[position].equals("Add New Location"))
        {
            selectedLocation = locationList.get(position);
        }

        if(locationNames[position].equals("Add New Location")){
            Intent intent = new Intent(this, Map.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedLocation = null;
    }
}