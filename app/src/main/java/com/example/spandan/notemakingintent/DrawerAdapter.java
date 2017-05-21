package com.example.spandan.notemakingintent;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spandan on 5/15/2017.
 */

public class DrawerAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> tools;
    public DrawerAdapter(Activity context, int resourceId, ArrayList<String> drawerTools){
        super(context,resourceId,drawerTools);
        this.context = context;
        this.tools = drawerTools;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ArrayList<Integer> toolsImage = new ArrayList<>();
        toolsImage.add(R.drawable.setings);
        toolsImage.add(R.drawable.planner);
        toolsImage.add(R.drawable.notification);
        toolsImage.add(R.drawable.connections);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.nav_listview_layout, null, true);
        ImageView drawerImage = (ImageView) rowView.findViewById(R.id.optionImage);
        TextView drawerText = (TextView) rowView.findViewById(R.id.optionText);

        drawerText.setText(tools.get(position));
        drawerImage.setImageDrawable(context.getResources().getDrawable(toolsImage.get(position)));


        Log.i("adapterInfo2","populated2");
        return rowView;
    }
}
