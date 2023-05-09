package com.example.mobileattendancemanager.database_DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileattendancemanager.Table.CountFreq;

@Dao
public interface Count_Dao {
    @Insert
    void insert(CountFreq countFreq);

    @Query("Select count(*) from countfreq where subject = :sub and section = :sec")
    int getCount(String sub,String sec);

    @Query("Select count(*) from countfreq where section = :sec")
    int getCount2(String sec);
}
