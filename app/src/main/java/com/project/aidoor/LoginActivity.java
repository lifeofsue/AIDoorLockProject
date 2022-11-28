package com.project.aidoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button mLoginBtn;
    TextView mSwitchSignUp, mForgotPassword;
    EditText mEmailText, mPwdText;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth =  FirebaseAuth.getInstance();

        mSwitchSignUp = findViewById(R.id.switchsignup);
        mLoginBtn = findViewById(R.id.Btn_Signin_Continue);
        mForgotPassword = findViewById(R.id.ForgotPassword);
        mEmailText = findViewById(R.id.Edit_Email);
        mPwdText = findViewById(R.id.Edit_Password);

        // Forgot Password? 텍스트 클릭 시
        mForgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPwActivity.class));
            }
        });

        // Don't have account? Sign up 텍스트 클릭 시 (=Sign Up 화면의 버튼과 같은 동작을 하도록 설정)
        mSwitchSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // 로그인 버튼 클릭 시
        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPwdText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    // home.xml로 바꿔야함!!!!!!!!!!!!!!!!!!!
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "이메일 혹은 비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}