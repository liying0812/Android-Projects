package com.example.lab3_liang;

import android.provider.BaseColumns;

final public class CourseInfoContract {
    private CourseInfoContract() {

    }

    public static class Courses implements BaseColumns {
        public static final String TABLE_NAME = "CoursesInfo";
        public static final String COURSE_ID = "course_id";
        public static final String PROF_NAME = "name";
    }
}
