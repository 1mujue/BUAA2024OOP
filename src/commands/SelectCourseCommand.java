package commands;

import entity.tokens.CourseId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseExistenceValidator;
import validators.courseValidators.CourseScheduleTimeValidator;
import validators.courseValidators.CourseSelectValidator;
import validators.userValidators.UserPermissionValidator;

import java.util.List;

/**
 * &#064;Classname SelectCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:32
 * &#064;Created MuJue
 */
public class SelectCourseCommand extends BaseCommand{
    private CourseId courseId = null;
    private static final SelectCourseCommand selectCourseCommand = new SelectCourseCommand();
    private SelectCourseCommand(){;}
    public static SelectCourseCommand getInstance(){
        return selectCourseCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        int cid = courseId.getCourseId();
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message = courseExecutor.selectCourse(cid);
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "selectCourse");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Student"));

        courseId = new CourseId(parameters.get(0));
        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.tokenValidate(courseId);
        courseExistenceValidator.isCertainCourseExist(courseId);

        CourseScheduleTimeValidator courseScheduleTimeValidator = CourseScheduleTimeValidator.getInstance();
        courseScheduleTimeValidator.studentCourseScheduleTimeConflictValidate(courseId);

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCertainCourseSelectNumberReachLimit(courseId);
    }
}
