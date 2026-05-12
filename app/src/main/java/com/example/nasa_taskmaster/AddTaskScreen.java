package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
    static String dueDate = "None";
    static String newTaskStringName = "Enter Task Name";
    private static String[] locationNames = {};
    private static ArrayList<Locations> locationList;
    private static Locations selectedLocation = null;
    private static int[] startRange;
    private static int[] endRange;
    private static int amountOfTasks = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task_screen);


       Button addTaskbtn = findViewById(R.id.addTaskBtn);
       Button dueDateButton = findViewById(R.id.dueDateBtn);


        locationList = Map.getLocations();
        if(locationList.isEmpty())
        {
            //so there is no null errors if the user currently has no locations
            locationNames = new String[1+1];
            locationNames[0] = "No Location Selected";
            locationNames[1] = "Add New Location";
        }
        else //if(!(locationList.isEmpty()))
        {
            locationNames = new String[locationList.size()+1];
            for (int i = 0; i < locationList.size(); i++) {
                locationNames[i] = locationList.get(i).getName();
            }
            locationNames[locationList.size()] = "Add New Location";
        }



        EditText newTaskName = findViewById(R.id.taskName);
      EditText newTaskDescription = findViewById(R.id.taskDescript);
        Spinner spinner = findViewById(R.id.addLocationSpinner2);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        newTaskName.setText(newTaskStringName);

        newTaskName.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                newTaskStringName = newTaskName.getText().toString();
                return false;
            }
        });


        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskScreen.this, RepeatTaskScreen.class);
                startActivity(intent);
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
                String  taskLocationName = "";
                double longitude = 0;
                double latitude = 0;
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
                        "Yours",
                        taskLocationName,
                        longitude,
                        latitude,
                        taskDeadLine,
                        taskstartDate, "0");
                createTask(newTask, startRange, endRange, amountOfTasks);



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

    public static void setRange(int[] startRange, int[] endRange, int amount){
        startRange = startRange;
        endRange = endRange;
        amountOfTasks = amount;
    }

    public static void clearTask(){
       dueDate = "None";
       locationNames = new String[]{};
        locationList = new ArrayList<>();
        selectedLocation = null;
        startRange = new int[3];
        endRange  = new int[3];
        amountOfTasks = 0;
    }

    public void createTask(Task task, int[] start, int[] end, int amount){
        if(amount <= 1){
            Log.d("Only added one task", "");
            task.setTaskDueDate(start);
            HomeScreen.addTasktoList(task);
            HomeScreen.user.addTask(task);
        }else{
            int days = (int)(convertToDays(start) - convertToDays(end));
            for(int i = 0; i < amount; i++){
                Task newTask = task;
                newTask.setTaskDueDate(convertLocalToArray(LocalDate.of(start[2], start[0], start[1]).plusDays(days * (i/amount))));
                HomeScreen.addTasktoList(newTask);
                HomeScreen.user.addTask(newTask);
            }
            Log.d("Added task", amount + "");

        }
    }

    private long convertToDays(int[] date){
        LocalDate localDate = LocalDate.of(date[2], date[0], date[1]);
        return (int)(localDate.toEpochDay());
    }

    public int[] convertLocalToArray(LocalDate localDate){
        int[] out = new int[3];
        out[0] = localDate.getMonth().getValue();
        out[1] = localDate.getDayOfMonth();
        out[2] = localDate.getYear();
        return out;
    }

}