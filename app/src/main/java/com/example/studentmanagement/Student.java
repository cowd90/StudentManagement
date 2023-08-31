package com.example.studentmanagement;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private String stName, stBirthday;
    private String gender;
    public Student(){}
    public Student(String stName, String stBirthday, String gender) {
        this.stName = stName;
        this.stBirthday = stBirthday;
        this.gender = gender;
    }
    public String getStName() {
        return stName;
    }
    public String getStBirthday() {
        return stBirthday;
    }
    public String getGender() {return gender;}

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setStBirthday(String stBirthday) {
        this.stBirthday = stBirthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NonNull
    @Override
    public String toString() {
        return this.stName +" - "+ this.stBirthday +" - "+ this.gender;
    }


}