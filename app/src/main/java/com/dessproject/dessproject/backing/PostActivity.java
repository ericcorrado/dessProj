package com.dessproject.dessproject.backing;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import com.dessproject.dessproject.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Base64;

public class PostActivity extends AppCompatActivity {
    PostClass newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        newPost = (PostClass) getIntent().getSerializableExtra("post");


        final Button submitButton = (Button) findViewById(R.id.submitBut);
        final EditText contentBox = (EditText) findViewById(R.id.contentBox);
        final EditText titleBox = (EditText) findViewById(R.id.titleBox);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String postToSave =  contentBox.getText().toString();
                final String encodedPost = new String(Base64.getEncoder().encode(postToSave.getBytes()));
                 final String title = titleBox.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Intent intent = new Intent(PostActivity.this, PostActivity.class);
                                        PostActivity.this.startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(PostActivity.this);
                                        builder.setMessage("Post Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                    }

                };

                Date curDate = new Date();
                WallRequest wallRequest = new WallRequest(encodedPost, title, String.valueOf(newPost.getAttatchedTags().get(0)), String.valueOf(newPost.getAttatchedUsers().get(0)), curDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PostActivity.this);
                queue.add(wallRequest);

            }

        });
    }
}