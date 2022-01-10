package com.example.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class changepassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    Button b2;
    EditText tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        b2=(Button) findViewById(R.id.submit);
        tv3=(EditText) findViewById(R.id.newpassword);
        tv4=(EditText) findViewById(R.id.again);

            b2.setOnClickListener(view -> {
                String password = tv3.getText().toString();
                String confirmPassword = tv4.getText().toString();
                if (!TextUtils.isEmpty(password) & !TextUtils.isEmpty(confirmPassword)) {
                    if (password.equals(confirmPassword)) {
                        mAuth.getCurrentUser().updatePassword(password).addOnCompleteListener(task -> {

                            tv3.setText("");
                            tv4.setText("");
                        });
                        sendTologin();
                    }
                    else {
                        //不相符的提示
                        Toast.makeText(changepassword.this, "Confirm Password and Password Field doesn't match!",Toast.LENGTH_LONG).show();
                    }


                }
            });
    }
    private void sendTologin(){
        Intent loginIntent = new Intent(changepassword.this,login.class);
        startActivity(loginIntent);
    }
}