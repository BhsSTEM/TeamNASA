package com.example.nasa_taskmaster;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CalendarDayAdapter extends RecyclerView.Adapter<CalendarDayAdapter.MyViewHolder>{


    private List<TaskFragment> taskFragmentList;

    public CalendarDayAdapter(List<TaskFragment> dataList){
        this.taskFragmentList = dataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;
        public Button viewMoreBtn;


        public MyViewHolder(View itemView){
            super(itemView);
            taskName = itemView.findViewById(R.id.taskNameView);
            viewMoreBtn = itemView.findViewById(R.id.ViewMoreButton);
        }
    }

    @NonNull
    @Override
    public CalendarDayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new CalendarDayAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarDayAdapter.MyViewHolder holder, int position) {
        TaskFragment taskItem = taskFragmentList.get(position);
        holder.taskName.setText(taskItem.getTask().getTaskName());
        holder.viewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("View More", "works");
                Intent intent = new Intent(v.getContext(), TaskDetailScreen.class);
                intent.putExtra("TaskInfo", taskItem.getTask().getTaskInfo());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskFragmentList.size();
    }


}