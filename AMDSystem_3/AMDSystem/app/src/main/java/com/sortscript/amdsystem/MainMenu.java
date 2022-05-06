package com.sortscript.amdsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sortscript.amdsystem.databinding.ActivityMainMenuBinding;

public class MainMenu extends AppCompatActivity {

    ActivityMainMenuBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new Search());
        ft.commit();


        binding.Liked.setOnClickListener(view -> {
            FragmentTransaction ft12 = getSupportFragmentManager().beginTransaction();
            ft12.replace(R.id.your_placeholder, new Favourite());
            ft12.commit();

            binding.likeIcon.setColorFilter(Color.parseColor("#ffffff"));
            binding.likeIcon.setBackgroundResource(R.drawable.selectedwork);
            binding.likeText.setTextColor(Color.parseColor("#21CB85"));

            binding.SearchIcon.setColorFilter(Color.parseColor("#757575"));
            binding.SearchIcon.setBackgroundResource(android.R.color.transparent);
            binding.SearchText.setTextColor(Color.parseColor("#757575"));

            binding.BookIcon.setColorFilter(Color.parseColor("#757575"));
            binding.BookIcon.setBackgroundResource(android.R.color.transparent);
            binding.BookText.setTextColor(Color.parseColor("#757575"));

            binding.ProfileIcon.setColorFilter(Color.parseColor("#757575"));
            binding.ProfileIcon.setBackgroundResource(android.R.color.transparent);
            binding.ProfileText.setTextColor(Color.parseColor("#757575"));

        });

        binding.Searching.setOnClickListener(view -> {
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.your_placeholder, new Search());
            ft1.commit();

            binding.SearchIcon.setColorFilter(Color.parseColor("#ffffff"));
            binding.SearchIcon.setBackgroundResource(R.drawable.selectedwork);
            binding.SearchText.setTextColor(Color.parseColor("#21CB85"));

            binding.likeIcon.setColorFilter(Color.parseColor("#757575"));
            binding.likeIcon.setBackgroundResource(android.R.color.transparent);
            binding.likeText.setTextColor(Color.parseColor("#757575"));

            binding.BookIcon.setColorFilter(Color.parseColor("#757575"));
            binding.BookIcon.setBackgroundResource(android.R.color.transparent);
            binding.BookText.setTextColor(Color.parseColor("#757575"));

            binding.ProfileIcon.setColorFilter(Color.parseColor("#757575"));
            binding.ProfileIcon.setBackgroundResource(android.R.color.transparent);
            binding.ProfileText.setTextColor(Color.parseColor("#757575"));

        });


        binding.Bookmark.setOnClickListener(view -> {
            FragmentTransaction ft12 = getSupportFragmentManager().beginTransaction();
            ft12.replace(R.id.your_placeholder, new BookMark());
            ft12.commit();

            binding.likeIcon.setColorFilter(Color.parseColor("#757575"));
            binding.likeIcon.setBackgroundResource(android.R.color.transparent);
            binding.likeText.setTextColor(Color.parseColor("#757575"));

            binding.SearchIcon.setColorFilter(Color.parseColor("#757575"));
            binding.SearchIcon.setBackgroundResource(android.R.color.transparent);
            binding.SearchText.setTextColor(Color.parseColor("#757575"));

            binding.BookIcon.setColorFilter(Color.parseColor("#ffffff"));
            binding.BookIcon.setBackgroundResource(R.drawable.selectedwork);
            binding.BookText.setTextColor(Color.parseColor("#21CB85"));

            binding.ProfileIcon.setColorFilter(Color.parseColor("#757575"));
            binding.ProfileIcon.setBackgroundResource(android.R.color.transparent);
            binding.ProfileText.setTextColor(Color.parseColor("#757575"));

        });

        binding.Profile.setOnClickListener(view -> {
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.your_placeholder, new Profile());
            ft1.commit();

            binding.ProfileIcon.setColorFilter(Color.parseColor("#ffffff"));
            binding.ProfileIcon.setBackgroundResource(R.drawable.selectedwork);
            binding.ProfileText.setTextColor(Color.parseColor("#21CB85"));

            binding.SearchIcon.setColorFilter(Color.parseColor("#757575"));
            binding.SearchIcon.setBackgroundResource(android.R.color.transparent);
            binding.SearchText.setTextColor(Color.parseColor("#757575"));

            binding.BookIcon.setColorFilter(Color.parseColor("#757575"));
            binding.BookIcon.setBackgroundResource(android.R.color.transparent);
            binding.BookText.setTextColor(Color.parseColor("#757575"));

            binding.likeIcon.setColorFilter(Color.parseColor("#757575"));
            binding.likeIcon.setBackgroundResource(android.R.color.transparent);
            binding.likeText.setTextColor(Color.parseColor("#757575"));


        });

    }


}