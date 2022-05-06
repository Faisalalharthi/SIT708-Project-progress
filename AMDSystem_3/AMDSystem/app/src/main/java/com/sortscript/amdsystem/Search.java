package com.sortscript.amdsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sortscript.amdsystem.databinding.FragmentBookMarkBinding;
import com.sortscript.amdsystem.databinding.FragmentSearchBinding;


public class Search extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSearchBinding binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.chatnow.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), ChatBot.class)));

        binding.ShowTime.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), ViewDetails.class)));

        return view;
    }
}