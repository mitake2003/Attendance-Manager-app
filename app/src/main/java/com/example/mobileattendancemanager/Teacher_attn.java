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

public class Teacher_attn extends AppCompatActivity {
    Button button,button2,back3;

    TextView txt1,txt2,txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attn);
        button = findViewById(R.id.btn);
        button2 = findViewById(R.id.btn2);
        back3 = findViewById(R.id.back3);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        Intent intent = new Intent(this,AttendenceList.class);
        Intent intent1 = new Intent(this,ViewPercent.class);

        Intent intent2 = getIntent();
        String user = intent2.getStringExtra("user");
        String pass = intent2.getStringExtra("pass");

        Database_holder database_holder = Database_holder.getDB(this);
        List<Logindata> list = database_holder.login_dao().getTeach(user,pass);

        txt1.setText(txt1.getText() + list.get(0).getName());
        txt2.setText(txt2.getText() + list.get(0).getRollNo());
        txt3.setText(txt3.getText() + list.get(0).getDepartment());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}