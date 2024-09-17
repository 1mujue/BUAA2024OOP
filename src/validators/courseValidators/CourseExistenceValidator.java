package validators.courseValidators;

import entity.tokens.CourseId;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;
import validators.TokenValidator;

/**
 * &#064;Classname CourseExistenceValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/8 11:19
 * &#064;Created MuJue
 */
public class CourseExistenceValidator extends TokenValidator {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseExistenceValidator courseValidator = new CourseExistenceValidator();
    private CourseExistenceValidator(){;}
    public static CourseExistenceValidator getInstance(){
        return courseValidator;
    }
    // the existence of global or current teacher's course.
    public void isAnyCourseExist() throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            isCurrentTeacherAnyCourseExist();
        } else{
            isCurrentGlobalAnyCourseExist();
        }
    }
    private void isCurrentTeacherAnyCourseExist() throws ValidationException{
        String tid = stateManipulator.getStateId();
        isCertainTeacherAnyCourseExist(tid);
    }
    public void isCertainTeacherAnyCourseExist(String tid) throws ValidationException{
        if(!userCourseManipulator.isTeacherCourseExist(tid)){
            throw new ValidationException("Course does not exist\n");
        }
    }
    private void isCurrentGlobalAnyCourseExist() throws ValidationException{
        if(!courseManipulator.isCourseExist()){
            throw new ValidationException("Course does not exist\n");
        }
    }
    public void isCertainCourseExist(CourseId courseId) throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            isCurrentTeacherCertainCourseExist(courseId.getCourseId());
        } else if(permission.equals("Administrator") || permission.equals("Student")){
            isCurrentGlobalCertainCourseExist(courseId.getCourseId());
        }
    }
    private void isCurrentTeacherCertainCourseExist(int cid) throws ValidationException{
        String tid = stateManipulator.getStateId();
        isCertainTeacherCertainCourseExist(tid, cid);
    }
    private void isCertainTeacherCertainCourseExist(String tid, int cid) throws ValidationException{
        if(!userCourseManipulator.isTeacherCourseExist(tid, cid)){
            throw new ValidationException("Course does not exist\n");
        }
    }
    private void isCurrentGlobalCertainCourseExist(int cid) throws ValidationException{
        if(!courseManipulator.isCourseExist(cid)){
            throw new ValidationException("Course does not exist\n");
        }
    }
}
