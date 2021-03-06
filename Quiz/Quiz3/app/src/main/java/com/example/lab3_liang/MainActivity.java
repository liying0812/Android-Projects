package com.example.lab3_liang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    public enum FragmentType {
        ADD, DELETE, SEARCH, SEARCH_COURSE_NUM, VIEW_ALL;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
    }


    @Override
    public void replaceFragment(FragmentType type) {
        switch(type){
            case ADD:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddFragment()).addToBackStack(null).commit();
                break;
//            case DELETE:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteFragment()).addToBackStack(null).commit();
//                break;
            case SEARCH:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).addToBackStack(null).commit();
                break;
            case SEARCH_COURSE_NUM:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchCourseNumFragment()).addToBackStack(null).commit();
                break;
            case VIEW_ALL:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ViewAllFragment()).addToBackStack(null).commit();
                break;
        }

    }
}