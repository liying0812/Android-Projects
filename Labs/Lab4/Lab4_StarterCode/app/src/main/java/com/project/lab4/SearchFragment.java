package com.project.lab4;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btn_search = view.findViewById(R.id.btn_search);
        et_name = view.findViewById(R.id.et_search_name);
        tv_result = view.findViewById(R.id.tv_search_result);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStudent();
            }
        });

        return view;
    }

    private void searchStudent() {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // ToDo: Search the student in the database

        // instantiate StudentDBHelper
        StudentDbHelper dbHelper = new StudentDbHelper((getActivity()));
        // get data repository in read mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Use the query() method, passing it selection criteria and desired columns (projection) for the
        //SQL command – SELECT name,student_id FROM StudentsInfo WHERE name LIKE “%<entered_name>%” ORDER BY name;
        String[] columns = {StudentInfoContract.Students.STUDENT_NAME, StudentInfoContract.Students.STUDENT_ID};
        String selection = StudentInfoContract.Students.STUDENT_NAME+ " LIKE? ";
        String[] selectionArgs = {"%"+name+"%"};

        Cursor cursor = db.query(StudentInfoContract.Students.TABLE_NAME, columns, selection,
                selectionArgs, null, null, StudentInfoContract.Students.STUDENT_NAME);

        String result = "";
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_ID));
            String recordName = cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_NAME));

            result = result + "\n\nID: " + id + "\nNAME: " + recordName;
        }

        // close db
        db.close();

        // Check whether the result is empty, If empty, set result= ”No records Found”
        if (result.isEmpty()) {
            result = "No records found";
        }

        tv_result.setText(result);
    }
}