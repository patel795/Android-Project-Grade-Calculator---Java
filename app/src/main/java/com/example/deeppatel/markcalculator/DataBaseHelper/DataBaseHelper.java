package com.example.deeppatel.markcalculator.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deeppatel.markcalculator.model.Data;

/**
 * Created by Deep Patel on 2017-12-25.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    //Creating different columns and assigning names to create a database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_NAME = "data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASSWORD = "password";

    //DDL for creating a table
    private static final String TABLE_CREATE = "create table data (id integer primary key not null," +
            "name text not null, uname text not null, password text not null);";

    //Instance members for accessing the database
    private SQLiteDatabase db;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Called by the runtime if the database doesnt exist yet
        //The runtime create the database for us, but we have to
        //create the structure here
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertData(Data data){

        //get writable since inserting a record
        db = this.getWritableDatabase();

        //use content values to group field names and values
        ContentValues values = new ContentValues();

        String query = "Select * from data";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_UNAME, data.getUname());
        values.put(COLUMN_PASSWORD, data.getPassword());

        //clase the database
        cursor.close();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String searchPass(String uname){
        //get readable data
        db = this.getReadableDatabase();
        String query = "select uname, password from "+ TABLE_NAME;

        //get all records from data table
        Cursor cursor = db.rawQuery(query, null);

        String username,password;
        password = "Not found";
        //Until the cursor moves forward
        if(cursor.moveToFirst()){
            do{
                username = cursor.getString(0);

                if(username.equals(uname)){
                    password = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        //close the cursor and database
        cursor.close();
        return password;
    }

    public String searchName(String uname){
        //get readable data
        db = this.getReadableDatabase();
        String query = "select uname, name from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String username,name;
        name = "Not found";
        //Until the cursor moves forward
        if(cursor.moveToFirst()){
            do{
                username = cursor.getString(0);

                if(username.equals(uname)){
                    name = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        //close the cursor and database
        cursor.close();
        return name;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop the table and call onCreate() not checking for
        //version info
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
