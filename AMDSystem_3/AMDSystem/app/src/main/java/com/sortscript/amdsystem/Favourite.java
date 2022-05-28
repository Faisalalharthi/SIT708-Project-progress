package com.sortscript.amdsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sortscript.amdsystem.databinding.FragmentBookMarkBinding;
import com.sortscript.amdsystem.databinding.FragmentFavouriteBinding;


public class Favourite extends Fragment {

    ProgressDialog progressDialog;

    FirebaseRecyclerOptions<Recommendation_Model> options;
    FirebaseRecyclerAdapter<Recommendation_Model, Recommendation_Holder> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFavouriteBinding binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Processing...");
        progressDialog.show();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Favourite").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        options = new FirebaseRecyclerOptions.Builder<Recommendation_Model>().setQuery(reference, Recommendation_Model.class).build();

        adapter = new FirebaseRecyclerAdapter<Recommendation_Model, Recommendation_Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Recommendation_Holder holder, int position, @NonNull Recommendation_Model model) {

                holder.UniName.setText(model.getInstitute());
                holder.Rating.setText(model.getScore());

                holder.fvrtBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_24));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(),ViewDetails.class).putExtra("PrivateKey",model.getKey()));
                    }
                });


                holder.fvrtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.fvrtBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24));
                        reference.child(model.getKey()).removeValue();
                    }
                });


                progressDialog.dismiss();
            }

            @NonNull
            @Override
            public Recommendation_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_design, parent, false);
                return new Recommendation_Holder(view1);
            }
        };

        binding.ShowAllFavourite.setHasFixedSize(true);
        binding.ShowAllFavourite.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter.startListening();
        binding.ShowAllFavourite.setAdapter(adapter);



        return view;



    }
}