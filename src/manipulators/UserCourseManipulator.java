package manipulators;

import data.UserCourseData;
import entity.Course;

import java.util.*;

/**
 * &#064;Classname UserCourseExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 23:23
 * &#064;Created MuJue
 */
public class UserCourseManipulator {
    private static final UserCourseData userCourseData = UserCourseData.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = new UserCourseManipulator();
    private UserCourseManipulator(){;}
    public static UserCourseManipulator getInstance(){
        return userCourseManipulator;
    }
    public void createTeacherCourse(String tid, Integer cid){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        insertUserCourse(teacherCourse, tid, cid);
    }
    public void selectStudentCourse(String sid, Integer cid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        insertUserCourse(studentCourse, sid, cid);
    }
    private void insertUserCourse(HashMap<String, List<Integer>> userCourse, String uid, int cid){
        if(userCourse.containsKey(uid)){
            userCourse.get(uid).add(cid);
        } else{
            userCourse.put(uid, new ArrayList<Integer>(List.of(cid)));
        }
    }

    public List<Integer> getTeacherCourse(String tid){
        List<Integer> courses = new ArrayList<>();
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<String, List<Integer>> entry : teacherCourse.entrySet()){
            if(entry.getKey().equals(tid)){
                courses.addAll(entry.getValue());
            }
        }
        return courses;
    }
    public int getTeacherCourseNumber(String tid){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        return getCourseNumber(teacherCourse, tid);
    }
    public Integer getStudentCourseNumber(String sid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        return getCourseNumber(studentCourse, sid);
    }
    private Integer getCourseNumber(HashMap<String, List<Integer>> userCourse, String uid){
        int count = 0;
        for(Map.Entry<String, List<Integer>> entry : userCourse.entrySet()){
            if(entry.getKey().equals(uid)){
                count = entry.getValue().size();
            }
        }
        return count;
    }
    public String getTeacherCourseTid(Integer cid){
        String tid = null;
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<String, List<Integer>> entry : teacherCourse.entrySet()){
            if(entry.getValue().contains(cid)){
                tid = entry.getKey();
                break; // C-X can ONLY belong to one teacher.
            }
        }
        return tid;
    }
    public List<String> getStudentCourseSid(Integer cid){
        List<String> sids = new ArrayList<>();
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<String, List<Integer>> entry : studentCourse.entrySet()){
            if(entry.getValue().contains(cid)){
                sids.add(entry.getKey());
            }
        }
        return sids;
    }
    public List<Integer> getStudentCourse(String sid){
        List<Integer> courses = new ArrayList<>();
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<String, List<Integer>> entry : studentCourse.entrySet()){
            if(entry.getKey().equals(sid)){
                courses.addAll(entry.getValue());
            }
        }
        return courses;
    }

    public Integer getCourseSelectedNumber(int cid){
        int count = 0;
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<String, List<Integer>> entry : studentCourse.entrySet()){
            if(entry.getValue().contains(cid)){
                count++;
            }
        }
        return count;
    }
    public boolean isTeacherCourseExist(String tid){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        return isCourseExist(teacherCourse, tid);
    }
    public boolean isStudentCourseExist(String sid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        return isCourseExist(studentCourse, sid);
    }
    private boolean isCourseExist(HashMap<String, List<Integer>> userCourse, String uid){
        for(Map.Entry<String, List<Integer>> entry: userCourse.entrySet()){
            if(entry.getKey().equals(uid) && !entry.getValue().isEmpty()){
                return true;
            }
        }
        return false;
    }
    public boolean isTeacherCourseExist(String tid, int cid){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        return isCourseExist(teacherCourse, tid, cid);
    }
    public boolean isStudentCourseExist(String sid, int cid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        return isCourseExist(studentCourse, sid, cid);
    }
    private boolean isCourseExist(HashMap<String, List<Integer>> userCourse, String uid, int cid){
        for(Map.Entry<String, List<Integer>> entry : userCourse.entrySet()){
            if(entry.getKey().equals(uid) && entry.getValue().contains(cid)){
                return true;
            }
        }
        return false;
    }
    public boolean isTeacherCourseNameExist(String tid, String name){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<String, List<Integer>> entry : teacherCourse.entrySet()) {
            if(entry.getKey().equals(tid)){
                List<Integer> cids = entry.getValue();
                for(Integer cid : cids){
                    Course course = courseManipulator.getCourseById(cid);
                    if(name.equals(course.getName())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isTeacherCourseTimeConflict(String tid, int weekTime, int fromTime, int toTime){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        return isCourseTimeConflict(teacherCourse, tid, weekTime, fromTime, toTime);
    }
    public boolean isStudentCourseTimeConflict(String sid, int weekTime, int fromTime, int toTime){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        return isCourseTimeConflict(studentCourse, sid, weekTime, fromTime, toTime);
    }
    private boolean isCourseTimeConflict(HashMap<String, List<Integer>> userCourses, String uid, int weekTime, int fromTime, int toTime){
        for(Map.Entry<String, List<Integer>> entry : userCourses.entrySet()) {
            if(entry.getKey().equals(uid)){
                List<Integer> cids = entry.getValue();
                for(Integer cid : cids){
                    Course course = courseManipulator.getCourseById(cid);
                    int tempWeekTime = course.getWeekTime();
                    if(tempWeekTime == weekTime){
                        int tempFromTime = course.getFromTime();
                        int tempToTime = course.getToTime();
                        if(fromTime >= tempFromTime && fromTime <= tempToTime){
                            return true;
                        }
                        if(toTime >= tempFromTime && toTime <= tempToTime){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public void removeAnyTeacherCertainCourse(Integer cid){
        HashMap<String, List<Integer>> teacherCourse = userCourseData.getTeacherCourse();
        for(Map.Entry<String, List<Integer>> entry : teacherCourse.entrySet()){
            if(entry.getValue().contains(cid)){
                entry.getValue().remove(cid);
                break; // C-X can ONLY belong to one teacher.
            }
        }
    }
    public void removeAnyStudentCertainCourse(Integer cid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<String, List<Integer>> entry : studentCourse.entrySet()){
            entry.getValue().remove(cid);
        }
    }
    public void removeCertainStudentCertainCourse(String uid, Integer cid){
        HashMap<String, List<Integer>> studentCourse = userCourseData.getStudentCourse();
        for(Map.Entry<String, List<Integer>> entry : studentCourse.entrySet()){
            if(entry.getKey().equals(uid)){
                entry.getValue().remove(cid);
                break;
            }
        }
    }
}
