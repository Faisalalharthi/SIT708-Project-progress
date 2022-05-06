package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public void ForgetNow(View view) {
        startActivity(new Intent(MainActivity.this, ForgetPassword.class));
    }

    public void MovetoRegistration(View view) {
        startActivity(new Intent(MainActivity.this, Registration.class));
    }

    public void MainScreen(View view) {
        startActivity(new Intent(MainActivity.this, MainMenu.class));

    }
}