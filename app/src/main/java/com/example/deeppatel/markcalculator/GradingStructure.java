package com.example.deeppatel.markcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deeppatel.markcalculator.DataBaseHelper.DataBaseHelper;
//import com.example.deeppatel.markcalculator.DataBaseHelper.DataBaseHelper2;
//import com.example.deeppatel.markcalculator.model.Data2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradingStructure extends Activity {

    //Field Variable
    private EditText edt_ass1;
    private EditText edt_ass2;
    private EditText edt_ass3;
    private EditText edt_project;
    private EditText edt_midterm;
    private EditText edt_final;
    private Button btn_total;
    private Button btn_save;
    private TextView txt_total;
    private String courseName;
    private double total;
    private String uname;
    private StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grading_structure);

        //Initializing
        edt_ass1 = findViewById(R.id.edt_ass1);
        edt_ass2 = findViewById(R.id.edt_ass2);
        edt_ass3 = findViewById(R.id.edt_ass3);
        edt_project = findViewById(R.id.edt_project);
        edt_midterm = findViewById(R.id.edt_midexam);
        edt_final = findViewById(R.id.edt_finalexam);
        btn_total = findViewById(R.id.btn_total);
        btn_save = findViewById(R.id.btn_save);
        txt_total = findViewById(R.id.txt_total);
        getData();//To get the username and coursename
        total();//To get the total
        save();// To save the contents of the assignments in a file
        read();// To read the contents of the assignments which was stored by save
    }

    public void total(){
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double ass1 = Double.parseDouble(edt_ass1.getText().toString());
                    double ass2 = Double.parseDouble(edt_ass2.getText().toString());
                    double ass3 = Double.parseDouble(edt_ass3.getText().toString());
                    double project = Double.parseDouble(edt_project.getText().toString());
                    double midterm = Double.parseDouble(edt_midterm.getText().toString());
                    double finalexam = Double.parseDouble(edt_final.getText().toString());

                    total = (ass1 + ass2 + ass3 + project + midterm + finalexam);

                    //Different conditions according to various cases
                    if(ass1 > 10){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for ass1",Toast.LENGTH_LONG).show();
                    }
                    else if(ass2 > 10){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for ass2",Toast.LENGTH_LONG).show();
                    }
                    else if(ass3 > 10){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for ass3",Toast.LENGTH_LONG).show();
                    }
                    else if(project > 15){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for project",Toast.LENGTH_LONG).show();
                    }
                    else if(midterm > 25){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for midterm",Toast.LENGTH_LONG).show();
                    }
                    else if(finalexam > 30){
                        Toast.makeText(GradingStructure.this,"Grade is out of range for finalexam",Toast.LENGTH_LONG).show();
                    }
                    else{
                        txt_total.setText(String.format("%.2f", total));
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Please fill all information", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getData(){
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");//Getting the coursename from Semester class

        //For getting the username from MainActivity
        SharedPreferences myPrefs = getSharedPreferences("myPrefs",MODE_PRIVATE);
        builder = new StringBuilder("");
        builder.append(myPrefs.getString("uname",null)).append("\n");
    }

    public void save(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Filename to save
                String fileName;

                //declare output stream we'll use
                FileOutputStream fileoutputStream;

                //Naming the filename as usernamecourseName.txt
                fileName = ("" + builder + courseName + ".txt");

                try{
                    //get the FileOutputStream
                    fileoutputStream = openFileOutput(fileName, MODE_PRIVATE);
                }catch (FileNotFoundException e){
                    Toast.makeText(getApplicationContext(),"File Not Found", Toast.LENGTH_LONG).show();
                    return;
                }

                //call out writeToFile method passing in FileOutputStream
                writeToFile(fileoutputStream);
            }
        });
    }

    private void writeToFile(FileOutputStream fos) {
        //Getting the data entered by user
        String ass1 = edt_ass1.getText().toString();
        String ass2 = edt_ass2.getText().toString();
        String ass3 = edt_ass3.getText().toString();
        String project = edt_project.getText().toString();
        String midtem = edt_midterm.getText().toString();
        String finalExam = edt_final.getText().toString();

        if(ass1.isEmpty() || ass2.isEmpty() || ass3.isEmpty() || project.isEmpty() || midtem.isEmpty() || finalExam.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all information", Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(ass1) > 10){
            Toast.makeText(GradingStructure.this,"Grade is out of range for ass1",Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(ass2) > 10){
            Toast.makeText(GradingStructure.this,"Grade is out of range for ass2",Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(ass3) > 10){
            Toast.makeText(GradingStructure.this,"Grade is out of range for ass3",Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(project) > 15){
            Toast.makeText(GradingStructure.this,"Grade is out of range for project",Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(midtem) > 25){
            Toast.makeText(GradingStructure.this,"Grade is out of range for midterm",Toast.LENGTH_LONG).show();
        }
        else if(Double.parseDouble(finalExam) > 30){
            Toast.makeText(GradingStructure.this,"Grade is out of range for finalexam",Toast.LENGTH_LONG).show();
        }
        else{
            //create strings for the file
            String ass1File = String.format("%s", ass1);
            String ass2File = String.format("%s", ass2);
            String ass3File = String.format("%s", ass3);
            String projectFile = String.format("%s", project);
            String midtermFile = String.format("%s", midtem);
            String finalFile = String.format("%s", finalExam);

            //wrap a PrintWriter around FileOutputStream for
            //convenience of println
            PrintWriter writer = new PrintWriter(fos);

            //print the lines
            writer.println(ass1);
            writer.println(ass2);
            writer.println(ass3);
            writer.println(project);
            writer.println(midtem);
            writer.println(finalExam);

            //close the PrintWriter, which will also close fileoutputstream
            writer.close();

            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(GradingStructure.this,Semester.class);
            startActivity(intent);
        }
    }

    public void read(){
        //Filename to open
        String fileName;

        //declare input stream and files we'll use
        FileInputStream fis;

        File file;

        //Name of the file which is usernamecourseName.txt
        fileName = ("" + builder + courseName + ".txt");

        //Get the FileInputStream
        try{
            fis = openFileInput(fileName);
        }catch (FileNotFoundException e){
            Toast.makeText(getApplicationContext(),"File Not Found", Toast.LENGTH_LONG).show();
            return;
        }

        //call our readFile method passing in FileInputStream
        readFile(fis);
    }

    private void readFile(FileInputStream fis) {
        Scanner scanner;

        //Use a stringbuilder to concatenate text
        StringBuilder text = new StringBuilder();

        //wrap a scanner around FileInputStream for
        //convenience of nextLine since reading text
        scanner = new Scanner(fis);

        //Using delimiter
        scanner.useDelimiter(",");

        //Keep reading the file as long as there's data
        while (scanner.hasNext()) {
            //Saving each line in different strings below
             String ass1 = scanner.nextLine();
             String ass2 = scanner.nextLine();
             String ass3 = scanner.nextLine();
             String project = scanner.nextLine();
             String midterm = scanner.nextLine();
             String finalExam = scanner.nextLine();
             edt_ass1.setText(ass1);
             edt_ass2.setText(ass2);
             edt_ass3.setText(ass3);
             edt_project.setText(project);
             edt_midterm.setText(midterm);
             edt_final.setText(finalExam);
        }

        //close the scanner which will also close fileinputstream
        scanner.close();

    }
}
