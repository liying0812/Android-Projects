package com.example.lab3_liang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddFragment extends Fragment {

    private Button btn_save;
    private EditText et_course, et_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btn_save = view.findViewById(R.id.btn_save);
        et_course = view.findViewById(R.id.et_course_id);
        et_name = view.findViewById(R.id.et_name);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentInfoIntoDatabase();
            }
        });
        return view;
    }

    private Boolean checkDup(String course, String name) {
        CourseDbHelper dbHelper = new CourseDbHelper(getActivity());

        // get data repository in read mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {CourseInfoContract.Courses.COURSE_ID, CourseInfoContract.Courses.PROF_NAME};

        String selection = CourseInfoContract.Courses.COURSE_ID+ " LIKE? " + "AND " + CourseInfoContract.Courses.PROF_NAME+ " LIKE? ";
        String[] selectionArgs = {"%"+course+"%","%"+name+"%"};

        // ORDER BY professor name;
        Cursor cursor = db.query(CourseInfoContract.Courses.TABLE_NAME, columns, selection, selectionArgs, null, null,
                null);

        if (cursor != null && cursor.getCount()>0) {
            // duplicate found
            return true;
        }

        return false;
    }

    private void saveStudentInfoIntoDatabase() {
        String courseNum = et_course.getText().toString();
        String name = et_name.getText().toString();

        if (courseNum.isEmpty()) {
            Toast.makeText(getActivity(), "ID cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // check if duplicate
        if (checkDup(courseNum, name) == true) {
            Toast.makeText(this.getContext(), "Insertion failed. Duplicate not allowed.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Add the record to the database
        CourseDbHelper dbHelper = new CourseDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // put method: id, name, email
        contentValues.put(CourseInfoContract.Courses.COURSE_ID, courseNum);
        contentValues.put(CourseInfoContract.Courses.PROF_NAME, name);

        // insert into db
        long recordId = db.insert(CourseInfoContract.Courses.TABLE_NAME, null, contentValues);
        // close db
        db.close();

        if (recordId == -1) {
            Toast.makeText(this.getContext(), "Insertion failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Successfully added course " + courseNum + " in the db", Toast.LENGTH_SHORT).show();
        }

        // clear all edit texts
        et_course.setText("");
        et_name.setText("");
    }
}