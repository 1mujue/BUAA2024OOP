package commands;

import entity.tokens.CourseId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import utils.TokenHandler;
import validators.ArgumentCountValidator;
import validators.CourseValidator;
import validators.PermissionValidator;
import validators.StateValidator;

import java.util.Arrays;

/**
 * &#064;Classname CancelCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:31
 * &#064;Created MuJue
 */
public class CancelCourseCommand extends BaseCommand{
    private CourseId courseId;
    @Override
    public void execute() throws ExecutionException {
        int cid = courseId.getCourseId();
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message = courseExecutor.cancelCourse(cid);
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "cancelCourse");

        courseId = new CourseId(parameters.get(0));
        CourseValidator courseValidator = CourseValidator.getInstance();
        courseValidator.courseTokenValidate(courseId);

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(Arrays.asList("Teacher","Administrator"));

        courseValidator.isCourseExist();
    }
}
