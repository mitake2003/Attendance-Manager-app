package com.example.mobileattendancemanager.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendence")
public class Attendence {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)

    public int id;

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "rollno")
    public String rollNo;

    @ColumnInfo(name = "subject")
    public String subject;

    @ColumnInfo(name = "day")
    public int day;

    @ColumnInfo(name = "month")
    public int month;

    @ColumnInfo(name = "year")
    public int year;

    @ColumnInfo(name = "section")
    public String section;
    @ColumnInfo(name = "status")
    public char status;

    public Attendence(){};

    @Ignore
    public Attendence(String name,String rollNo, String subject, int day, int month, int year,String sec,char st) {
        this.name = name;
        this.rollNo = rollNo;
        this.subject = subject;
        this.day = day;
        this.month = month;
        this.year = year;
        this.status = st;
        this.section = sec;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
