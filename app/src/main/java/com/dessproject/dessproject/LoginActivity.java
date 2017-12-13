package com.dessproject.dessproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.dessproject.dessproject.backing.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText ETUserName = (EditText) findViewById(R.id.editText);
        final EditText ETPassword = (EditText) findViewById(R.id.editText2);
        final Button BLogin = (Button) findViewById(R.id.button2);
        final TextView EVRegisterHere = (TextView) findViewById(R.id.textView);

        EVRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        BLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = ETUserName.getText().toString();
                String password = ETPassword.getText().toString();
                try {

                    MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                    digest.update(password.getBytes());
                    byte messageDigest[] = digest.digest();

                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < messageDigest.length; i++) {
                        String h = Integer.toHexString(0xFF & messageDigest[i]);
                        while (h.length() < 2)
                            h = "0" + h;
                        hexString.append(h);
                    }
                    password = hexString.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("fName");


                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username", username);
                                LoginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
