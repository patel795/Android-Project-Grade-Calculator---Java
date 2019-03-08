package com.example.deeppatel.markcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deeppatel.markcalculator.model.Data;

public class Display extends Activity {

    //Field Variable
    private TextView txt_display; //To display the Main page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Initializing
        txt_display = findViewById(R.id.txt_display);
        getData();// method to get the name of the user
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
            Toast.makeText(this,"Home", Toast.LENGTH_LONG).show();
        }

        else if(id == R.id.action_semester){
            getData();
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

    public void getData(){
        //Getting the name passed from Main Activity
        SharedPreferences myPrefs = getSharedPreferences("myPrefs",MODE_PRIVATE);

        StringBuilder builder = new StringBuilder("              Hello ");
        builder.append(myPrefs.getString("name",null)).append("\n");
        txt_display.setText(builder.toString());
    }
}
