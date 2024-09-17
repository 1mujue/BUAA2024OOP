package commands;

import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.userValidators.UserPermissionValidator;
import validators.StateValidator;
import validators.userValidators.UserIdValidator;

import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname LogoutCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class LogoutCommand extends BaseCommand{
    private UserId userId = null;
    private static final LogoutCommand logoutCommand = new LogoutCommand();
    private LogoutCommand(){;}
    public static LogoutCommand getInstance(){
        return logoutCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        String message;
        if(count == 0){
            message = noArgsExecute();
        }else{
            message = oneArgsExecute();
        }
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }
    public String noArgsExecute() throws ExecutionException{
        UserExecutor userExecutor = UserExecutor.getInstance();
        return userExecutor.logout();
    }
    public String oneArgsExecute() throws ExecutionException{
        UserExecutor userExecutor = UserExecutor.getInstance();
        return userExecutor.logout(userId.getValue());
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "logout");

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
        userPermissionValidator.legalityValidate(Arrays.asList(
                "Student",
                "Teacher",
                "Administrator"
        ));
    }
    private void oneArgsValidate() throws ValidationException{
        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.legalityValidate(List.of("Administrator"));

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);
        userIdValidator.userIdExistenceValidate(userId.getValue());

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate(userId.getValue());
    }
}
