package com.example.assingment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {
    public final static String TAG = "SecondActivity";

    private WebView myWebView;
    String animalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // define complete url
        animalText = getIntent().getStringExtra("animal");
        String animalUrl = "https://en.wikipedia.org/wiki/" + animalText;

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(animalUrl);
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Handle your logic in your onBackPressed() method and just call that method in onSupportNavigateUp() so the back button on the phone and the arrow on the toolbar do the same thing.
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}