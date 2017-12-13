package com.dessproject.dessproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText ETAge = (EditText) findViewById(R.id.editText6);
        final EditText ETName = (EditText) findViewById(R.id.editText3);
        final EditText ETUserName = (EditText) findViewById(R.id.editText4);
        final EditText ETPassword = (EditText) findViewById(R.id.editText5);
        final Button BRegiter = (Button) findViewById(R.id.button);
    }
}
