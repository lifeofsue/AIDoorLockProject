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
    EditText mEmailText, mPwdText;
    private FirebaseAuth firebaseAuth;
    TextView mSwichLogUp, mSwichForgotPw;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 화면 하단의 Sign Up 텍스트뷰 클릭 시, Sign in 화면으로 전환
        mSwichLogUp = findViewById(R.id.switchsignup);
        mSwichLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                //Toast.makeText(LoginActivity.this, "2클릭인식ㅇㅋ", Toast.LENGTH_SHORT).show();
            }
        });

        // ForgotPwActivity 완성하고 수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // 화면 중간의 Forgot password? 텍스트뷰 클릭 시, forgotpass 화면으로 전환
        mSwichForgotPw = findViewById(R.id.switchforgotpw);
        mSwichForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, ForgotPwActivity.class);
                startActivity(intent2);
                //Toast.makeText(LoginActivity.this, "3클릭인식ㅇㅋ", Toast.LENGTH_SHORT).show();
            }
        });

        // firebase access
        firebaseAuth =  FirebaseAuth.getInstance();

        // 로그인 기능 구현
        mLoginBtn = findViewById(R.id.Btn_Signin_Continue);
        mEmailText = findViewById(R.id.Edit_SignIn_Email);
        mPwdText = findViewById(R.id.Edit_SignIn_Password);

        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPwdText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Toast.makeText(LoginActivity.this, "진입", Toast.LENGTH_SHORT).show();
                                if(task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "이메일 혹은 비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                }
                            }
                        });
            }
        });
    }
}