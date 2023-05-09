package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.List;

public class Student_attn extends AppCompatActivity {

    Button viewAttn,back2;

    TextView txt4,txt5,txt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attn);
        viewAttn = findViewById(R.id.viewAttn);
        back2 = findViewById(R.id.back2);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);

        Database_holder database_holder = Database_holder.getDB(this);


        Intent intent = getIntent();
        Intent intent1 = new Intent(this,studAttendance.class);

        intent1.putExtra("user",intent.getStringExtra("user"));
        intent1.putExtra("pass",intent.getStringExtra("pass"));

        List<Logindata> list = database_holder.login_dao().getRollno(intent.getStringExtra("user"),intent.getStringExtra("pass"));

        txt4.setText(list.get(0).getName());
        txt5.setText(list.get(0).getRollNo());
        txt6.setText(list.get(0).getSection());
        viewAttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}