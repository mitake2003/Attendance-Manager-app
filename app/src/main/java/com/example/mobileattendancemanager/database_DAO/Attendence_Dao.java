package com.example.mobileattendancemanager.database_DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileattendancemanager.Table.Attendence;

import java.util.List;

@Dao
public interface Attendence_Dao {
    @Insert
    void insert(Attendence attendence);
    @Query("select * from attendence")
    List<Attendence> getAll();

    @Query("Select count(rollno) from attendence where rollno = :rollno and status = 80 and subject = :sub and section = :sec")
    int getRollcnt(String rollno,String sub,String sec);

    @Query("Select count(rollno) from attendence where rollno = :rollno and status = 80 and section = :sec")
    int getRollcnt2(String rollno,String sec);
    @Query("select * from attendence group by rollno having day = :d and month = :m and year = :y ")
    List<Attendence> getdmy(int d,int m,int y);

    @Query("select day from attendence where rollno = :roll and month = :m and year = :y and subject = :sub and status = 80")
    List<Integer> getattnd(String roll,int m,int y,String sub);
}
