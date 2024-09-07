package commands;

import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.PermissionValidator;
import validators.StateValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname LogoutCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class LogoutCommand extends BaseCommand{
    @Override
    public void execute() throws ExecutionException {
        int count = parameters.size();
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
        String uid = parameters.get(0);
        return userExecutor.logout(uid);
    }

    @Override
    public void validate() throws ValidationException {
        int count = parameters.size();
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, REQUIRED_COUNT.LOGOUT_COUNT);

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        if(count == 0){
            noArgsValidate();
        } else if(count == 1){
            adminArgsValidate();
        }
    }
    private void noArgsValidate() throws ValidationException{
        List<String> permissions = new ArrayList<>();
        permissions.add("Student");
        permissions.add("Teacher");
        permissions.add("Administrator");
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(permissions);
    }
    private void adminArgsValidate() throws ValidationException{
        List<String> permissions = new ArrayList<>();
        permissions.add("Administrator");
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(permissions);

        String uid = parameters.get(0);
        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate(uid);
    }
}
