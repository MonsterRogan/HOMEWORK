package com.example.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class login extends AppCompatActivity {

    Button b1;
    EditText tv1,tv2;
    FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tv1= (EditText)findViewById(R.id.email);
        tv2= (EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        b1.setOnClickListener(view -> {
            String loginEmail = tv1.getText().toString();
            String loginPass = tv2.getText().toString();

            if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
                mAuth.signInWithEmailAndPassword(loginEmail,loginPass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        sendToMain();
                    }else {
                        String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(login.this , "Error : " + errorMessage , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void sendToMain() {
        Intent mainIntent = new Intent(login.this,MainActivity.class);
        startActivity(mainIntent);

    }
}
