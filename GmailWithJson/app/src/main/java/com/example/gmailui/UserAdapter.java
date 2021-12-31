package com.example.gmailui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gmailui.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter {

    List<User> dataset;
    private Context context;

    public UserAdapter(Context context, List<User> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    public UserAdapter(Context context ,User[] array) {
        this.dataset = new ArrayList<>(Arrays.asList(array));
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.email_card, parent, false);

        UserViewHolder vh = new UserViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = dataset.get(position);
        //TODO: deal with avatar
        //((UserViewHolder) holder).mAvatar.setBackground(android.R.color.transparent);
        Glide.with(context).load(Utils.BASE_URL+user.getAvatar().getPhoto()).into(((UserViewHolder) holder).mAvatar);
        ((UserViewHolder) holder).mSender.setText(user.getUsername());
        ((UserViewHolder) holder).mSubject.setText(user.getEmail());
        ((UserViewHolder) holder).setOnUserClickListener(
                (view, position1) -> {
                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("User", dataset.get(position1));
                    context.startActivity(intent);
                }
        );
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView mAvatar;
        public TextView mSender;
        public TextView mSubject;
        private OnUserClickListener onUserClickListener;


        public UserViewHolder(View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.avatar);
            mSender = itemView.findViewById(R.id.sender);
            mSubject = itemView.findViewById(R.id.subject);
            itemView.setOnClickListener(this);
        }

        public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
            this.onUserClickListener = onUserClickListener;
        }

        @Override
        public void onClick(View view) {
            onUserClickListener.onUserClick(view,getAdapterPosition());
        }
    }

    public interface OnUserClickListener {
        void onUserClick(View view, int position);
    }

}
