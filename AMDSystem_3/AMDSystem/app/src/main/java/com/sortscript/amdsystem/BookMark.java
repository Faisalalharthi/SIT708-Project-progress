package com.sortscript.amdsystem;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sortscript.amdsystem.databinding.FragmentBookMarkBinding;


public class BookMark extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentBookMarkBinding binding = FragmentBookMarkBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.BookMarkShow.setOnClickListener(view12 -> {
            binding.RecommendedShow.setBackgroundColor(Color.parseColor("#F4F4F4"));
            binding.RecommendedShow.setTextColor(Color.parseColor("#000000"));


            binding.BookMarkShow.setBackgroundColor(Color.parseColor("#21CB85"));
            binding.BookMarkShow.setTextColor(Color.parseColor("#ffffff"));


        });

        binding.RecommendedShow.setOnClickListener(view1 -> {


            binding.BookMarkShow.setBackgroundColor(Color.parseColor("#F4F4F4"));
            binding.BookMarkShow.setTextColor(Color.parseColor("#000000"));

            binding.RecommendedShow.setBackgroundColor(Color.parseColor("#21CB85"));
            binding.RecommendedShow.setTextColor(Color.parseColor("#ffffff"));


        });

        return view;
    }
}