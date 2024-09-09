package validators;

import entity.tokens.Token;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;
import utils.TokenHandler;

import java.util.List;

/**
 * &#064;Classname CourseValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/8 11:19
 * &#064;Created MuJue
 */
public class CourseValidator {
    private static final TokenHandler tokenHandler = TokenHandler.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseValidator courseValidator = new CourseValidator();
    private CourseValidator(){;}
    public static CourseValidator getInstance(){
        return courseValidator;
    }
    public void courseTokenValidate(Token token) throws ValidationException{
        token.validate();
    }

    public void isCourseNumberReachLimit() throws ValidationException{
        String tid = stateManipulator.getStateId();
        int number = userCourseManipulator.getTeacherCourseNumber(tid);
        if(number == 10){
            throw new ValidationException("Course count reaches limit\n");
        }
    }
    public void isCourseNameExist(String name) throws ValidationException{
        String tid = stateManipulator.getStateId();
        List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
        for(Integer cid : cids){
            String courseName = courseManipulator.getCourseName(cid);
            if(name.equals(courseName)){
                throw new ValidationException("Course name already exists\n");
            }
        }
    }
    // The existence of any course.
    public void isCourseExist() throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            String tid = stateManipulator.getStateId();
            if(!userCourseManipulator.isTeacherCourseExist(tid)){
                throw new ValidationException("Course does not exist\n");
            }
        }
        else {
            if(!courseManipulator.isCourseExist()){
                throw new ValidationException("Course does not exist\n");
            }
        }
    }
    // the existence of a certain course.
    public void isCourseExist(int cid) throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            String tid = stateManipulator.getStateId();
            if(!userCourseManipulator.isTeacherCourseExist(tid, cid)){
                throw new ValidationException("Course does not exist\n");
            }
        }else{
            if(!courseManipulator.isCourseExist(cid)){
                throw new ValidationException("Course does not exist\n");
            }
        }
    }
}
