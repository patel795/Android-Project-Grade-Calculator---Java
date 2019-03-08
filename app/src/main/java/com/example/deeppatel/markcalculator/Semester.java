package com.example.deeppatel.markcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deeppatel.markcalculator.DataBaseHelper.DataBaseHelper;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_dropdown_item;

public class Semester extends Activity {

    //Field Variables
    private Spinner spn_semester;
    private ListView lst_course;
    private int id_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        //Initializing
        spn_semester = findViewById(R.id.spn_sem);
        lst_course = findViewById(R.id.lst_course);

        spn_adaptor();// To arrange the spinner view
        listview(); // To manage the listview functions
        spinner(); //Checks which position of spinner is clickede
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate the menu
        //this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        if(id == R.id.action_home){
            Intent intent = new Intent(this,Display.class);
            startActivity(intent);
        }

        else if(id == R.id.action_semester){
            Intent intent = new Intent(this,Semester.class);
            startActivity(intent);
        }

        else if(id == R.id.action_logout){
            Intent intent  = new Intent(this, MainActivity.class);
            //To clear the current session
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(this,"Logout Successful", Toast.LENGTH_LONG).show();
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public void spn_adaptor(){

        //Getting the data from strings.xml
        final String[] sem = getResources().getStringArray(R.array.Semester);

        //Creating an instance of ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, sem);

        //Setting the view which will be used in the spinner
        adapter.setDropDownViewResource(
                simple_spinner_dropdown_item
        );

        //Assigning the adaptor1 to the spinner
        spn_semester.setAdapter(adapter);
    }

    public void listadaptor1(){

        //Getting the data from strings.xml
        final String[] sem = getResources().getStringArray(R.array.Sem1);

        //Creating an instance of ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, sem);

        //Assigning the adapter to the listview
        lst_course.setAdapter(adapter);
    }

    public void listadaptor2(){

        //Getting the data from strings.xml
        final String[] sem = getResources().getStringArray(R.array.Sem2);

        //Creating an instance of ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, sem);

        //Assigning the adapter to the listview
        lst_course.setAdapter(adapter);
    }

    public void listadaptor3(){

        //Getting the data from strings.xml
        final String[] sem = getResources().getStringArray(R.array.Sem3);

        //Creating an instance of ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, sem);

        //Assigning the adapter to the listview
        lst_course.setAdapter(adapter);
    }

    public void listview(){
        lst_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String course = "";
                String[] courses;

                //Here id_database is the position of the spinner selected
                if(id_database == 0) {
                    for (int j = 0; j <= position; j++){
                        courses = getResources().getStringArray(R.array.Sem1);
                        course = courses[j];//Assigning the value of the array to a string called course
                    }
                }
                else if(id_database == 1){
                    for (int j = 0; j <= position; j++){
                        courses = getResources().getStringArray(R.array.Sem2);
                        course = courses[j];//Assigning the value of the array to a string called course
                    }
                }
                else if(id_database == 2){
                    for (int j = 0; j <= position; j++){
                        courses = getResources().getStringArray(R.array.Sem3);
                        course = courses[j];//Assigning the value of the array to a string called course
                    }
                }

                //Sending the value of coursname using intent to GradingStructure
                Intent intent = new Intent(getApplicationContext(),GradingStructure.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });
    }

    //Checks which position is clicked for spinner and then calls the listview adaptore
    //as per the position
    private void spinner() {
        spn_semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_database = position;
                    if(spn_semester.getSelectedItemPosition() == 0){
                        listadaptor1();
                    }
                    else if(spn_semester.getSelectedItemPosition() == 1){
                        listadaptor2();
                    }
                    else{
                        listadaptor3();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    //Nothing
            }
        });
    }
}
