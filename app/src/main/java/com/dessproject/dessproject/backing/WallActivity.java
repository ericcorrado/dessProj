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

public class WallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        final Button subButton = (Button) findViewById(R.id.button);
        final Button viewButton = (Button) findViewById(R.id.button2);









        viewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int uId = 1;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(WallActivity.this, WallActivity.class);
                                WallActivity.this.startActivity(intent);
                                int numRows = jsonResponse.getInt("numRows");
                                List<String> postList = new ArrayList<String>();
                                for (int i = 0; i < numRows; i++) {
                                    JSONObject curObject = jsonResponse.getJSONObject(String.valueOf(i));
                                    PostClass newPost = new PostClass();
                                    String decodedContent = new String(Base64.getDecoder().decode(curObject.getString("content").getBytes()));
                                    newPost.setContent(decodedContent);
                                    postList.add(newPost.getContent());
                                }
                                WallListView.posts = postList;
                                Intent viewIntent = new Intent(WallActivity.this, WallListView.class);
                                startActivity(viewIntent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(WallActivity.this);
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
                WallRequest wallRequest1 = new WallRequest(uId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WallActivity.this);
                queue.add(wallRequest1);


            }
        });



        subButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final int uId = 4;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(WallActivity.this, WallActivity.class);
                                WallActivity.this.startActivity(intent);
                                int numRows = jsonResponse.getInt("numRows");
                                List<UserClass> userList = new ArrayList<UserClass>();
                                for (int i = 0; i < numRows; i++) {
                                    JSONObject curObject = jsonResponse.getJSONObject(String.valueOf(i));
                                    UserClass user = new UserClass();
                                    user.setUsedId(curObject.getString("cId"));
                                    user.setFirstName(curObject.getString("fName"));
                                    user.setLastName(curObject.getString("lName"));
                                    userList.add(user);
                                }
                                SelectConnectionsActivity.connections = userList;
                                PostClass newPost = new PostClass();
                                Intent postIntent = new Intent(WallActivity.this, SelectConnectionsActivity.class);
                                postIntent.putExtra("post",newPost);
                                startActivity(postIntent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(WallActivity.this);
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
                ConnectionsRequest connectionsRequest = new ConnectionsRequest(uId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WallActivity.this);
                queue.add(connectionsRequest);




            }
        });



    }
}
