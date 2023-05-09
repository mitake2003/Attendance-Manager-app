package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Attendence;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class studAttendance extends AppCompatActivity {

    GridLayout gridLayout;
    Button back2;
    Spinner spn1,spn2,spn3;
    String Month[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
    String days[] = {"Sun","Mon","Tue","Wed","Thr","Fri","Sat"};
    String sub = "PBL";
    TextView txtname,txtroll,percent2,sec1;
    int month,day,res,dm,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_attendance);
        gridLayout = findViewById(R.id.gridLayout);
        spn1 = findViewById(R.id.spn1);
        spn2 = findViewById(R.id.spn2);
        spn3 = findViewById(R.id.spn3);
        back2 = findViewById(R.id.back2);
        txtname = findViewById(R.id.txtname);
        txtroll = findViewById(R.id.txtroll);
        percent2 = findViewById(R.id.percent2);
        sec1 = findViewById(R.id.sec1);

        Database_holder database_holder = Database_holder.getDB(this);

        //Getting username and password through intent passing
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        String password = intent.getStringExtra("pass");
        List<Logindata> rollno = database_holder.login_dao().getRollno(username,password);

        double n = (double) database_holder.count_dao().getCount2(rollno.get(0).getSection());
        double count = (double) database_holder.attendence_dao().getRollcnt2(rollno.get(0).getRollNo(),rollno.get(0).getSection());
        double per = count/n * 100;
        if (count == 0){
            percent2.setText("0.0%");
        }
        else {
            percent2.setText(String.format("%.2f",per)+"%");
        }
        if (per > 80){
            percent2.setTextColor(Color.parseColor("#44BF49"));
        }
        else if (per > 50 && per < 80){
            percent2.setTextColor(Color.parseColor("#CAC06A"));
        }
        else {
            percent2.setTextColor(Color.parseColor("#E91E1E"));
        }

        txtname.setText(txtname.getText() + rollno.get(0).getName());
        txtroll.setText(txtroll.getText() + rollno.get(0).getRollNo());
        sec1.setText(sec1.getText() + rollno.get(0).getSection());

        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1; // Note: January is 0
        year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,1);
        spn2.setSelection(month - 1);
        List<Integer> list = database_holder.attendence_dao().getattnd(rollno.get(0).getRollNo(),month,year,"PBL");
        create(month,year,list);


        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sub = (String) adapterView.getItemAtPosition(i);
                gridLayout.removeAllViews();
                List<Integer> list = database_holder.attendence_dao().getattnd(rollno.get(0).getRollNo(),month,year,sub);
                create(month,year,list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gridLayout.removeAllViews();
                month = i + 1;
                List<Integer> list = database_holder.attendence_dao().getattnd(rollno.get(0).getRollNo(),month,year,sub);
                create(month,year,list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gridLayout.removeAllViews();
                year = 2023 + i;
                List<Integer> list = database_holder.attendence_dao().getattnd(rollno.get(0).getRollNo(),month,year,sub);
                create(month,year,list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void create(int month,int year,List<Integer> list){
        HashSet<Integer> set = new HashSet<>(list);
        Log.d("day",set.size() + " " + list.size());

        Drawable drawable = getResources().getDrawable(R.drawable.txt_bg);
        drawable.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_ATOP);

        //List<Attendence> lst = database_holder.attendence_dao().getattnd();
        for (int i=0; i<7; i++){
            TextView textView = new TextView(this);
            textView.setText(days[i]);
            textView.setBackground(drawable);
            textView.setGravity(Gravity.CENTER);
            gridLayout.addView(textView);
        }

        Calendar c = Calendar.getInstance();
        c.set(year,month - 1,1);
        res = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day = c.get(Calendar.DAY_OF_WEEK);
        Log.d("day",day + " " + month + " " + year);

        GridLayout.Spec rowSpec = GridLayout.spec(2);
        GridLayout.Spec colSpec = GridLayout.spec(day - 1);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);

        int j = day;
        Drawable drawable1 = getResources().getDrawable(R.drawable.txt_bg);
        if (set.contains(1)){
            drawable1.setColorFilter(Color.parseColor("#8BC34A"), PorterDuff.Mode.SRC_ATOP);
        }
        else if (j == 1 || j == 7){
            drawable1.setColorFilter(Color.parseColor("#E4D974"), PorterDuff.Mode.SRC_ATOP);
        }
        else {
            drawable1.setColorFilter(Color.parseColor("#E66464"), PorterDuff.Mode.SRC_ATOP);
        }
        TextView textView = new TextView(this);
        textView.setText(Integer.toString(1));
        textView.setBackground(drawable1);
        textView.setGravity(Gravity.CENTER);
        gridLayout.addView(textView,layoutParams);
        j++;
        if (j == 8){
            j = 1;
        }

        for (int i=2; i<=res; i++){
            Log.d("week",j +" ");
            Drawable drawable2 = getResources().getDrawable(R.drawable.txt_bg);
            if (set.contains(i)){
                drawable2.setColorFilter(Color.parseColor("#8BC34A"), PorterDuff.Mode.SRC_ATOP);
            }
            else if (j == 1 || j == 7){
                drawable2.setColorFilter(Color.parseColor("#E4D974"), PorterDuff.Mode.SRC_ATOP);
            }
            else {
                drawable2.setColorFilter(Color.parseColor("#E66464"), PorterDuff.Mode.SRC_ATOP);
            }
            textView = new TextView(this);
            textView.setText(Integer.toString(i));
            textView.setBackground(drawable2);
            textView.setGravity(Gravity.CENTER);
            gridLayout.addView(textView);
            j++;
            if (j == 8){
                j = 1;
            }
        }
    }
}