package com.example.mobileattendancemanager.database_DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileattendancemanager.Table.Logindata;

import java.util.List;

@Dao
public interface Login_Dao {

    @Insert
    void insert(Logindata ld);

    @Query("select * from Userdata")
    List<Logindata> getAll();
    @Query("select * from Userdata where section = :sec and department is null")
    List<Logindata> getStud(String sec);

    @Query("select * from userdata where name = :name and rollNo = :roll and department is null")
    List<Logindata> getuser(String name,String roll);
    @Query("select * from Userdata where username = :user and password = :pass and department is null")
    List<Logindata> getRollno(String user,String pass);

    @Query("select * from userdata where username = :user and password = :pass and department is not null")
    List<Logindata> getTeach(String user,String pass);
}
