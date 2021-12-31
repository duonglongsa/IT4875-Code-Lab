package com.example.gmailui;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.gmailui.model.User;

public class InfoActivity extends AppCompatActivity {
    private ImageView user_avatar;
    private TextView username,user,email,address,phone,company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        user_avatar = findViewById(R.id.image_avatar);
        username = findViewById(R.id.username);
        user = findViewById(R.id.name);
        email = findViewById(R.id.user_email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone_number);
        company =findViewById(R.id.company);
        User mUser = (User)getIntent().getSerializableExtra("User");

        Glide.with(this).load(Utils.BASE_URL+mUser.getAvatar().getPhoto()).into(user_avatar);
        username.setText("User name: " + mUser.getUsername());
        user.setText("Name: " + mUser.getName());
        email.setText("Email: " + mUser.getEmail());
        address.setText("Address: " + mUser.getAddress().getStreet()+" "+mUser.getAddress().getSuite()+" "+mUser.getAddress().getCity());
        phone.setText("Phone: " + mUser.getPhone());
        company.setText("Company: " + mUser.getCompany().getName());
    }
}