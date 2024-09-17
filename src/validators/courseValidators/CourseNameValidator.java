package validators.courseValidators;

import entity.tokens.CourseName;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;

import java.util.List;

/**
 * &#064;Classname CourseNameValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 20:38
 * &#064;Created MuJue
 */
public class CourseNameValidator {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseNameValidator courseNameValidator = new CourseNameValidator();
    private CourseNameValidator(){;}
    public static CourseNameValidator getInstance(){
        return courseNameValidator;
    }
    public void isCertainTeacherCourseNameExist(CourseName courseName) throws ValidationException {
        String tid = stateManipulator.getStateId();
        List<Integer> cids = userCourseManipulator.getTeacherCourse(tid);
        for(Integer cid : cids){
            String tempCourseName = courseManipulator.getCourseName(cid);
            if(courseName.getValue().equals(tempCourseName)){
                throw new ValidationException("Course name exists\n");
            }
        }
    }
}
