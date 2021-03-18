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
import android.widget.TextView;


public class ViewAllFragment extends Fragment {

    private TextView tv_all_courses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container,false);

        tv_all_courses = view.findViewById(R.id.tv_all_student_info);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllStudentInfo();
    }

    private void getAllStudentInfo() {
        // ToDo: Retrieve the student info saved from the database
        // ToDo: Retrieve the student info saved from the database
        CourseDbHelper dbHelper = new CourseDbHelper(getActivity());

        // get data repository in read mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // ORDER BY professor name;
        Cursor cursor = db.query(CourseInfoContract.Courses.TABLE_NAME, null, null, null, null, null,
                CourseInfoContract.Courses.PROF_NAME);

        String result = "";
        while (cursor.moveToNext()) {
            String course = cursor.getString(cursor.getColumnIndex(CourseInfoContract.Courses.COURSE_ID));
            String name = cursor.getString(cursor.getColumnIndex(CourseInfoContract.Courses.PROF_NAME));

            result = result + "\n\nCourse: " + course + "\nProfessor: " + name;
        }

        // close db
        db.close();
        if (result.isEmpty()) {
            result = "No records found";
        }

        tv_all_courses.setText(result);
    }
}