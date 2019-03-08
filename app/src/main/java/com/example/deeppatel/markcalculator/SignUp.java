package com.example.deeppatel.markcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deeppatel.markcalculator.DataBaseHelper.DataBaseHelper;
import com.example.deeppatel.markcalculator.model.Data;

public class SignUp extends Activity {

    //Field Variables
    private EditText edt_name;
    private EditText edt_username;
    private EditText edt_password;
    private EditText edt_confirm;
    private Button btn_signup;
    private Button btn_cancel;
    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initializing
        edt_name = findViewById(R.id.edt_name);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_confirm = findViewById(R.id.edt_confirm);
        btn_signup = findViewById(R.id.btn_signUp);
        btn_cancel = findViewById(R.id.btn_cancel);

        Signup(); //To manage the signup function
        Cancel(); //To cancel and go back to main page
    }

    public void Signup(){
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the data entered by the user
                String namestr = edt_name.getText().toString();
                String unamestr = edt_username.getText().toString();
                String pass1str = edt_password.getText().toString();
                String pass2str = edt_confirm.getText().toString();

                //If any of the field entered by the user is empty
                if(namestr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all information", Toast.LENGTH_LONG).show();
                }
                else {
                    //Checks if password and confirm-password are same
                    if (pass1str.equals(pass2str)) {
                        Data data = new Data();
                        //Setting the value of username, name and password to the database to store
                        data.setName(namestr);
                        data.setUname(unamestr);
                        data.setPassword(pass1str);

                        //This inserts the values to database
                        helper.insertData(data);
                        //Moving to main activity after singup is successful
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "SignUp Successful", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void Cancel(){
        //Cancels and go back to main page
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
