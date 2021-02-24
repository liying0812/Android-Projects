package edu.scu.coen268.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    public final static String USERNAME = "testUser";
    public final static String PASSWORD = "123456";

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_main);

        // initialize UI elements
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }

    //ToDo: 1. Implement the callback methods
    //ToDo: 2. Implement click event handler method
}