package manipulators;

import data.CourseData;
import entity.Course;
import java.util.List;

/**
 * &#064;Classname CourseManipulator
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:22
 * &#064;Created MuJue
 */
public class CourseManipulator {
    private static final CourseData courseData = CourseData.getInstance();
    private static final CourseManipulator courseManipulator = new CourseManipulator();
    private CourseManipulator(){;}
    public static CourseManipulator getInstance(){
        return courseManipulator;
    }
    public Course getCourseById(int cid){
        List<Course> courses = courseData.getCourses();
        for(Course course : courses){
            if(course.getId() == cid){
                return course;
            }
        }
        return null;
    }
    public List<Course> getCourses(){
        return courseData.getCourses();
    }
    public String getCourseName(int cid){
        List<Course> courses = courseData.getCourses();
        for(Course course : courses){
            if(course.getId() == cid){
                return course.getName();
            }
        }
        return null;
    }
    public void addCourse(Course course){
        List<Course> courses = courseData.getCourses();
        courses.add(course);
    }
    public void removeCourse(int cid){
        List<Course> courses = courseData.getCourses();
        courses.removeIf(course -> course.getId() == cid);
    }
    public boolean isCourseExist(){
        List<Course> courses = courseData.getCourses();
        return !courses.isEmpty();
    }
    public boolean isCourseExist(int cid){
        List<Course> courses = courseData.getCourses();
        for(Course course : courses){
            if(course.getId() == cid){
                return true;
            }
        }
        return false;
    }
}
