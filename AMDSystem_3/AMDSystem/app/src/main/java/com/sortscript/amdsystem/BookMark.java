package com.sortscript.amdsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.sortscript.amdsystem.databinding.FragmentBookMarkBinding;

import java.util.HashMap;


public class BookMark extends Fragment {

    ProgressDialog progressDialog;

    FirebaseRecyclerOptions<Recommendation_Model> options;
    FirebaseRecyclerAdapter<Recommendation_Model, Recommendation_Holder> adapter;

    RecyclerView BookMark, Recommended;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentBookMarkBinding binding = FragmentBookMarkBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        BookMark = view.findViewById(R.id.ShowAllBookMark);
        Recommended = view.findViewById(R.id.ShowAllRecommendation);
        

        binding.BookMarkShow.setOnClickListener(view12 -> {
            binding.RecommendedShow.setBackgroundColor(Color.parseColor("#F4F4F4"));
            binding.RecommendedShow.setTextColor(Color.parseColor("#000000"));
            binding.BookMarkShow.setBackgroundColor(Color.parseColor("#21CB85"));
            binding.BookMarkShow.setTextColor(Color.parseColor("#ffffff"));
            binding.ShowAllBookMark.setVisibility(View.VISIBLE);
            Recommended.setVisibility(View.GONE);

     
            DisplayBookMark();


        });

        binding.RecommendedShow.setOnClickListener(view1 -> {


            binding.BookMarkShow.setBackgroundColor(Color.parseColor("#F4F4F4"));
            binding.BookMarkShow.setTextColor(Color.parseColor("#000000"));

            binding.RecommendedShow.setBackgroundColor(Color.parseColor("#21CB85"));
            binding.RecommendedShow.setTextColor(Color.parseColor("#ffffff"));


            Recommended.setVisibility(View.VISIBLE);
            binding.ShowAllBookMark.setVisibility(View.GONE);

            DisplayRecommendation();

        });


        DisplayRecommendation();


        return view;
    }

    private void DisplayBookMark() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Universities").child("BookMarked").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        options = new FirebaseRecyclerOptions.Builder<Recommendation_Model>().setQuery(databaseReference, Recommendation_Model.class).build();

        adapter = new FirebaseRecyclerAdapter<Recommendation_Model, Recommendation_Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Recommendation_Holder holder, int position, @NonNull Recommendation_Model model) {

                holder.UniName.setText(model.getInstitute());
                holder.Rating.setText(model.getScore());
                holder.Bookmarkbtn.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);

                holder.Bookmarkbtn.setOnClickListener(view -> holder.Bookmarkbtn.setBackgroundResource(R.drawable.ic_baseline_bookmark_24));

                holder.itemView.setOnClickListener(view -> startActivity(new Intent(getActivity(), ViewDetails.class).putExtra("PrivateKey", model.getKey())));

                holder.Bookmarkbtn.setOnClickListener(view -> {
                    holder.Bookmarkbtn.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                    databaseReference.child(model.getKey()).removeValue();
                });

                holder.fvrtBtn.setOnClickListener(view -> {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Favourite");
                    HashMap<String, Object> map = new HashMap<>();
                    String PriKey = reference.push().getKey();
                    map.put("institute", model.getInstitute());
                    map.put("Key", PriKey);
                    map.put("country", model.getCountry());
                    map.put("faculty", model.getFaculty());
                    map.put("patent", model.getPatent());
                    map.put("publication", model.getPublication());
                    map.put("quality", model.getQuality());
                    map.put("rank", model.getRank());
                    map.put("score", model.getScore());


                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(PriKey).setValue(map).addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            holder.fvrtBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        } else {
                            Toast.makeText(getActivity(), "Check Internet", Toast.LENGTH_SHORT).show();
                        }
                    });


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


        BookMark.setHasFixedSize(true);
        BookMark.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter.startListening();
        BookMark.setAdapter(adapter);
    }

    private void DisplayRecommendation() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Recommendation").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query query = reference.limitToLast(25);
        options = new FirebaseRecyclerOptions.Builder<Recommendation_Model>().setQuery(query, Recommendation_Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Recommendation_Model, Recommendation_Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Recommendation_Holder holder, int position, @NonNull Recommendation_Model model) {

                holder.UniName.setText(model.getInstitute());
                holder.Rating.setText(model.getScore());

                holder.Bookmarkbtn.setOnClickListener(view -> {
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Universities").child("BookMarked");
                    HashMap<String, Object> map = new HashMap<>();
                    String PriKey = reference1.push().getKey();

                    map.put("institute", model.getInstitute());
                    map.put("Key", PriKey);
                    map.put("country", model.getCountry());
                    map.put("faculty", model.getFaculty());
                    map.put("patent", model.getPatent());
                    map.put("publication", model.getPublication());
                    map.put("quality", model.getQuality());
                    map.put("rank", model.getRank());
                    map.put("score", model.getScore());


                    reference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(PriKey).setValue(map).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            holder.Bookmarkbtn.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                        } else {
                            Toast.makeText(getActivity(), "Check Internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                    });

                holder.itemView.setOnClickListener(view -> startActivity(new Intent(getActivity(), ViewDetails.class).putExtra("PrivateKey", model.getKey())));
                holder.fvrtBtn.setOnClickListener(view -> {

                    DatabaseReference reference12 = FirebaseDatabase.getInstance().getReference().child("Universities").child("Favourite");
                    HashMap<String, Object> map = new HashMap<>();
                    String PriKey = reference12.push().getKey();

                    map.put("institute", model.getInstitute());
                    map.put("Key", PriKey);
                    map.put("country", model.getCountry());
                    map.put("faculty", model.getFaculty());
                    map.put("patent", model.getPatent());
                    map.put("publication", model.getPublication());
                    map.put("quality", model.getQuality());
                    map.put("rank", model.getRank());
                    map.put("score", model.getScore());


                    reference12.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(PriKey).setValue(map).addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            holder.fvrtBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        } else {
                            Toast.makeText(getActivity(), "Check Internet", Toast.LENGTH_SHORT).show();
                        }
                    });


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


        Recommended.setHasFixedSize(true);
        Recommended.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter.startListening();
        Recommended.setAdapter(adapter);

    }
}