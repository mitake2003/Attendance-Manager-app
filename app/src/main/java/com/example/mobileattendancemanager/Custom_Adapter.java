package com.example.mobileattendancemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileattendancemanager.Table.Logindata;

import java.util.List;

public class Custom_Adapter extends ArrayAdapter<Logindata> {
    public Custom_Adapter(Context context, List<Logindata> data){
        super(context,0,data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name1);
        TextView roll = convertView.findViewById(R.id.roll);

        Logindata logindata = getItem(position);
        name.setText(logindata.getName());
        roll.setText(logindata.getRollNo());

        return convertView;
    }
}
