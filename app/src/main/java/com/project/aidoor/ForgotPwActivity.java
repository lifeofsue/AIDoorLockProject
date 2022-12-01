package com.project.aidoor;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPwActivity extends AppCompatActivity {

    EditText mUserEmail;
    Button mConfirmBtn;
    TextView mResendEmail;

    // firebase access
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Intent intent = getIntent();
    String userEmail = intent.getData().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);

        // 입력한 이메일로 인증 메일(+링크) 보내기
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
//                        .setUrl("https://aidoor-project.firebaseapp.com/__/auth/action?mode=action&oobCode=code")
                          .setUrl("https://aidoor.page.link/forgotpw")
                        // This must be true
                        .setHandleCodeInApp(true)
                        //.setIOSBundleId("com.example.ios")
                        .setAndroidPackageName(
                                "com.project.AiDoor",
                                true, /* installIfNotAvailable */
                                "12"    /* minimumVersion */)
                        .build();

        // 사용자가 동일한 기기에서 이메일 로그인을 완료할 경우를 대비하여 사용자의 이메일을 저장
        mUserEmail = findViewById(R.id.Edit_UserEmail);
        String email = mUserEmail.getText().toString().trim();
//        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPwActivity.this, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });

        // 입력한 이메일이 가입할 때 적었던 이메일과 같은지 확인
        if (auth.isSignInWithEmailLink(userEmail)) {

            // The client SDK will parse the code from the link for you.
            auth.signInWithEmailLink(email, userEmail)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Successfully signed in with email link!");
                                AuthResult result = task.getResult();
                                // You can access the new user via result.getUser()
                                // Additional user info profile *not* available via:
                                // result.getAdditionalUserInfo().getProfile() == null
                                // You can check if the user is new or existing:
                                // result.getAdditionalUserInfo().isNewUser()
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if(user.isEmailVerified()){
                                    Toast.makeText(ForgotPwActivity.this, "인증된 이메일로 로그인 완료", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent( ForgotPwActivity.this, ChangePwActivity.class));
                                } else{
                                    user.sendEmailVerification();
                                }
                            } else {
                                Log.e(TAG, "Error signing in with email link", task.getException());
                            }
                        }
                    });
        }
    }
}