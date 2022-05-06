package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class ViewDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        getSupportActionBar().hide();
    }

    public void LikedDone(View view) {

        findViewById(R.id.updated).setVisibility(View.VISIBLE);
        findViewById(R.id.FavBtn).setVisibility(View.GONE);
        findViewById(R.id.Details).setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.updated).setVisibility(View.GONE);
                findViewById(R.id.FavBtn).setVisibility(View.VISIBLE);
                findViewById(R.id.Details).setVisibility(View.VISIBLE);
            }
        }, 2000);

    }
}