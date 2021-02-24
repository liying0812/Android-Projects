       package com.example.assingment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.dog, R.drawable.cat, R.drawable.rabbit, R.drawable.fish, R.drawable.shrimp, R.drawable.dolphin, R.drawable.panda,  R.drawable.lion1, R.drawable.tiger};

    String[] titles = {"Dog", "Cat", "Rabbit", "Fish", "Shrimp", "Dolphin", "Panda", "Lion", "Tiger"};

    String[] descriptions = {"Want to know more about Dog?", "This is a Cat!", "Learn more about Rabbit!", "Click to know Fish~", "Welcome to world of Shrimp!", "Dolphin Show!", "Want to know Panda?", "Look! A Lion!", "Talk with Tiger!"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setRequestedOrientation(activity_main.SCREEN_ORIENTATION_LANDSCAPE);

        ListView listView = (ListView) findViewById(R.id.item_list);

        CustomAnimalList adapter = new CustomAnimalList(MainActivity.this, titles, descriptions, images);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String animalType = titles[position];

                switch (position){
                    case 0: Intent dogIntent = new Intent(MainActivity.this, SecondActivity.class);
                            dogIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(dogIntent);
                            break;
                    case 1: Intent catIntent = new Intent(MainActivity.this, SecondActivity.class);
                            catIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(catIntent);
                            break;
                    case 2: Intent rabbitIntent = new Intent(MainActivity.this, SecondActivity.class);
                            rabbitIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(rabbitIntent);
                            break;
                    case 3: Intent fishIntent = new Intent(MainActivity.this, SecondActivity.class);
                            fishIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(fishIntent);
                            break;
                    case 4: Intent shrimpIntent = new Intent(MainActivity.this, SecondActivity.class);
                            shrimpIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(shrimpIntent);
                            break;
                    case 5: Intent dolphinIntent = new Intent(MainActivity.this, SecondActivity.class);
                            dolphinIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(dolphinIntent);
                            break;
                    case 6: Intent pandaIntent = new Intent(MainActivity.this, SecondActivity.class);
                            pandaIntent.putExtra("animal", animalType);
                            MainActivity.this.startActivity(pandaIntent);
                            break;
                    case 7: Intent lionIntent = new Intent(MainActivity.this, SecondActivity.class);
                        lionIntent.putExtra("animal", animalType);
                        MainActivity.this.startActivity(lionIntent);
                        break;
                    case 8: Intent tigerIntent = new Intent(MainActivity.this, SecondActivity.class);
                        tigerIntent.putExtra("animal", animalType);
                        MainActivity.this.startActivity(tigerIntent);
                        break;
                }
            }
        });
    }
}