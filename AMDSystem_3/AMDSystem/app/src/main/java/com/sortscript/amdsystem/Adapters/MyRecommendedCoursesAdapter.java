package com.sortscript.amdsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sortscript.amdsystem.Models.Recommendation;
import com.sortscript.amdsystem.R;
import com.sortscript.amdsystem.Recommendation_Model;

import java.util.ArrayList;
import java.util.HashMap;


public class MyRecommendedCoursesAdapter extends RecyclerView.Adapter<MyRecommendedCoursesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Recommendation> modelRespAry;
    Recommendation_Model recommendation_model;

    public MyRecommendedCoursesAdapter(Context context, ArrayList<Recommendation> body, Recommendation_Model recommendation_model) {
        this.context = context;
        this.modelRespAry = body;
        this.recommendation_model = recommendation_model;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//            String price =modelRespAry.get(position).getPrice();
        String score = modelRespAry.get(position).getScore();
        String institute = modelRespAry.get(position).getInstitution();
        holder.institution.setText("" + institute);
//            holder.price.setText(""+price);
        holder.score.setText("" + score);

    }


    @Override
    public int getItemCount() {
        return modelRespAry.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton bookmarkBtn, fvrtBtn;
        TextView score, institution;
//        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            institution = itemView.findViewById(R.id.institution);
            score = itemView.findViewById(R.id.score);
            bookmarkBtn = itemView.findViewById(R.id.bookmarkBtn);
            fvrtBtn = itemView.findViewById(R.id.fvrtBtn);
//            price = itemView.findViewById(R.id.price);
        }
    }
}
