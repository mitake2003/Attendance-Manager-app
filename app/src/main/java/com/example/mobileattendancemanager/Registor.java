package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.ArrayList;
import java.util.List;

public class Registor extends AppCompatActivity {
    EditText name,roll,user,password;
    Button rtr3;
    Spinner spnSec;
    String name1,roll1,user1,password1,sec = "SE1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        rtr3 = findViewById(R.id.rtr3);
        spnSec = findViewById(R.id.spnSec);

        Intent intent = new Intent(this,Login.class);
        intent.putExtra("title","Student Login");

        Database_holder database_holder = Database_holder.getDB(this);

        spnSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sec = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rtr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = name.getText().toString();
                roll1 = roll.getText().toString();
                user1 = user.getText().toString();
                password1 = password.getText().toString();
                if (!name1.isEmpty() && !roll1.isEmpty() && !user1.isEmpty() && !password1.isEmpty()){
                    Logindata ld = new Logindata(roll1,name1,user1,password1,sec);
                    database_holder.login_dao().insert(ld);
                    startActivity(intent);
                    finish();
                }
                else {
                    userNotify("Field are Empty");
                }
            }
        });

    }

    public void userNotify(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}