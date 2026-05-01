package com.example.nasa_taskmaster;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Login_Page_V2_Fresh extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Firebase firebaser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //FirebaseFirestore db = FirebaseFirestore.getInstance(FirebaseApp.getInstance(), "yoshi1");
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    Button logoutButton;
    private Luqol q = new Luqol();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        q.log("Luqol initiated.");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page_v2_fresh);
        emailEditText = findViewById(R.id.editTextText2);
        passwordEditText = findViewById(R.id.editTextText3);
        loginButton = findViewById(R.id.logb);
        logoutButton = findViewById(R.id.logb2);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {
                Log.d("DEBUG", "Register button clicked!");
                Toast.makeText(Login_Page_V2_Fresh.this, "Switching to Register Screen", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Page_V2_Fresh.this, RegisterAccountPage.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginClick();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // :3
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }
    private void login(String email, String password) {
        q.log("Made it to login.");
        q.warn("THIS IS AN INSECURE DEBUG BUILD. DO NOT USE.");
        q.log(email);
        q.log(password);
        q.startProcess("Login");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(Login_Page_V2_Fresh.this, HomeScreen.class);
                            startActivity(intent);
                            q.endProcess("Login");
                            finish(); // Finish activity only on success
                        } else {
                            //Fail
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            q.interruptProcess("Login");
                            q.warn(email);
                            q.warn(password);
                            Toast.makeText(Login_Page_V2_Fresh.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void handleLoginClick() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        login(email, password);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //Intent intent = new Intent(Login_Page_V2_Fresh.this, HomeScreen.class); // swap in your actual main activity
            //startActivity(intent);
            //finish();
        }
    }

    public void bringToRegistry(View view) {
        Intent intent = new Intent(this, RegisterAccountPage.class);
        startActivity(intent);
    }

    public void logout() {
        mAuth.getInstance()
                 .signOut();
        Toast.makeText(this, "User has been logged out.", Toast.LENGTH_SHORT).show();
    }
}
