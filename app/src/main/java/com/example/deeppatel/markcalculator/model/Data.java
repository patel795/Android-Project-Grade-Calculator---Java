package com.example.deeppatel.markcalculator.model;

import android.content.Context;

import com.example.deeppatel.markcalculator.R;

import java.util.ArrayList;

/**
 * Created by Deep Patel on 2017-12-25.
 */

public class Data {
    //Variables
    private String name, uname, password, courseName1;

    public Data(String courseName1){
        this.courseName1 = courseName1;
    }

    public Data(){
    }

    //Getters and setters for name, uname , password, coursname1
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setUname(String uname){
        this.uname = uname;
    }

    public String getUname(){
        return this.uname;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCourseName1(){ return this.courseName1;}

    public void setCourseName1(String courseName1){ this.courseName1 = courseName1;}

    //Creating a array to store the data from strings.xml
    public static ArrayList<Data> getData(Context context){
        String[] sem1 = context.getResources().getStringArray(R.array.Sem1);
        String[] sem2 = context.getResources().getStringArray(R.array.Sem2);
        String[] sem3 = context.getResources().getStringArray(R.array.Sem3);

        ArrayList<Data> data = new ArrayList<>();

        for(int i = 0; i < sem1.length; i++){
            data.add(new Data(sem1[i]));
        }

        return data;
    }
}
