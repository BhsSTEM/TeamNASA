package com.example.nasa_taskmaster;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class taskMoreAdapter extends RecyclerView.Adapter<taskMoreAdapter.MyViewHolder>{


    private List<TaskFragment> taskFragmentList;

    public taskMoreAdapter(List<TaskFragment> dataList){
        this.taskFragmentList = dataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;


        public MyViewHolder(View itemView){
            super(itemView);
            taskName = itemView.findViewById(R.id.taskNameView);
        }
    }

    @NonNull
    @Override
    public taskMoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull taskMoreAdapter.MyViewHolder holder, int position) {
        TaskFragment taskItem = taskFragmentList.get(position);
        taskItem.setTask(TestTaskData.testTask1);
    }

    @Override
    public int getItemCount() {
        return taskFragmentList.size();
    }
}
