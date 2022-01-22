package com.example.booklendingdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView forgotpassword;
    Button Login;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        Login = findViewById(R.id.cirLoginButton);
        forgotpassword = findViewById(R.id.forgotpass);
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "User logged in", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(LoginActivity.this, DashBoard.class);
                    startActivity(I);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                String editTextEmail = email.getText().toString();
                String editTextPassword = password.getText().toString();
                if (editTextEmail.isEmpty()) {
                    email.setError("Provide your Email first!");
                    email.requestFocus();
                } else if (editTextPassword.isEmpty()) {
                    password.setError("Enter Password!");
                    password.requestFocus();
                } else if (editTextEmail.isEmpty() && editTextEmail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(editTextEmail.isEmpty() && editTextPassword.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(editTextEmail, editTextPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Failed Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Redirecting...!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DashBoard.class));
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void onLoginClick(View view){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}
