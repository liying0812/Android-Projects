package com.example.lab3_liang;

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
import android.widget.TextView;
import android.widget.Toast;


public class SearchFragment extends Fragment {

    private Button btn_search;
    private EditText et_name;
    private TextView tv_result;

    private Button btn_delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btn_search = view.findViewById(R.id.btn_search);
        et_name = view.findViewById(R.id.et_search_name);
        tv_result = view.findViewById(R.id.tv_search_result);

        btn_delete = view.findViewById(R.id.btn_delete);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourse();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });

        return view;
    }

    private void searchCourse() {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // ToDo: Search the student in the database
        CourseDbHelper dbHelper = new CourseDbHelper((getActivity()));
        // get data repository in read mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Use the query() method, passing it selection criteria and desired columns (projection) for the
        //SQL command – SELECT course,name FROM TABLE_NAME WHERE name LIKE “%<entered_name>%” ORDER BY name;
        String[] columns = {CourseInfoContract.Courses.COURSE_ID, CourseInfoContract.Courses.PROF_NAME};

        String selection = CourseInfoContract.Courses.PROF_NAME+ " LIKE? ";
        String[] selectionArgs = {"%"+name+"%"};

        Cursor cursor = db.query(CourseInfoContract.Courses.TABLE_NAME, columns, selection,
                selectionArgs, null, null, CourseInfoContract.Courses.PROF_NAME);

        String result = "";
        while (cursor.moveToNext()) {
            String courseNum = cursor.getString(cursor.getColumnIndex(CourseInfoContract.Courses.COURSE_ID));
            String profName = cursor.getString(cursor.getColumnIndex(CourseInfoContract.Courses.PROF_NAME));

            result = result + "\n\nID: " + courseNum + "\nNAME: " + profName;
        }

        // close db
        db.close();

        // Check whether the result is empty, If empty, set result= ”No records Found”
        if (result.isEmpty()) {
            Toast.makeText(this.getContext(), "No name matches", Toast.LENGTH_SHORT).show();
        } else {
            tv_result.setText(result);
        }

        et_name.setText("");
    }

    private void deleteCourse() {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // ToDo: Delete the student with the input id from the database
        // TODO: Add the record to the database
        CourseDbHelper dbHelper = new CourseDbHelper(getActivity());
        // get data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // use delete() method to delete records where id = user-entered id
        String whereClause = CourseInfoContract.Courses.PROF_NAME + "=?";
        String[] whereArgs = {name};

        int count = db.delete(CourseInfoContract.Courses.TABLE_NAME, whereClause, whereArgs);

        // close db
        db.close();

        // check count value and return appropriate toast message
        if (count < 1) {
            Toast.makeText(this.getContext(), "No records deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Deleted " + count + " records", Toast.LENGTH_SHORT).show();
        }

        // clear edit text
        et_name.setText("");
    }
}