package com.example.mobileattendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileattendancemanager.DB_holder.Database_holder;
import com.example.mobileattendancemanager.Table.Logindata;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    TextView txt;
    EditText user2,password2;
    Button lgn3;
    String username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt = findViewById(R.id.txt);
        user2 = findViewById(R.id.user2);
        password2 = findViewById(R.id.password2);
        lgn3 = findViewById(R.id.lgn3);

        Intent intent = getIntent();
        String Title = intent.getStringExtra("title");
        txt.setText(Title);

        Intent intent1 = new Intent(this,Teacher_attn.class);
        Intent intent2 = new Intent(this,Student_attn.class);

        //List<Logindata> lst = DataSingleton.getInstance().getMyObjects();
        Database_holder database_holder = Database_holder.getDB(this);
        List<Logindata> lst = database_holder.login_dao().getAll();

        Log.d("data","hello" + lst.size());

        lgn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user2.getText().toString();
                pass = password2.getText().toString();
                if (!username.isEmpty() && !pass.isEmpty()){
                    boolean flag = true;
                    for (int i=0; i<lst.size(); i++){
                        Logindata lgndata = lst.get(i);
                        if (username.equals(lgndata.getUserName()) && pass.equals(lgndata.getPassword())){
                            if (Title.equals("Teacher Login") && lgndata.getDepartment() != null){
                                flag = false;
                                intent1.putExtra("user",username);
                                intent1.putExtra("pass",pass);
                                startActivity(intent1);
                                finish();
                            }
                            else if (Title.equals("Student Login") && lgndata.getDepartment() == null){
                                flag = false;
                                intent2.putExtra("user",username);
                                intent2.putExtra("pass",pass);
                                startActivity(intent2);
                            }
                        }
                    }
                    if (flag){
                        notifyUser("Username and password might be wrong");
                    }
                    else {
                        notifyUser("Login successful");
                    }

                }
                else {
                    notifyUser("Field are Empty");
                }
            }
        });

    }

    public void notifyUser(String msg){
        Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();
    }
}