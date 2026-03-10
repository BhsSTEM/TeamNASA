package com.example.nasa_taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.MyViewHolder>{


    private List<TaskFragment> taskFragmentList;

    public HomeScreenAdapter(List<TaskFragment> dataList){
        this.taskFragmentList = dataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TaskFragment taskFrag;

        public MyViewHolder(View itemView){
            super(itemView);
        }
    }

    @NonNull
    @Override
    public HomeScreenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new HomeScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapter.MyViewHolder holder, int position) {
        TaskFragment taskItem = taskFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return taskFragmentList.size();
    }
}
