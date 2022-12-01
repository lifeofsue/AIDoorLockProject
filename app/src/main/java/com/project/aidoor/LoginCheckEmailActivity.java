package com.project.aidoor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginCheckEmailActivity extends AppCompatActivity {

    Button mCheckEmailBtn;
    TextView mResendEmail;

    // firebase access
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Intent intent = getIntent();
    String userEmail = intent.getData().toString();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkemail);

        mCheckEmailBtn = findViewById(R.id.Btn_CheckEmail);
        mResendEmail = findViewById(R.id.ResendEmail);


    }





}

