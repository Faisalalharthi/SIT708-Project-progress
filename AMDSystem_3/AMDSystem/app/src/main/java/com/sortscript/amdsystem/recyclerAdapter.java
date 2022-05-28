package com.sortscript.amdsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MessageViewHolder> {
    private ArrayList<Message> messages;
    ArrayList<String> faqQuestions;
    ArrayList<String> faqAnswers =new ArrayList<>();
    getItemPosition itemposInterface;
    String faqmessage;
    Context context;
    public recyclerAdapter(ArrayList<Message> messages, ArrayList<String> faqQuestions, getItemPosition itemposInterface, ChatBot mainActivity) {
        this.messages = messages;
        this.faqQuestions = faqQuestions;
        this.itemposInterface = itemposInterface;
        this.context=mainActivity;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout sentLayout;
        private LinearLayout receivedLayout;
        private TextView sentText;
        private TextView receivedText;

        public MessageViewHolder(final View itemView) {
            super(itemView);
            sentLayout = itemView.findViewById(R.id.sentLayout);
            receivedLayout = itemView.findViewById(R.id.receivedLayout);
            sentText = itemView.findViewById(R.id.sentTextView);
            receivedText = itemView.findViewById(R.id.receivedTextView);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String message = messages.get(position).getMessage();
        itemposInterface.getPosition(position);
        if (position % 2 == 0 && position <=11) {
            faqList(holder,position);
        } else {
            //Message is received
            holder.receivedLayout.setVisibility(LinearLayout.VISIBLE);
            holder.receivedText.setText(message);
            faqAnswers.add(message);
            holder.sentLayout.setVisibility(LinearLayout.GONE);
//            if(position>10){
//                Toast.makeText(context, "Size--> "+faqAnswers.size(), Toast.LENGTH_SHORT).show();
//            }
        }
    }
    public void faqList(MessageViewHolder holder, int position){
        if(position<=12){
            faqmessage = faqQuestions.get(position);
        }

        //If a message is sent
        holder.sentLayout.setVisibility(LinearLayout.VISIBLE);
        holder.sentText.setText(faqmessage);
        // Set visibility as GONE to remove the space taken up
        holder.receivedLayout.setVisibility(LinearLayout.GONE);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
