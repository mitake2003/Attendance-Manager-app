package com.example.mobileattendancemanager.DB_holder;

import android.content.Context;
import android.service.autofill.UserData;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobileattendancemanager.Table.Attendence;
import com.example.mobileattendancemanager.Table.CountFreq;
import com.example.mobileattendancemanager.Table.Logindata;
import com.example.mobileattendancemanager.database_DAO.Attendence_Dao;
import com.example.mobileattendancemanager.database_DAO.Count_Dao;
import com.example.mobileattendancemanager.database_DAO.Login_Dao;

@Database(entities = {Logindata.class, Attendence.class, CountFreq.class},version = 28)
public abstract class Database_holder extends RoomDatabase {
    public abstract Login_Dao login_dao();
    public abstract Attendence_Dao attendence_dao();
    public abstract Count_Dao count_dao();
    private static final String DB_NAME = "AttendanceDB";
    private static Database_holder instance;

    public static synchronized Database_holder getDB(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,Database_holder.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
