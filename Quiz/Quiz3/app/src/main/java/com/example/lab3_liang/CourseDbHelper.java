package com.example.lab3_liang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDbHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "courses.db";
    private final static int DATABASE_VERSION = 1;

    public CourseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE = "CREATE TABLE " + CourseInfoContract.Courses.TABLE_NAME + "(" +
            CourseInfoContract.Courses._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CourseInfoContract.Courses.COURSE_ID + " TEXT NOT NULL, " +
            CourseInfoContract.Courses.PROF_NAME + " TEXT NOT NULL " + ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ CourseInfoContract.Courses.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //create all tables sqLiteDatabase.execSQL(CREATE_TABLE);
        //create all tables sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop existing tables
        sqLiteDatabase.execSQL(DROP_TABLE); //start over onCreate(sqLiteDatabase);
        //start over
        onCreate(sqLiteDatabase);
    }
}
