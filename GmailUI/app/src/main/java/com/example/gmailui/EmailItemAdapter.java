package com.example.gmailui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmailItemAdapter extends RecyclerView.Adapter {

    List<EmailItem> dataset;
    Random random = new Random();
    int randomLimit = 10;


    public EmailItemAdapter(List<EmailItem> dataset) {
        this.dataset = dataset;
    }

    public EmailItemAdapter(EmailItem[] array) {
        this.dataset = new ArrayList<>(Arrays.asList(array));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.email_card, parent, false);

        EmailItemViewHolder vh = new EmailItemViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailItem item = dataset.get(position);
        //TODO: deal with avatar
        //((EmailItemViewHolder) holder).mAvatar.setBackground(android.R.color.transparent);
        ((EmailItemViewHolder) holder).mAvatar.setBackgroundResource(
                android.R.color.transparent);
        ((EmailItemViewHolder) holder).mSender.setText(item.getSender());
        ((EmailItemViewHolder) holder).mSubject.setText(item.getSubject());
        ((EmailItemViewHolder) holder).mbrief.setText(item.getBrief());
        ((EmailItemViewHolder) holder).mDate.setText(item.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class EmailItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView mAvatar;
        public TextView mSender;
        public TextView mSubject;
        public TextView mbrief;
        public TextView mDate;

        public EmailItemViewHolder(View itemView) {
            super(itemView);

            mAvatar = itemView.findViewById(R.id.avatar);
            mSender= itemView.findViewById(R.id.sender);
            mSubject = itemView.findViewById(R.id.subject);
            mbrief = itemView.findViewById(R.id.brief);
            mDate = itemView.findViewById(R.id.date);
        }
    }
}
