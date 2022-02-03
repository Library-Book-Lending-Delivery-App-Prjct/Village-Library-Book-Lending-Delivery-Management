package com.example.booklendingdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText mFullname,mEmail,mPhone,mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        mFullname = findViewById(R.id.editTextName);
        mEmail = findViewById(R.id.editTextEmail);
        mPhone = findViewById(R.id.editTextMobile);
        mPassword = findViewById(R.id.editTextPassword);
        mRegisterBtn = findViewById(R.id.cirRegisterButton);
        mLoginBtn = findViewById(R.id.LoginBtn);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mFullname.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (name.isEmpty()){
                    mFullname.setError("Provide your FullName");
                    mFullname.requestFocus();
                }else if (name.matches(".*[0-9].*")){
                    mFullname.setError("Name does not contain a number");
                    mFullname.requestFocus();
                }else if (email.isEmpty()) {
                    mEmail.setError("Emailid is required!");
                    mEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mEmail.setError("Enter a valid Emailid");
                    mEmail.requestFocus();
                }else if(phone.isEmpty()){
                    mPhone.setError("Phone number is required!");
                    mPhone.requestFocus();
                }else if (phone.length()!=10){
                    mPhone.setError("Enter valid Phone number");
                    mPhone.requestFocus();
                }else if (password.isEmpty()) {
                    mPassword.setError("Password is required!");
                    mPassword.requestFocus();
                }else if(password.length() < 6){
                    mPassword.setError("Enter valid Password atleat 6 charecters");
                    mPassword.requestFocus();
                }

                else if(name.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(name.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty())) {
                    // register the user in firebase
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"User Created.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }
    public void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}