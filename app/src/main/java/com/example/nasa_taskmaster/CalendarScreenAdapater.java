package com.example.nasa_taskmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarScreenAdapater extends BaseAdapter {
    private ArrayList<Date> days = new ArrayList<>();
    private LayoutInflater inflater;
    public CalendarScreenAdapater(Context context, ArrayList<Date> days){
        this.days = days;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_calender_screen, parent, false);
        }


        Date date = days.get(position);

        // 3. Update the UI (e.g., set the day number)
        CalendarView calendar = convertView.findViewById(R.id.calendarView);

        return convertView;
    }
}
