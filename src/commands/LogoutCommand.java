package commands;

import entity.tokens.UserId;
import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.PermissionValidator;
import validators.StateValidator;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname LogoutCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class LogoutCommand extends BaseCommand{
    private UserId userId;
    @Override
    public void execute() throws ExecutionException {
        String message;
        if(count == 0){
            message = noArgsExecute();
        }else{
            message = adminArgsExecute();
        }
        Outputer.PRINT(message);
    }
    public String noArgsExecute() throws ExecutionException{
        UserExecutor userExecutor = UserExecutor.getInstance();
        return userExecutor.logout();
    }
    public String adminArgsExecute() throws ExecutionException{
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
            adminArgsValidate();
        }
    }
    private void noArgsValidate() throws ValidationException{
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(Arrays.asList(
                "Student",
                "Teacher",
                "Administrator"
        ));
    }
    private void adminArgsValidate() throws ValidationException{
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(List.of("Administrator"));

        userId = new UserId(parameters.get(0));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate(userId.getValue());
    }
}
