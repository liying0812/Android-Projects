package com.project.lab4;

import android.content.ContentValues;
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

public class DeleteFragment extends Fragment {

    private EditText et_id;
    private Button btn_delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        btn_delete = view.findViewById(R.id.btn_delete);
        et_id = view.findViewById(R.id.et_delete_id);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });
        return view;
    }

    private void deleteStudent() {
        String id = et_id.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(getActivity(), "ID cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // ToDo: Delete the student with the input id from the database
        // TODO: Add the record to the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        // get data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // use delete() method to delete records where id = user-entered id
        String whereClause = StudentInfoContract.Students.STUDENT_ID + "=?";
        String[] whereArgs = {id};

        int count = db.delete(StudentInfoContract.Students.TABLE_NAME, whereClause, whereArgs);

        // close db
        db.close();

        // check count value and return appropriate toast message
        if (count < 1) {
            Toast.makeText(this.getContext(), "No records deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Deleted " + count + " records", Toast.LENGTH_SHORT).show();
        }

        // clear edit text
        et_id.setText("");
    }
}