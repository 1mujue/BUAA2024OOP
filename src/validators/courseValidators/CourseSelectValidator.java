package validators.courseValidators;

import entity.tokens.CourseId;
import entity.tokens.UserId;
import exceptions.ValidationException;
import manipulators.CourseManipulator;
import manipulators.StateManipulator;
import manipulators.UserCourseManipulator;

import java.util.Collections;
import java.util.List;

/**
 * &#064;Classname CourseSelectValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 20:44
 * &#064;Created MuJue
 */
public class CourseSelectValidator {
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final CourseManipulator courseManipulator = CourseManipulator.getInstance();
    private static final UserCourseManipulator userCourseManipulator = UserCourseManipulator.getInstance();
    private static final CourseSelectValidator courseSelectValidator = new CourseSelectValidator();
    private CourseSelectValidator(){;}
    public static CourseSelectValidator getInstance(){
        return courseSelectValidator;
    }
    public void isCurrentStudentSelectAnyCourse() throws ValidationException {
        String uid = stateManipulator.getStateId();
        isCertainStudentSelectGlobalAnyCourse(uid);
    }
    public void isCertainStudentSelectAnyCourse(UserId userId) throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            isCertainStudentSelectTeacherAnyCourse(userId.getValue());
        } else if(permission.equals("Administrator")){
            isCertainStudentSelectGlobalAnyCourse(userId.getValue());
        }
    }
    private void isCertainStudentSelectTeacherAnyCourse(String sid) throws ValidationException{
        String tid = stateManipulator.getStateId();
        List<Integer> cids1 = userCourseManipulator.getStudentCourse(sid);
        List<Integer> cids2 = userCourseManipulator.getTeacherCourse(tid);
        if(Collections.disjoint(cids1, cids2)){
            throw new ValidationException("Student does not select course\n");
        }
    }
    private void isCertainStudentSelectGlobalAnyCourse(String sid) throws ValidationException{
        List<Integer> cids = userCourseManipulator.getStudentCourse(sid);
        if(cids.isEmpty()){
            throw new ValidationException("Student does not select course\n");
        }
    }
    public void isCertainStudentSelectCertainCourse(UserId userId, CourseId courseId) throws ValidationException{
        String permission = stateManipulator.getStatePermission();
        if(permission.equals("Teacher")){
            isCertainStudentSelectTeacherCertainCourse(userId.getValue(), courseId.getCourseId());
        } else if(permission.equals("Administrator")){
            isCertainStudentSelectGlobalCertainCourse(userId.getValue(), courseId.getCourseId());
        }
    }
    private void isCertainStudentSelectTeacherCertainCourse(String sid, Integer cid) throws ValidationException{
        String tid = stateManipulator.getStateId();
        List<Integer> cids1 = userCourseManipulator.getStudentCourse(sid);
        List<Integer> cids2 = userCourseManipulator.getTeacherCourse(tid);
        if(!(cids1.contains(cid) && cids2.contains(cid))){
            throw new ValidationException("Student does not select course\n");
        }
    }
    private void isCertainStudentSelectGlobalCertainCourse(String sid, Integer cid) throws ValidationException{
        List<Integer> cids = userCourseManipulator.getStudentCourse(sid);
        if(!cids.contains(cid)){
            throw new ValidationException("Student does not select course\n");
        }
    }
    public void isCertainCourseSelectNumberReachLimit(CourseId courseId) throws ValidationException{
        int count = userCourseManipulator.getCourseSelectedNumber(courseId.getCourseId());
        if(count >= 30){
            throw new ValidationException("Course capacity is full\n");
        }
    }
    public void isAnyStudentSelectCertainCourse(CourseId courseId) throws ValidationException{
        int count = userCourseManipulator.getCourseSelectedNumber(courseId.getCourseId());
        if(count == 0){
            throw new ValidationException("Student does not select course\n");
        }
    }
}
