package com.example.nasa_taskmaster;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


public class Settings extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Luqol q = new Luqol();
    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        //if (savedInstanceState == null) {
        //    getSupportFragmentManager()
        //            .beginTransaction()
        //            .replace(R.id.settings, new SettingsFragment())
        //            .commit();
        //}
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        logoutButton = findViewById(R.id.button4);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.logout();
                Intent intent = new Intent(Settings.this, Login_Page_V2_Fresh.class);
                startActivity(intent);
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    public void logout() {
        mAuth.getInstance()
                .signOut();
        Toast.makeText(this, "User has been logged out.", Toast.LENGTH_SHORT).show();
    }
}