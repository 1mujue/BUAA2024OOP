package commands;

import entity.tokens.CourseId;
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

import java.util.Arrays;

/**
 * &#064;Classname RemoveStudentCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:31
 * &#064;Created MuJue
 */
public class RemoveStudentCommand extends BaseCommand{
    private UserId userId = null;
    private CourseId courseId = null;
    private static final RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand();
    private RemoveStudentCommand(){;}
    public static RemoveStudentCommand getInstance(){
        return removeStudentCommand;
    }


    @Override
    public void execute() throws ExecutionException {
        UserCourseExecutor userCourseExecutor = UserCourseExecutor.getInstance();
        String message = null;
        if(count == 1){
            message = userCourseExecutor.removeStudent(userId.getValue());
        } else if(count == 2){
            message = userCourseExecutor.removeStudent(userId.getValue(), courseId.getCourseId());
        }
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "removeStudent");

        userId = new UserId(parameters.get(0));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(
                Arrays.asList(
                        "Teacher",
                        "Administrator"
                )
        );

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);

        userIdValidator.userIdExistenceValidate(userId.getValue());

        userIdValidator.isUserIdBelongsToStudent(userId.getValue());

        if(count == 1){
            oneArgsValidate();
        } else if(count == 2){
            courseId = new CourseId(parameters.get(1));
            twoArgsValidate();
        }
    }
    private void oneArgsValidate() throws ValidationException{
        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCertainStudentSelectAnyCourse(userId);
    }
    private void twoArgsValidate() throws ValidationException{
        CourseExistenceValidator courseExistenceValidator = CourseExistenceValidator.getInstance();
        courseExistenceValidator.tokenValidate(courseId);
        courseExistenceValidator.isCertainCourseExist(courseId);

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCertainStudentSelectCertainCourse(userId, courseId);
    }
}
