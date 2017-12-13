package com.dessproject.dessproject.backing;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dessproject.dessproject.R;
import com.dessproject.dessproject.adapters.TagAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 12/12/2017.
 */

public class SelectTagsActivity extends ListActivity{
    static List tags;
    PostClass newPost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPost = (PostClass) getIntent().getSerializableExtra("post");
        setListAdapter(new TagAdapter(this, tags));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        newPost.getAttatchedTags().add((Integer) v.getTag());
        Intent postIntent = new Intent(SelectTagsActivity.this, PostActivity.class);
        postIntent.putExtra("post",newPost);
        startActivity(postIntent);
        finish();



    }
}
