package data;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * &#064;Classname UserCourseData
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:18
 * &#064;Created MuJue
 */
public class UserCourseData {
    private static final HashMap<String, List<Integer>> teacherCourse = new HashMap<>();
    private static final HashMap<String, List<Integer>> studentCourse = new HashMap<>();
    private static final UserCourseData userCourseData = new UserCourseData();
    private UserCourseData(){;}
    public static UserCourseData getInstance(){
        return userCourseData;
    }
    public HashMap<String, List<Integer>> getTeacherCourse(){
        return teacherCourse;
    }
    public HashMap<String, List<Integer>> getStudentCourse(){
        return studentCourse;
    }
}
