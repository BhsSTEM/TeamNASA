package com.example.nasa_taskmaster;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterAccountPage extends AppCompatActivity {

    private Firebase firebaser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //FirebaseFirestore db = FirebaseFirestore.getInstance(FirebaseApp.getInstance(), "yoshi1");
    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        EdgeToEdge.enable(this);
        Log.d("DEBUG", "RegisterAccountPage loaded!");
        setContentView(R.layout.activity_register_account_page);
        emailEditText = findViewById(R.id.editTextText21);
        passwordEditText = findViewById(R.id.editTextText31);
        regButton = findViewById(R.id.button21);
        if (regButton == null) {
            Log.e("DEBUG", "regButton is null! Check setContentView layout.");
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegClick();
            }
        });
    }
    //
    // HEY DOOFUS!!! DON'T FORGET THIS!!!!!!!!
    //
    public void bringToLogin(View view) {
        Intent intent = new Intent(this, Login_Page_V2_Fresh.class);
        startActivity(intent);
    }
    private void handleRegClick() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString(); // Using username as password

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        register(email, password);
    }
    private void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, user.toString());
                            updateUI(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterAccountPage.this, "Authentication failed."  + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser unformattedUser) {
        Map<String, String> user = new HashMap<>();
        user.put("uid", unformattedUser.getUid());
        user.put("email", unformattedUser.getEmail());
        Log.d(TAG, "User created.");
        db.collection("uzi1")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Log.d(TAG, db.toString() + "I am a user :)");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}





































//OwO? What's this?