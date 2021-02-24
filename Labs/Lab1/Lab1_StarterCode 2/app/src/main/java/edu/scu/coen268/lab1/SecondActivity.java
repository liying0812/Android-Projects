package edu.scu.coen268.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public final static String TAG = "SecondActivity";
    private String userName = "";

    TextView userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_second);

        // initialize UI elements
        userText = findViewById(R.id.tv_user);

        //ToDo: 2. Get and set username
    }

    //ToDo: 1. Implement the callback methods
    //ToDo: 3. Implement click event handler method
}