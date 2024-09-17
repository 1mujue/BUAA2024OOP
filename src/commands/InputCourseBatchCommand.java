package commands;

import entity.tokens.Path;
import enums.PATH;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.FileExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.FileValidator;
import validators.userValidators.UserPermissionValidator;
import validators.StateValidator;

import java.util.List;

/**
 * &#064;Classname InputCourseBatchCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:00
 * &#064;Created MuJue
 */
public class InputCourseBatchCommand extends BaseCommand{
    private Path path = null;
    private static final InputCourseBatchCommand inputCourseBatchCommand = new InputCourseBatchCommand();
    private InputCourseBatchCommand(){;}
    public static InputCourseBatchCommand getInstance(){
        return inputCourseBatchCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        FileExecutor fileExecutor = FileExecutor.getInstance();
        String message =  fileExecutor.inputTeacherCourse(path.getValue());
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "inputCourseBatch");

        path = new Path(PATH.DATA.getPath() + parameters.get(0));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Teacher"));

        FileValidator fileValidator = FileValidator.getInstance();
        fileValidator.isPathFileExists(path);
        fileValidator.isPathFileDirectory(path);
    }
}
