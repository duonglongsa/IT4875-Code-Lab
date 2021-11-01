package com.example.submitform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, studentID, phoneNumber, dateOfBirth, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        studentID = findViewById(R.id.studentID);
        email = findViewById(R.id.email);

    }

    public void onSubmitClick(View view){
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show();
        }
        if(studentID.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your student ID!", Toast.LENGTH_SHORT).show();
        }
        if(dateOfBirth.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your date of birth!", Toast.LENGTH_SHORT).show();
        }
        if(phoneNumber.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your phone number!", Toast.LENGTH_SHORT).show();
        }
        if(email.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your email!", Toast.LENGTH_SHORT).show();
        }
    }
}