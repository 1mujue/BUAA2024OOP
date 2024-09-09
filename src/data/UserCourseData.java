package data;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;

/**
 * &#064;Classname UserCourseData
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:18
 * &#064;Created MuJue
 */
public class UserCourseData {
    private static final HashMap<Integer, String> teacherCourse = new HashMap<>();
    private static final HashMap<Integer, String> studentCourse = new HashMap<>();
    private static final UserCourseData userCourseData = new UserCourseData();
    private UserCourseData(){;}
    public static UserCourseData getInstance(){
        return userCourseData;
    }
    public HashMap<Integer, String> getTeacherCourse(){
        return teacherCourse;
    }
    public HashMap<Integer, String> getStudentCourse(){
        return studentCourse;
    }
}
