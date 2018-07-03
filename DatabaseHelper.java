package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "Property_Assessment.db";
    public static final String TABLE_NAME = "Attributes";
    public static final String COL1 = "measurement";
    public static final String COL2 = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"onCreate");
        db.execSQL("create table " + TABLE_NAME + " (measurement integer,date integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }

    public boolean insertData(String measurement, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,measurement);
        contentValues.put(COL2,date);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()  {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
