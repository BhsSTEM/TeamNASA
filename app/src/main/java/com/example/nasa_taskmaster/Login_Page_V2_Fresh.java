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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page_V2_Fresh extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page_v2_fresh);
        emailEditText = findViewById(R.id.editTextText2);
        passwordEditText = findViewById(R.id.editTextText3);
        loginButton = findViewById(R.id.logb);
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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //Fail
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_Page_V2_Fresh.this, "Authentication failed: " + task.getException().getMessage(),

                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void handleLoginClick() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString(); // Using username as password

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        login(email, password);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(Login_Page_V2_Fresh.this, HomeScreen.class); // swap in your actual main activity
            startActivity(intent);
            finish();
        }
    }

    public void bringToRegistry(View view) {
        Intent intent = new Intent(this, RegisterAccountPage.class);
        startActivity(intent);
    }
}