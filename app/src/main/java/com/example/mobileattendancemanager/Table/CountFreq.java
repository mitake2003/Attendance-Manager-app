package com.example.mobileattendancemanager.Table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "countfreq")
public class CountFreq {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "subject")
    public String sub;

    @ColumnInfo(name = "section")
    public String section;
    public CountFreq(){};

    @Ignore
    public CountFreq(int id,String sub){
        this.id = id;
        this.sub = sub;
    }

    @Ignore
    public CountFreq(String sub,String sec){
        this.sub = sub;
        this.section = sec;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
