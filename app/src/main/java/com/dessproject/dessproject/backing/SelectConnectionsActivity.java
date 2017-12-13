package com.dessproject.dessproject.backing;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.dessproject.dessproject.R;
import com.dessproject.dessproject.adapters.ConnectionsAdapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eric on 12/12/2017.
 */

public class SelectConnectionsActivity extends ListActivity{

    static List<UserClass> connections;
    PostClass newPost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPost = (PostClass) getIntent().getSerializableExtra("post");
        setListAdapter(new ConnectionsAdapter(this,connections));



    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        newPost.getAttatchedUsers().add(Integer.valueOf((String)v.getTag()));
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Intent intent = new Intent(SelectConnectionsActivity.this, SelectConnectionsActivity.class);
                        SelectConnectionsActivity.this.startActivity(intent);
                        int numRows = jsonResponse.getInt("numRows");
                        List<TagClass> tagList = new ArrayList<TagClass>();
                        for (int i = 0; i < numRows; i++) {
                            JSONObject curObject = jsonResponse.getJSONObject(String.valueOf(i));
                            TagClass newTag = new TagClass();
                            newTag.setTagId(Integer.valueOf(curObject.getString("id")));
                            newTag.setTagName(curObject.getString("title"));
                            newTag.setDescription("description");
                            tagList.add(newTag);
                        }

                        SelectTagsActivity.tags = tagList;
                        Intent postIntent = new Intent(SelectConnectionsActivity.this, SelectTagsActivity.class);
                        postIntent.putExtra("post",newPost);
                        startActivity(postIntent);
                        finish();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SelectConnectionsActivity.this);
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
        TagRequest tagRequest = new TagRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelectConnectionsActivity.this);
        queue.add(tagRequest);




    }
}



