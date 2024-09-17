package commands;

import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserCourseExecutor;
import utils.Outputer;
import validators.*;
import validators.courseValidators.CourseExistenceValidator;
import validators.courseValidators.CourseSelectValidator;
import validators.userValidators.UserPermissionValidator;
import validators.userValidators.UserIdValidator;

import java.util.List;

/**
 * &#064;Classname ListCourseScheduleCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:02
 * &#064;Created MuJue
 */
public class ListCourseScheduleCommand extends BaseCommand{
    private UserId userId = null;
    private static final ListCourseScheduleCommand listCourseScheduleCommand = new ListCourseScheduleCommand();
    private ListCourseScheduleCommand(){;}
    public static ListCourseScheduleCommand getInstance(){
        return listCourseScheduleCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        String message = null;
        if(count == 0){
            message = noArgsExecute();
        } else if(count == 1){
            message = oneArgsExecute();
        }
        message += "List course schedule success\n";
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }
    private String noArgsExecute() throws ExecutionException{
        UserCourseExecutor userCourseExecutor = UserCourseExecutor.getInstance();
        return userCourseExecutor.listCourseSchedule();
    }
    private String oneArgsExecute() throws ExecutionException{
        UserCourseExecutor userCourseExecutor = UserCourseExecutor.getInstance();
        return userCourseExecutor.listCourseSchedule(userId.getValue());
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator= ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "listCourseSchedule");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        if(count == 0){
            noArgsValidate();
        } else if(count == 1){
            userId = new UserId(parameters.get(0));
            oneArgsValidate();
        }
    }
    private void noArgsValidate() throws ValidationException{
        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Student"));

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCurrentStudentSelectAnyCourse();
    }
    private void oneArgsValidate() throws ValidationException{
        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Administrator"));

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);
        userIdValidator.userIdExistenceValidate(userId.getValue());
        userIdValidator.isUserIdBelongsToStudent(userId.getValue());

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCertainStudentSelectAnyCourse(userId);
    }
}
