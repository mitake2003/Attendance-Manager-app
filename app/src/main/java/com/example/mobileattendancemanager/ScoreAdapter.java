package com.example.mobileattendancemanager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Attendence;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Logindata> {
    private Context context;
    String subject;
    public ScoreAdapter(Context context, List<Logindata> data,String sub){
        super(context,0,data);
        this.context = context;
        this.subject = sub;
    }

    Database_holder database_holder = Database_holder.getDB(context);

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_score, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name2);
        TextView roll = convertView.findViewById(R.id.roll1);
        TextView percent = convertView.findViewById(R.id.percent);

        Logindata attend = getItem(position);
        name.setText(attend.getName());
        roll.setText(attend.getRollNo());
        double n = (double) database_holder.count_dao().getCount(subject,attend.getSection());
        double count = (double) database_holder.attendence_dao().getRollcnt(attend.getRollNo(),subject,attend.getSection());
        Log.d("count",n + " " + count + " " + count/n);
        double per = count/n * 100;
        if (n == 0){
            per = 0.0;
        }
        percent.setText(String.format("%.2f",per)+"%");
        if (per > 80){
            percent.setTextColor(Color.parseColor("#44BF49"));
        }
        else if (per>50 && per < 80){
            percent.setTextColor(Color.parseColor("#CAC06A"));
        }
        else {
            percent.setTextColor(Color.parseColor("#E91E1E"));
        }
        return convertView;
    }

    public void addSub(String sub){
        this.subject = sub;
    }
}
