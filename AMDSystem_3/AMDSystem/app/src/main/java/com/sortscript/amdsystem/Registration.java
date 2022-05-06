package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
    }

    public void MovetoSignIn(View view) {
        startActivity(new Intent(Registration.this, MainActivity.class));
        finish();
    }

    public void AccountVerification(View view) {
        startActivity(new Intent(Registration.this, AccountVerification.class));
    }
}