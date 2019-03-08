package com.example.deeppatel.markcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deeppatel.markcalculator.DataBaseHelper.DataBaseHelper;

public class MainActivity extends Activity {

    //Field Variables
    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;
    private Button btn_signup;
    private Intent intent;
    DataBaseHelper helper =  new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signUp);

        Login();// For login info
        Signup(); // For signup info
    }

    public void Login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting text of username and password
                String unamestr = edt_username.getText().toString();
                String passstr = edt_password.getText().toString();

                //Sending the username to other activities using intent
                intent = new Intent();
                intent.putExtra("uname",unamestr);

                //Searching the password and name from database class
                String password = helper.searchPass(unamestr);
                String name = helper.searchName(unamestr);

                // Sending the name and username to other classes using shared preferences
                SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("name",name);
                editor.putString("uname", unamestr);
                editor.apply();

                //If the username or password field is empty
                if(unamestr.isEmpty() || passstr.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter both username and password", Toast.LENGTH_LONG).show();
                }
                else {
                    //If password in the database is same as the password entered by the user
                    if (passstr.equals(password)) {
                        Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_LONG).show();
                        //Opening Display class if the above condition satisfies
                        intent = new Intent(MainActivity.this,Display.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Information is incorrect....Login Unsucceesful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void Signup(){
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opening the signup class using intent
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
}
