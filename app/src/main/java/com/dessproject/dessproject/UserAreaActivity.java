package com.dessproject.dessproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final EditText ETUserName = (EditText) findViewById(R.id.editText7);
        final EditText ETAge = (EditText) findViewById(R.id.editText8);
        final TextView WelcomeMessage =(TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.textView2);
        EditText etUsername = (EditText) findViewById(R.id.editText7);
        EditText etAge = (EditText) findViewById(R.id.editText8);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");

    }
}
