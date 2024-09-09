package manipulators;

import com.sun.jdi.IntegerType;
import data.CourseData;
import data.UserCourseData;
import data.UserData;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * &#064;Classname UserCourseManipulator
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:23
 * &#064;Created MuJue
 */
public class UserCourseManipulator {
    private static final UserCourseData userCourseData = UserCourseData.getInstance();
    private static final UserCourseManipulator userCourseManipulator = new UserCourseManipulator();
    private UserCourseManipulator(){;}
    public static UserCourseManipulator getInstance(){
        return userCourseManipulator;
    }
    public void createTeacherCourse(String tid, int cid){
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        teacherCourse.put(cid, tid);
    }
    public void selectStudentCourse(String sid, int cid){
        HashMap<Integer, String> studentCourse = userCourseData.getStudentCourse();
        studentCourse.put(cid, sid);
    }
    public void removeTeacherCourse(String tid, int cid){
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        teacherCourse.remove(cid, tid);
    }
    public void removeTeacherCourse(int cid){
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        teacherCourse.remove(cid);
    }
    public void removeStudentCourse(String sid, int cid){
        HashMap<Integer, String> studentCourse = userCourseData.getTeacherCourse();
        studentCourse.remove(cid, sid);
    }
    public void removeStudentCourse(int cid){
        HashMap<Integer, String> studentCourse = userCourseData.getTeacherCourse();
        studentCourse.remove(cid);
    }
    public List<Integer> getTeacherCourse(String tid){
        List<Integer> courses = new ArrayList<>();
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<Integer,String> entry : teacherCourse.entrySet()){
            if(entry.getValue().equals(tid)){
                courses.add(entry.getKey());
            }
        }
        return courses;
    }
    public int getTeacherCourseNumber(String tid){
        int ans = 0;
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<Integer,String> entry : teacherCourse.entrySet()){
            if(entry.getValue().equals(tid)){
                ans++;
            }
        }
        return ans;
    }
    public String getTeacherCourseTid(int cid){
        String tid = null;
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<Integer,String> entry : teacherCourse.entrySet()){
            if(entry.getKey().equals(cid)){
                tid = entry.getValue();
                break;
            }
        }
        return tid;
    }
    public List<Integer> getStudentCourse(String sid){
        List<Integer> courses = new ArrayList<>();
        HashMap<Integer, String> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<Integer,String> entry : studentCourse.entrySet()){
            if(entry.getValue().equals(sid)){
                courses.add(entry.getKey());
            }
        }
        return courses;
    }
    public Integer getStudentCourseNumber(String sid){
        int count = 0;
        HashMap<Integer, String> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<Integer,String> entry : studentCourse.entrySet()){
            if(entry.getValue().equals(sid)){
                count++;
            }
        }
        return count;
    }
    public boolean isTeacherCourseExist(String tid){
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<Integer, String> entry : teacherCourse.entrySet()){
            if(entry.getValue().equals(tid)){
                return true;
            }
        }
        return false;
    }
    public boolean isStudentCourseExist(String sid){
        HashMap<Integer, String> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<Integer, String> entry: studentCourse.entrySet()){
            if(entry.getValue().equals(sid)){
                return true;
            }
        }
        return false;
    }
    public boolean isTeacherCourseExist(String tid, int cid){
        HashMap<Integer, String> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<Integer, String> entry : teacherCourse.entrySet()){
            if(entry.getKey().equals(cid) && entry.getValue().equals(tid)){
                return true;
            }
        }
        return false;
    }
}
