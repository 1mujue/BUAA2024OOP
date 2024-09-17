package validators;

import entity.User;
import entity.tokens.CourseId;
import exceptions.ValidationException;
import executors.UserCourseExecutor;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;

/**
 * &#064;Classname UserCourseValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 20:33
 * &#064;Created MuJue
 */
public class UserCourseValidator {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final UserCourseValidator userCourseValidator = new UserCourseValidator();
    private UserCourseValidator(){;}
    public static UserCourseValidator getInstance(){
        return userCourseValidator;
    }
    public void isCurrentTeacherCourseNumberReachLimit() throws ValidationException {
        String tid = stateManipulator.getStateId();
        int number = userCourseManipulator.getTeacherCourseNumber(tid);
        if(number == 10){
            throw new ValidationException("Course count reaches limit\n");
        }
    }
}
