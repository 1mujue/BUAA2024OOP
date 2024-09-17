package commands;

import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseExistenceValidator;
import validators.userValidators.UserPermissionValidator;
import validators.userValidators.UserIdValidator;

import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname ListCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:30
 * &#064;Created MuJue
 */
public class ListCourseCommand extends BaseCommand{
    private UserId userId = null;
    private static final ListCourseCommand listCourseCommand = new ListCourseCommand();
    private ListCourseCommand(){;}
    public static ListCourseCommand getInstance(){
        return listCourseCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message;
        if(count == 0){
            message = courseExecutor.listCourse();
        }else {
            String tid = userId.getValue();
            message = courseExecutor.listCourse(tid);
        }
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "listCourse");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        if(count == 0){
            noArgsValidate();
        }else{
            userId = new UserId(parameters.get(0));
            oneArgsValidate();
        }

    }
    public void noArgsValidate() throws ValidationException{
        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(Arrays.asList(
                "Student",
                "Teacher",
                "Administrator"
        ));

        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.isAnyCourseExist();
    }
    public void oneArgsValidate() throws ValidationException {
        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Administrator"));

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);
        userIdValidator.userIdExistenceValidate(userId.getValue());

        userPermissionValidator.legalityValidate(userId.getValue(), List.of("Teacher"));

        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.isCertainTeacherAnyCourseExist(userId.getValue());
    }
}
