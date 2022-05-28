package com.sortscript.amdsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sortscript.amdsystem.databinding.ActivityViewDetailsBinding;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {

    ActivityViewDetailsBinding binding;

    String PrivateKey;
    String Name, Country, rating, faculty, patent, publications, quality, rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        PrivateKey = getIntent().getStringExtra("PrivateKey");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Recommendation").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.orderByChild("Key").equalTo(PrivateKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Name = (String) snapshot1.child("institute").getValue();
                    binding.UniName.setText(Name);
                    Country = (String) snapshot1.child("country").getValue();
                    binding.Country.setText(Country);
                    rating = (String) snapshot1.child("score").getValue();
                    binding.Rating.setText(rating);
                    faculty = (String) snapshot1.child("faculty").getValue();
                    patent = (String) snapshot1.child("patent").getValue();
                    publications = (String) snapshot1.child("publications").getValue();
                    quality = (String) snapshot1.child("quality").getValue();
                    rank = (String) snapshot1.child("rank").getValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void LikedDone(View view) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Favourite");


        HashMap<String, Object> map = new HashMap<>();

        String PriKey = reference.push().getKey();

        map.put("institute", Name);
        map.put("Key", PriKey);
        map.put("country", Country);
        map.put("faculty", faculty);
        map.put("patent", patent);
        map.put("publication", publications);
        map.put("quality", quality);
        map.put("rank", rank);
        map.put("score", rating);


        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(PriKey).setValue(map).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

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
            } else {
                Toast.makeText(this, "Check Internet", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void backshow(View view) {
        this.onBackPressed();
    }
}