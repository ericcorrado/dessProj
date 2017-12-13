package com.dessproject.dessproject.adapters;

/**
 * Created by Eric on 12/12/2017.
 */

        import com.dessproject.dessproject.R;
        import com.dessproject.dessproject.backing.UserClass;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

public class ConnectionsAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<UserClass> values;

    public ConnectionsAdapter(Context context, List values) {
        super(context, R.layout.select_connections, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.select_connections, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.connectionLabel);
        textView.setText(values.get(position).getFirstName() + " " + values.get(position).getLastName());
        rowView.setTag(values.get(position).getUsedId());


        // Change icon based on name
        UserClass user = values.get(position);





        return rowView;
    }
}