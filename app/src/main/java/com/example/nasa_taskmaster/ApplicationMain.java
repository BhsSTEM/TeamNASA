package com.example.nasa_taskmaster;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

public class ApplicationMain extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDark = prefs.getBoolean("dark_mode", false);

        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }
}