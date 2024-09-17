package executors;

import entity.Course;
import entity.TeacherCourse;
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
        StringBuilder message = new StringBuilder();
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            String tid = stateManipulator.getStateId();
            List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
            cids.sort(Integer::compareTo);
            for(Integer cid : cids){
                Course course = courseManipulator.getCourseById(cid);
                message.append(course.toString());
            }
            return message.toString();
        }
        else{
            List<TeacherCourse> teacherCourses = new ArrayList<>();
            List<Course> courses = courseManipulator.getCourses();
            for(Course course : courses){
                String tid = userCourseManipulator.getTeacherCourseTid(course.getId());
                String tName = userManipluator.getUserById(tid).getName();
                teacherCourses.add(new TeacherCourse(tName, course));
            }
            return getCourseSorted(message, teacherCourses).append("List course success\n").toString();
        }
    }
    public String listCourse(String tid) throws ExecutionException{
        StringBuilder message = new StringBuilder();
        List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
        User teacher = userManipluator.getUserById(tid);
        String tName = teacher.getName();
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        for(Integer cid : cids){
            Course course = courseManipulator.getCourseById(cid);
            TeacherCourse teacherCourse = new TeacherCourse(tName, course);
            teacherCourses.add(teacherCourse);
        }
        return getCourseSorted(message, teacherCourses).append("List course success\n").toString();
    }
    private StringBuilder getCourseSorted(StringBuilder message, List<TeacherCourse> teacherCourses) {
        teacherCourses.sort((o1, o2) -> {
            if(o1.gettName().equals(o2.gettName())){
                return o1.getCourse().getId().compareTo(o2.getCourse().getId());
            } else {
                return o1.gettName().compareTo(o2.gettName());
            }
        });
        for(TeacherCourse teacherCourse : teacherCourses){
            message.append(teacherCourse.toString());
        }
        return message;
    }
    public String selectCourse(int cid) throws ExecutionException{
        String sid = stateManipulator.getStateId();
        userCourseManipulator.selectStudentCourse(sid, cid);
        return "Select course success (courseId: C-" + cid + ")\n";
    }
    public String cancelCourse(int cid) throws ExecutionException{
        String permission = stateManipulator.getStatePermission();
        String uid = stateManipulator.getStateId();
        if(permission.equals("Teacher") || permission.equals("Administrator")){
            userCourseManipulator.removeAnyTeacherCertainCourse(cid);
            courseManipulator.removeCourse(cid);
            userCourseManipulator.removeAnyStudentCertainCourse(cid);
        } else if(permission.equals("Student")){
            userCourseManipulator.removeCertainStudentCertainCourse(uid, cid);
        }
        return "Cancel course success (courseId: C-" + cid + ")\n";
    }
}
