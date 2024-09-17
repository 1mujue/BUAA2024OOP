package data;

import entity.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname CourseData
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:18
 * &#064;Created MuJue
 */
public class CourseData implements Serializable {
    private static final CourseData courseData = new CourseData();
    private CourseData(){;}
    public static CourseData getInstance(){
        return courseData;
    }
    private final List<Course> courses = new ArrayList<>();
    public List<Course> getCourses(){
        return courses;
    }
}
