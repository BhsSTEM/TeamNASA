package com.example.nasa_taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    Button confirm;
    EditText currentPasswordEditText;
    EditText newPasswordText;
    EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Luqol q = new Luqol();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            confirm = findViewById(R.id.button33);
            currentPasswordEditText = findViewById(R.id.editTextTextPassword);
            newPasswordText = findViewById(R.id.editTextTextPassword2);
            emailText = findViewById(R.id.editTextTextPassword4);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View ve) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String currentPassword = currentPasswordEditText.getText().toString().trim();
                    String newPassword = newPasswordText.getText().toString().trim();
                    String emailOf = emailText.getText().toString().trim();
                    q.log("Current: " + currentPassword);
                    q.log("New: " + newPassword);
                    q.log("Email: " + emailOf);
// Validate first
                    if (currentPassword.isEmpty() || newPassword.isEmpty()) {
                        q.error("Not all fields filled!");
                        return;
                    }
                    if (newPassword.length() < 6) {
                        q.error("Password must be at least 6 characters.");
                        return;
                    }

// Re-auth with CURRENT password
                    AuthCredential credential = EmailAuthProvider.getCredential(emailOf, currentPassword);

                    user.reauthenticate(credential)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // Update to NEW password
                                    user.updatePassword(newPassword)
                                            .addOnCompleteListener(taske -> {
                                                if (taske.isSuccessful()) {
                                                    q.log("Password updated successfully!");
                                                    Intent intent = new Intent(ChangePassword.this, HomeScreen.class);
                                                    startActivity(intent);
                                                } else {
                                                    String error = taske.getException() != null
                                                            ? taske.getException().getMessage()
                                                            : "Unknown error";
                                                    q.error("Error: " + error);
                                                }
                                            });
                                } else {
                                    q.error("Incorrect credentials.");
                                }
                            });
                }
            });
            return insets;
        });
    }
}