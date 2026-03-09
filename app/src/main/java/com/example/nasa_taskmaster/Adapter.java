package com.example.nasa_taskmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Equipment> equipmentList;
    private Context context;

    // Constructor to receive the list and context
    public Adapter(ArrayList<Equipment> equipmentList, Context context) {
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Equipment equipment = equipmentList.get(position);

        String displayText = equipment.getName();
        holder.equipmentButton.setText(displayText);

        holder.equipmentButton.setOnClickListener(v -> {
            // Navigation logic
        });
    }

    @Override
    public int getItemCount() {
        return equipmentList != null ? equipmentList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button equipmentButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentButton = itemView.findViewById(R.id.equipmentButtonForRecyclerView);
        }
    }
}
