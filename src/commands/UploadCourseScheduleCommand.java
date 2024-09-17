package commands;

import enums.PATH;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserCourseExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.courseValidators.CourseExistenceValidator;
import validators.courseValidators.CourseSelectValidator;
import validators.userValidators.UserPermissionValidator;
import validators.StateValidator;

import java.util.List;

/**
 * &#064;Classname UploadCourseScheduleCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:02
 * &#064;Created MuJue
 */
public class UploadCourseScheduleCommand extends BaseCommand{
    private static final UploadCourseScheduleCommand uploadCourseScheduleCommand = new UploadCourseScheduleCommand();
    private UploadCourseScheduleCommand(){;}
    public static UploadCourseScheduleCommand getInstance(){
        return uploadCourseScheduleCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        UserCourseExecutor userCourseExecutor = UserCourseExecutor.getInstance();
        String message = userCourseExecutor.listCourseSchedule();
        Outputer outputer = Outputer.getInstance();
        outputer.setPath(PATH.DATA.getPath() + parameters.get(0));
        outputer.setMode(false);
        outputer.PRINT(message);
        outputer.setPath(PATH.OUT.getPath());
        outputer.setMode(true);
        outputer.PRINT("Upload course schedule success\n");
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "uploadCourseSchedule");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Student"));

        CourseSelectValidator courseSelectValidator = CourseSelectValidator.getInstance();
        courseSelectValidator.isCurrentStudentSelectAnyCourse();
    }
}
