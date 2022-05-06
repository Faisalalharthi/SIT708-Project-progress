package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AccountVerification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);
        getSupportActionBar().hide();
    }

    public void VerificationDone(View view) {
        findViewById(R.id.VerifyDone).setVisibility(View.VISIBLE);
        findViewById(R.id.Topper).setVisibility(View.GONE);
    }
}