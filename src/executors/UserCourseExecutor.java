package executors;

import entity.Course;
import entity.ScheduleCourse;
import entity.User;
import entity.tokens.StudentSelectCourse;
import entity.tokens.UserId;
import exceptions.ExecutionException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;
import manipulators.UserManipulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * &#064;Classname UserCourseExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/15 21:50
 * &#064;Created MuJue
 */
public class UserCourseExecutor {
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final UserCourseExecutor userCourseExecutor = new UserCourseExecutor();
    private UserCourseExecutor(){;}
    public static UserCourseExecutor getInstance(){
        return userCourseExecutor;
    }
    public String listStudent(int cid) throws ExecutionException {
        StringBuilder message = new StringBuilder();
        List<String> sids = userCourseManipulator.getStudentCourseSid(cid);
        List<StudentSelectCourse> studentSelectCourses = new ArrayList<>();
        for(String sid : sids){
            User student = userManipulator.getUserById(sid);
            StudentSelectCourse studentSelectCourse = new StudentSelectCourse(sid,student.getName());
            studentSelectCourses.add(studentSelectCourse);
        }
        studentSelectCourses.sort((o1, o2) -> {
            UserId userId1 = o1.getUserId();
            UserId userId2 = o2.getUserId();
            Integer score1 = userId1.getScore();
            Integer score2 = userId2.getScore();
            if(score1.equals(score2)){
                Integer number1 = userId1.getNumber();
                Integer number2 = userId2.getNumber();
                return number1.compareTo(number2);
            }else {
                return score1.compareTo(score2);
            }
        });
        for(StudentSelectCourse studentSelectCourse : studentSelectCourses){
            message.append(studentSelectCourse.toString());
        }
        message.append("List student success\n");
        return message.toString();
    }
    public String removeStudent(String sid) throws ExecutionException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            return removeTeacherStudent(sid);
        } else if(permission.equals("Administrator")){
            return removeGlobalStudent(sid);
        }
        return null;
    }
    private String removeTeacherStudent(String sid) throws ExecutionException{
        String tid = stateManipulator.getStateId();
        List<Integer> cids1 = userCourseManipulator.getStudentCourse(sid);
        List<Integer> cids2 = userCourseManipulator.getTeacherCourse(tid);
        List<Integer> cids3 = cids1.stream()
                .filter(cids2::contains)
                .toList();
        for(Integer cid : cids3){
            userCourseManipulator.removeCertainStudentCertainCourse(sid, cid);
        }
        return "Remove student success\n";
    }
    private String removeGlobalStudent(String sid) throws ExecutionException{
        List<Integer> cids1 = userCourseManipulator.getStudentCourse(sid);
        for(Integer cid: cids1){
            userCourseManipulator.removeCertainStudentCertainCourse(sid, cid);
        }
        return "Remove student success\n";
    }
    public String removeStudent(String sid, int cid) throws ExecutionException{
        userCourseManipulator.removeCertainStudentCertainCourse(sid, cid);
        return "Remove student success\n";
    }
    public String listCourseSchedule() throws ExecutionException{
        String sid = stateManipulator.getStateId();
        return listCourseSchedule(sid);
    }
    public String listCourseSchedule(String uid) throws ExecutionException{
        List<Integer> cids = userCourseManipulator.getStudentCourse(uid);
        List<ScheduleCourse> scheduleCourses = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer cid : cids){
            String tid = userCourseManipulator.getTeacherCourseTid(cid);
            String tName = userManipulator.getUserById(tid).getName();
            Course course = courseManipulator.getCourseById(cid);
            ScheduleCourse scheduleCourse = new ScheduleCourse(
                    course.getWeekTime(),
                    course.getFromTime(),
                    course.getToTime(),
                    course.getName(),
                    course.getCredit(),
                    course.getDurationTime(),
                    tName
            );
            scheduleCourses.add(scheduleCourse);
        }
        scheduleCourses.sort((o1, o2) -> {
            if(Objects.equals(o1.getWeekTime(), o2.getWeekTime())){
                return o1.getFromTime().compareTo(o2.getFromTime());
            } else {
                return o1.getWeekTime().compareTo(o2.getWeekTime());
            }
        });
        for(ScheduleCourse scheduleCourse : scheduleCourses){
            stringBuilder.append(scheduleCourse.toString());
        }
        return stringBuilder.toString();
    }
}
