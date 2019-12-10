package com.example.kitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.my_model.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.FederatedSignInActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText emailRegister, passwordRegister;
    Button register;
    TextView login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailRegister = findViewById(R.id.emailregister);
        passwordRegister = findViewById(R.id.passwordregister);
        register = findViewById(R.id.buttonregister);
        login = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        dbref = FirebaseDatabase.getInstance().getReference();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailRegister.getText().toString().trim();
                final String password = passwordRegister.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailRegister.setError("Email Belum Terisi !");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordRegister.setError("Password Belum Terisi !");
                    return;
                }
                if (password.length() < 6) {
                    passwordRegister.setError(("Password Minimal Berisikan 6 Karakter !"));
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addData(new LoginModel(email,password));
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        } else {
                            Toast.makeText(RegisterActivity.this,"Gagal Register"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            private void addData(LoginModel register){
                dbref.child("Users").push().setValue(register).addOnSuccessListener(RegisterActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

}
