package com.example.spandan.notemakingintent;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spandan on 5/15/2017.
 */

public class MyCustomAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> getTask;
    public MyCustomAdapter(Activity context, int resourceId, ArrayList<String> tasks){
        super(context,resourceId,tasks);
        this.context = context;
        this.getTask = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.task_list, null, true);
        EditText editText = (EditText) rowView.findViewById(R.id.taskentry);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.taskcheckbox);

        editText.setText(getTask.get(position));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Log.i("adapterInfo2","populated2");
        return rowView;
    }
}
