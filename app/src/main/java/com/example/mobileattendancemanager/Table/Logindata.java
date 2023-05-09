package com.example.mobileattendancemanager.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Userdata")
public class Logindata {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String rollNo;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "username")
    public String userName;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "department")
    public String department;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "section")
    public String section;
    public Logindata(){};
    @Ignore
    public Logindata(String roll, String name, String userName, String password,String section){
        this.rollNo = roll;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.section = section;
    }

    @Ignore
    public Logindata(String id,String name,String userName,String password,String department,String email){
        this.rollNo = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.email = email;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

