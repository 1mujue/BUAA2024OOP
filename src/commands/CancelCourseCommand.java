package commands;

import entity.tokens.CourseId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseCancelValidator;
import validators.courseValidators.CourseExistenceValidator;
import validators.userValidators.UserPermissionValidator;

import java.util.Arrays;

/**
 * &#064;Classname CancelCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:31
 * &#064;Created MuJue
 */
public class CancelCourseCommand extends BaseCommand{
    private static final CancelCourseCommand cancelCourseCommand = new CancelCourseCommand();
    private CancelCourseCommand(){;}
    public static CancelCourseCommand getInstance(){
        return cancelCourseCommand;
    }
    private CourseId courseId = null;
    @Override
    public void execute() throws ExecutionException {
        int cid = courseId.getCourseId();
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message = courseExecutor.cancelCourse(cid);
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "cancelCourse");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        courseId = new CourseId(parameters.get(0));
        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.tokenValidate(courseId);

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(Arrays.asList(
                "Student",
                "Teacher",
                "Administrator"
        ));

        CourseCancelValidator courseCancelValidator = CourseCancelValidator.getInstance();
        courseCancelValidator.isCurrentCancelCourseExist(courseId);
    }
}
