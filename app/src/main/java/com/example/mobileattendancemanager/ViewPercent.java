package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Attendence;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.Calendar;
import java.util.List;

public class ViewPercent extends AppCompatActivity {
    ListView listpercent;
    Button back;
    Spinner spnSub,spnSec1;
    String sub = "PBL",sec = "SE1";

    List<Logindata> attend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_percent);
        listpercent = findViewById(R.id.listpercent);
        back = findViewById(R.id.back);
        spnSub = findViewById(R.id.spnSub);
        spnSec1 = findViewById(R.id.spnSec1);

        Intent intent = new Intent(this,studAttendance.class);

        Database_holder database_holder = Database_holder.getDB(this);
        attend = database_holder.login_dao().getStud(sec);

        ScoreAdapter adapter = new ScoreAdapter(this,attend,sub);
        listpercent.setAdapter(adapter);

        spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sub = (String) adapterView.getItemAtPosition(i);
                adapter.clear();
                adapter.addSub(sub);
                adapter.addAll(attend);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnSec1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sec = (String) adapterView.getItemAtPosition(i);
                attend = database_holder.login_dao().getStud(sec);
                adapter.clear();
                adapter.addAll(attend);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listpercent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt = view.findViewById(R.id.name2);
                TextView txt1 = view.findViewById(R.id.roll1);
                String name = txt.getText().toString();
                String roll = txt1.getText().toString();
                List<Logindata> lgdata = database_holder.login_dao().getuser(name,roll);
                Logindata logindata = lgdata.get(0);
                intent.putExtra("user",logindata.getUserName());
                intent.putExtra("pass",logindata.getPassword());
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}