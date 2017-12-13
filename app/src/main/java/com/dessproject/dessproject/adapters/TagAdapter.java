package com.dessproject.dessproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dessproject.dessproject.R;
import com.dessproject.dessproject.backing.TagClass;

import java.util.List;

/**
 * Created by Eric on 12/12/2017.
 */

public class TagAdapter extends ArrayAdapter {
    private final Context context;
    private final List<TagClass> values;

    public TagAdapter(Context context, List values) {
        super(context, R.layout.select_connections, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.select_tag, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tagLabel);
        textView.setText(values.get(position).getTagName());
        rowView.setTag(values.get(position).getTagId());

        //UserClass user = values.get(position);

        return rowView;
    }
}
