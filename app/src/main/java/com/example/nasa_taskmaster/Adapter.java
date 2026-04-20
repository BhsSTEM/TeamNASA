package com.example.nasa_taskmaster;

import android.content.Context;
import android.content.Intent;
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

        String equipmentName = equipment.getName();
        String equipmentStatus = equipment.getStatus();
        int equipmentYear = equipment.getYear();
        String equipmentDescription = equipment.getDescription();

        String locationName = "No Location";
        if(!(equipment.getLocation() == null))
        {
            Locations location = equipment.getLocation();
            locationName = location.getName();
        }

        String finalLocation = locationName;

        holder.equipmentButton.setText(equipmentName);

        holder.equipmentButton.setOnClickListener(v -> {
           Intent intent = new Intent(context, EquipmentDetails.class);
           intent.putExtra("equipmentName", equipmentName);
           intent.putExtra("equipmentStatus", equipmentStatus);
           intent.putExtra("equipmentYear", equipmentYear);
           intent.putExtra("equipmentDescription", equipmentDescription);
           intent.putExtra("location", finalLocation);
           context.startActivity(intent);
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
