package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgetOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_otp);
        getSupportActionBar().hide();
    }

    public void ChangePass(View view) {
        startActivity(new Intent(ForgetOTP.this, CreateNewPassword.class));
    }
}