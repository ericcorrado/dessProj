package com.dessproject.dessproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.dessproject.dessproject.backing.RegisterRequest;
import com.dessproject.dessproject.backing.UsernameRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText ETFName = (EditText) findViewById(R.id.editText3);
        final EditText ETLName = (EditText) findViewById(R.id.editText6);
        final EditText ETEmail = (EditText) findViewById(R.id.editText9);
        final EditText ETUserName = (EditText) findViewById(R.id.editText4);
        final EditText ETPassword = (EditText) findViewById(R.id.editText5);
        final Button BRegiter = (Button) findViewById(R.id.button);
        final Button Bcheck = (Button) findViewById(R.id.button3);

        Bcheck.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String Username = ETUserName.getText().toString();

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("UserName is Unavailable")
                                                .setNegativeButton("ok", null)
                                                .create()
                                                .show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("User Name is Available")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        UsernameRequest UsernameRequest = new UsernameRequest(Username, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(UsernameRequest);
                }
                });


        BRegiter.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FName = ETFName.getText().toString();
                final String LName = ETLName.getText().toString();
                final String Email = ETEmail.getText().toString();
                final String Username = ETUserName.getText().toString();
                String Password = ETPassword.getText().toString();

                try {

                    MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                    digest.update(Password.getBytes());
                    byte messageDigest[] = digest.digest();

                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < messageDigest.length; i++) {
                        String h = Integer.toHexString(0xFF & messageDigest[i]);
                        while (h.length() < 2)
                            h = "0" + h;
                        hexString.append(h);
                    }
                    Password = hexString.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(FName, LName, Email, Username, Password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}

