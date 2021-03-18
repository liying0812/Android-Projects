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


public class SearchCourseNumFragment extends Fragment {

    private Button btn_search;
    private EditText et_course;
    private TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serach_course_num, container, false);
        btn_search = view.findViewById(R.id.btn_search);
        et_course = view.findViewById(R.id.et_search_course);
        tv_result = view.findViewById(R.id.tv_search_result);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourseNum();
            }
        });

        return view;
    }

    private void searchCourseNum() {
        String course = et_course.getText().toString();
        if (course.isEmpty()) {
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

        String selection = CourseInfoContract.Courses.COURSE_ID+ " LIKE? ";
        String[] selectionArgs = {"%"+course+"%"};

        Cursor cursor = db.query(CourseInfoContract.Courses.TABLE_NAME, columns, selection,
                selectionArgs, null, null, CourseInfoContract.Courses.COURSE_ID);

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
            Toast.makeText(this.getContext(), "No number matches", Toast.LENGTH_SHORT).show();
        } else {
            tv_result.setText(result);
        }

    }
}