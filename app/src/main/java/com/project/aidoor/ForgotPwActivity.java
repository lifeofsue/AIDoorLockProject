package com.project.aidoor;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPwActivity extends AppCompatActivity {

    EditText mEmailText;
    Button mConfirmBtn;

    // firebase access
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Intent intent = getIntent();
    String emailLink = intent.getData().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);

        // 화면의 Confirm 버튼 클릭 시, login check email 화면으로 전환
        mConfirmBtn = findViewById(R.id.Btn_Forgotpassword);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPwActivity.this, LoginCheckEmailActivity.class);
                startActivity(intent);
                //Toast.makeText(LoginActivity.this, "2클릭인식ㅇㅋ", Toast.LENGTH_SHORT).show();
            }
        });

        // 이메일로 인증 링크 보내기
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        // U다시 리디렉션할 URL입니다. 이에 대한 도메인(www.example.com)입니다.
                        // URL은 Firebase 콘솔에서 화이트리스트에 있어야 합니다.
                        .setUrl("https://aidoor-project.firebaseapp.com/__/auth/action?mode=action&oobCode=code")
                        // This must be true
                        .setHandleCodeInApp(true)
                        .setIOSBundleId("com.example.ios")
                        .setAndroidPackageName(
                                "com.project.aidoor",
                                true, /* installIfNotAvailable */
                                "12"    /* minimumVersion */)
                        .build();

        //FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(String.valueOf(mEmailText), actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });

        mEmailText = findViewById(R.id.Edit_Email);
        String email = mEmailText.getText().toString().trim();

        // Construct the email link credential from the current URL.
        AuthCredential credential =
                EmailAuthProvider.getCredentialWithLink(email, emailLink);

        // Re-authenticate the user with this credential.
        auth.getCurrentUser().reauthenticateAndRetrieveData(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                            Log.e(TAG, "재인증 오류", task.getException());
                        }
                    }
                });


    }
}