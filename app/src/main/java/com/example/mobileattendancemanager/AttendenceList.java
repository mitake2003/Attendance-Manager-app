package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Attendence;
import com.example.mobileattendancemanager.Table.CountFreq;
import com.example.mobileattendancemanager.Table.Logindata;
import com.example.mobileattendancemanager.database_DAO.Count_Dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AttendenceList extends AppCompatActivity {
    ListView listView;
    Button submit;
    int day,month,year,n;
    String sub = "PBL",sec = "SE1";
    TextView txtdate;
    Spinner spn4,spn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_list);
        listView = findViewById(R.id.listView);
        submit = findViewById(R.id.submit);
        txtdate = findViewById(R.id.txtdate);
        spn4 = findViewById(R.id.spn4);
        spn5 = findViewById(R.id.spn5);

        HashSet<String> set = new HashSet<>();
        HashSet<Integer> color = new HashSet<>();
        Calendar calendar = Calendar.getInstance();

        // Get the day, month, and year values
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1; // Note: January is 0
        year = calendar.get(Calendar.YEAR);
        txtdate.setText(day + "/" + month + "/" + year);
        Log.d("date",day + " " + month + " " + year);

        Database_holder database_holder = Database_holder.getDB(this);
        List<Logindata> stud = database_holder.login_dao().getStud(sec);
        n = stud.size();

        Custom_Adapter adapter = new Custom_Adapter(this,stud);
        listView.setAdapter(adapter);

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day1 = calendar.get(Calendar.DAY_OF_MONTH);
                int month1 = calendar.get(Calendar.MONTH);
                int year1 = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AttendenceList.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year2, int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        day = dayOfMonth;
                        month = monthOfYear + 1;
                        year = year2;
                        txtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year2);
                    }
                },year1,month1,day1);
                datePickerDialog.show();
            }
        });

        spn5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sec = (String) adapterView.getItemAtPosition(i);
                List<Logindata> stud = database_holder.login_dao().getStud(sec);
                n = stud.size();
                set.clear();
                adapter.clear();
                adapter.addAll(stud);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!color.contains(i)){
                    view.setBackgroundColor(Color.parseColor("#88B555"));
                    color.add(i);
                }
                else{
                    view.setBackgroundColor(Color.parseColor("#DC515C"));
                    color.remove(i);
                }
                TextView present = view.findViewById(R.id.present);
                TextView roll = view.findViewById(R.id.roll);
                String txt = present.getText().toString();
                String rollno = roll.getText().toString();
                if (txt.isEmpty()){
                    set.add(rollno);
                    present.setText("P");
                }
                else {
                    set.remove(rollno);
                    present.setText("");
                }
            }
        });

        spn4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sub = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i<n; i++){
                    Logindata lgn = stud.get(i);
                    Attendence attend;
                    if (set.contains(lgn.getRollNo())){
                        attend = new Attendence(lgn.getName(),lgn.getRollNo(),sub,day,month,year,sec,'P');
                    }
                    else {
                        attend = new Attendence(lgn.getName(),lgn.getRollNo(),sub,day,month,year,sec,'A');
                    }
                    database_holder.attendence_dao().insert(attend);
                }
                CountFreq count = new CountFreq(sub,sec);
                database_holder.count_dao().insert(count);
                Toast.makeText(AttendenceList.this, "Attendence uploaded succesfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}