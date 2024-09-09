package commands;

import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.CourseExecutor;
import utils.Outputer;
import validators.*;

import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname ListCourseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:30
 * &#064;Created MuJue
 */
public class ListCourseCommand extends BaseCommand{
    private UserId userId;
    @Override
    public void execute() throws ExecutionException {
        int count = parameters.size();
        CourseExecutor courseExecutor = CourseExecutor.getInstance();
        String message;
        if(count == 0){
            message = courseExecutor.listCourse();
        }else {
            String tid = (String)parameters.get(0);
            message = courseExecutor.listCourse(tid);
        }
        Outputer.PRINT(message);
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
            adminArgsValidate();
        }

    }
    public void noArgsValidate() throws ValidationException{
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(Arrays.asList("Student","Teacher","Administrator"));

        CourseValidator courseValidator = CourseValidator.getInstance();
        courseValidator.isCourseExist();
    }
    public void adminArgsValidate() throws ValidationException{
        userId  = new UserId(parameters.get(0));

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(List.of("Administrator"));

        UserValidator userValidator = UserValidator.getInstance();
        userValidator.userIdExistenceValidate(userId.getValue());

        permissionValidator.legalityValidate(userId.getValue(), List.of("Teacher"));

        CourseValidator courseValidator = CourseValidator.getInstance();
        courseValidator.isCourseExist();
    }
}
