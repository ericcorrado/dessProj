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

import com.dessproject.dessproject.R;
import com.dessproject.dessproject.adapters.ConnectionsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 12/12/2017.
 */

public class SelectConnectionsActivity extends ListActivity{

    static List connections;
    PostClass newPost = (PostClass) getIntent().getSerializableExtra("post");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ConnectionsAdapter(this,connections));


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        newPost.getAttatchedUsers().add((Integer) l.getTag());
        String selectedValue = (String) getListAdapter().getItem(position);
        Intent postIntent = new Intent(SelectConnectionsActivity.this, SelectTagsActivity.class);
        postIntent.putExtra("post",newPost);
        startActivity(postIntent);
        finish();

    }
}
