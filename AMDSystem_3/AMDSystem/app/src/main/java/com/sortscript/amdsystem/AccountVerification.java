package com.sortscript.amdsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AccountVerification extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText num1, num2, num3, num4, num5, num6;
    UserModel userModel;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("sampleObj");
        String phnNumb = userModel.getUserPhone();
        String phone = "+"+phnNumb;
        Toast.makeText(this, "" + phone, Toast.LENGTH_SHORT).show();
        sendVerificationCode(phone);
        // initializing variables for button and Edittext.
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);

    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userModel.getUserEmail(), userModel.getUserPassword()).addOnCompleteListener(task1 -> {

                            if (task1.isSuccessful()) {
                                Toast.makeText(AccountVerification.this, "Verified Successfully", Toast.LENGTH_SHORT).show();

                                DatabaseReference reference =  FirebaseDatabase.getInstance().getReference().child("Universities").child("UsersId");
                                HashMap<String, String> map = new HashMap<>();
                                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                map.put("UserId", userId);
                                map.put("Email", userModel.getUserEmail());
                                map.put("Password", userModel.getUserPassword());
                                map.put("UserName", userModel.getUserName());
                                map.put("PhoneNum", userModel.getUserPhone());
                                reference.child(userId).setValue(map);
                            } else {
                                Toast.makeText(AccountVerification.this, task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        });


                    } else {
                        Toast.makeText(AccountVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void sendVerificationCode(String number) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)         // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)         // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(AccountVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }

    public void VerificationDone(View view) {
        String num1Val = num1.getText().toString();
        String num2Val = num2.getText().toString();
        String num3Val = num3.getText().toString();
        String num4Val = num4.getText().toString();
        String num5Val = num5.getText().toString();
        String num6Val = num6.getText().toString();
        String concat = num1Val + num2Val + num3Val + num4Val + num5Val + num6Val;
        verifyCode(concat);
        findViewById(R.id.VerifyDone).setVisibility(View.VISIBLE);
        findViewById(R.id.Topper).setVisibility(View.GONE);
        startActivity(new Intent(AccountVerification.this, MainMenu.class));
    }
}