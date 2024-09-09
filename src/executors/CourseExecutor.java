package executors;

import entity.Course;
import entity.User;
import exceptions.ExecutionException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;
import manipulators.UserManipulator;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname CourseExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/8 12:01
 * &#064;Created MuJue
 */
public class CourseExecutor {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserManipulator userManipluator = UserManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseExecutor courseExecutor = new CourseExecutor();
    private CourseExecutor(){;}
    public static CourseExecutor getInstance(){
        return courseExecutor;
    }
    public String createCourse(Course course) throws ExecutionException{
        courseManipulator.addCourse(course);
        String tid = stateManipulator.getStateId();
        int cid = course.getId();
        userCourseManipulator.createTeacherCourse(tid, cid);
        return "Create course success (courseId: C-" + cid + ")\n";
    }
    public String listCourse() throws ExecutionException{
        String permission = stateManipulator.getStatePermission();
        List<Course> courses = new ArrayList<>();
        StringBuilder message = new StringBuilder();
        if(permission.equals("Teacher")){
            String tid = stateManipulator.getStateId();
            List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
            for(Integer cid : cids){
                courses.add(courseManipulator.getCourseById(cid));
            }
            for(Course course : courses){
                message.append(course.toString());
            }
        }
        else{
            courses = courseManipulator.getCourses();
            for(Course course : courses){
                String tid = userCourseManipulator.getTeacherCourseTid(course.getId());
                String tName = userManipluator.getUserById(tid).getName();
                message.append(tName).append(" ").append(course.toString());
            }
        }

        return message.toString();
    }
    public String listCourse(String tid) throws ExecutionException{
        List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
        User teacher = userManipluator.getUserById(tid);
        StringBuilder message = new StringBuilder();
        for(Integer cid : cids){
            Course course = courseManipulator.getCourseById(cid);
            message.append(teacher.getName()).append(" ").append(course.toString());
        }
        return message.toString();
    }
    public String selectCourse(int cid) throws ExecutionException{
        String sid = stateManipulator.getStateId();
        userCourseManipulator.selectStudentCourse(sid, cid);
        return "Select course success\n";
    }
    public String cancelCourse(int cid) throws ExecutionException{
        userCourseManipulator.removeTeacherCourse(cid);
        userCourseManipulator.removeStudentCourse(cid);
        courseManipulator.removeCourse(cid);
        return "Cancel course success\n";
    }
}
