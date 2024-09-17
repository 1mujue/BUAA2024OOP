package commands;

import entity.tokens.CourseId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserCourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseExistenceValidator;
import validators.courseValidators.CourseSelectValidator;
import validators.userValidators.UserPermissionValidator;

import java.util.Arrays;

/**
 * &#064;Classname ListStudentCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:02
 * &#064;Created MuJue
 */
public class ListStudentCommand extends BaseCommand{
    private CourseId courseId = null;
    private static final ListStudentCommand listStudentCommand = new ListStudentCommand();
    private ListStudentCommand(){;}
    public static ListStudentCommand getInstance(){
        return listStudentCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        UserCourseExecutor userCourseExecutor = UserCourseExecutor.getInstance();
        String message = userCourseExecutor.listStudent(courseId.getCourseId());
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "listStudent");

        courseId = new CourseId(parameters.get(0));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(Arrays.asList(
                "Teacher",
                "Administrator"
        ));

        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.tokenValidate(courseId);
        courseExistenceValidator.isCertainCourseExist(courseId);

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isAnyStudentSelectCertainCourse(courseId);
    }
}
