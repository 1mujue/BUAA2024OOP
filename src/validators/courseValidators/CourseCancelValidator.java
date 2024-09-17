package validators.courseValidators;

import entity.tokens.CourseId;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;

/**
 * &#064;Classname CourseCancelValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 20:40
 * &#064;Created MuJue
 */
public class CourseCancelValidator {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseCancelValidator courseCancelValidator = new CourseCancelValidator();
    private CourseCancelValidator(){;}
    public static CourseCancelValidator getInstance(){
        return courseCancelValidator;
    }
    public void isCurrentCancelCourseExist(CourseId courseId) throws ValidationException {
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){ // cancel course.
            String tid = stateManipulator.getStateId();
            if(!userCourseManipulator.isTeacherCourseExist(tid, courseId.getCourseId())){
                throw new ValidationException("Course does not exist\n");
            }
        } else if(permission.equals("Student")){ // cancel course.
            String sid = stateManipulator.getStateId();
            if(!userCourseManipulator.isStudentCourseExist(sid, courseId.getCourseId())){
                throw new ValidationException("Course does not exist\n");
            }
        }
        else if (permission.equals("Administrator")){ // cancel course.
            if(!courseManipulator.isCourseExist(courseId.getCourseId())){
                throw new ValidationException("Course does not exist\n");
            }
        }
    }
}
