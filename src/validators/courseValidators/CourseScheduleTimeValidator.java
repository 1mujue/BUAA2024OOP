package validators.courseValidators;

import entity.Course;
import entity.tokens.CourseId;
import entity.tokens.CourseScheduleTime;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;

/**
 * &#064;Classname CourseScheduleTimeValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 20:30
 * &#064;Created MuJue
 */
public class CourseScheduleTimeValidator {
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseScheduleTimeValidator scheduleTimeValidator = new CourseScheduleTimeValidator();
    private CourseScheduleTimeValidator(){;}
    public static CourseScheduleTimeValidator getInstance(){
        return scheduleTimeValidator;
    }
    public void studentCourseScheduleTimeConflictValidate(CourseId courseId) throws ValidationException {
        Course course = courseManipulator.getCourseById(courseId.getCourseId());
        int weekTime = course.getWeekTime();
        int fromTime = course.getFromTime();
        int toTime = course.getToTime();

        String sid = stateManipulator.getStateId();
        if(userCourseManipulator.isStudentCourseTimeConflict(sid, weekTime, fromTime, toTime)){
            throw new ValidationException("Course time conflicts\n");
        }
    }
    // create course.
    public void isTeacherCourseScheduleTimeConflict(CourseScheduleTime courseScheduleTime) throws ValidationException{
        int weekTime = courseScheduleTime.getWeekTime();
        int fromTime = courseScheduleTime.getFromTime();
        int toTime = courseScheduleTime.getToTime();
        String tid = stateManipulator.getStateId();

        if(userCourseManipulator.isTeacherCourseTimeConflict(tid, weekTime, fromTime, toTime)){
            throw new ValidationException("Course time conflicts\n");
        }
    }
}
