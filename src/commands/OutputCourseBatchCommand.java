package commands;

import entity.tokens.Path;
import enums.PATH;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.FileExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.userValidators.UserPermissionValidator;
import validators.StateValidator;

import java.util.List;

/**
 * &#064;Classname OutputCourseBatchCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:01
 * &#064;Created MuJue
 */
public class OutputCourseBatchCommand extends BaseCommand{
    private Path path = null;
    private static final OutputCourseBatchCommand outputCourseBatchCommand = new OutputCourseBatchCommand();
    private OutputCourseBatchCommand(){;}
    public static OutputCourseBatchCommand getInstance(){
        return outputCourseBatchCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        path = new Path(PATH.DATA.getPath() + parameters.get(0));
        FileExecutor fileExecutor = FileExecutor.getInstance();
        String message = fileExecutor.outputTeacherCourse(path.getValue());
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "outputCourseBatch");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Teacher"));
    }
}
