package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Logindata;

public class Register2 extends AppCompatActivity {
    EditText name1,id1,dep,email,user1,password1;
    Button rtr4;
    String name2,id2,dep1,email1,user3,password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        name1 = findViewById(R.id.name1);
        id1 = findViewById(R.id.id1);
        dep = findViewById(R.id.dep);
        email = findViewById(R.id.email);
        user1 = findViewById(R.id.user1);
        password1 = findViewById(R.id.password1);
        rtr4 = findViewById(R.id.rtr4);

        Database_holder database_holder = Database_holder.getDB(this);
        Intent intent = new Intent(this,Login.class);
        intent.putExtra("title","Teacher Login");

        rtr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2 = name1.getText().toString();
                id2 = id1.getText().toString();
                dep1 = dep.getText().toString();
                email1 = email.getText().toString();
                password2 = password1.getText().toString();
                user3 = user1.getText().toString();
                if (!name2.isEmpty() && !id2.isEmpty() && !dep1.isEmpty() && !email1.isEmpty() && !password2.isEmpty() && !user3.isEmpty()){
                    Logindata logindata = new Logindata(id2,name2,user3,password2,dep1,email1);
                    database_holder.login_dao().insert(logindata);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Register2.this, "Field are Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}