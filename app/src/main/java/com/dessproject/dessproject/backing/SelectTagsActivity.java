package com.dessproject.dessproject.backing;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dessproject.dessproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 12/12/2017.
 */

public class SelectTagsActivity extends ListActivity{
    static List tags;
    List selectedTags = new ArrayList();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.select_tag,tags));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedTags.add(((TextView) view).getText());
            }
        });

    }
}
