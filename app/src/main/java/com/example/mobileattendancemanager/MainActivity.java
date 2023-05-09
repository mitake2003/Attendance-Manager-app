package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Logindata;

public class MainActivity extends AppCompatActivity {
    Button lgn1,lgn2,rtr1,rtr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lgn1 = findViewById(R.id.lgn1);
        lgn2 = findViewById(R.id.lgn2);
        rtr1 = findViewById(R.id.rtr1);
        rtr2 = findViewById(R.id.rtr2);

//        Database_holder database_holder = Database_holder.getDB(this);
//
//        Logindata logindata = new Logindata("46","teacher","f","g","comp","lsdjf");
//        database_holder.login_dao().insert(logindata);

//        for (int i=0; i<15; i++){
//            Logindata ld = new Logindata(Integer.toString(i),"iosyhwel","f","g");
//            database_holder.login_dao().insert(ld);
//        }


        Intent intent = new Intent(this,Login.class);
        Intent intent1 = new Intent(this,Registor.class);
        Intent intent2 = new Intent(this,Register2.class);

        lgn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title","Teacher Login");
                startActivity(intent);
            }
        });

        lgn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title","Student Login");
                startActivity(intent);
            }
        });

        rtr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        rtr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

    }
}