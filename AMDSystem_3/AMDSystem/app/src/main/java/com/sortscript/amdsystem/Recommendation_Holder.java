package com.sortscript.amdsystem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Recommendation_Holder extends RecyclerView.ViewHolder {

    public ImageView UniIcon, UniImg;
    public TextView UniName, Rating, Description;
    public ImageButton Bookmarkbtn,fvrtBtn;

    public Recommendation_Holder(@NonNull View itemView) {
        super(itemView);

        UniIcon = itemView.findViewById(R.id.logoImg);
        UniImg = itemView.findViewById(R.id.UniImage);

        UniName = itemView.findViewById(R.id.institution);
        Rating = itemView.findViewById(R.id.score);
        Description = itemView.findViewById(R.id.description);

        Bookmarkbtn = itemView.findViewById(R.id.bookmarkBtn);
        fvrtBtn = itemView.findViewById(R.id.fvrtBtn);

    }
}
