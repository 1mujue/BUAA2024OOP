package commands;

import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.IdValidator;
import validators.PermissionValidator;
import validators.StateValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname PrintInfoCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class PrintInfoCommand extends BaseCommand{
    @Override
    public void execute() throws ExecutionException {
        UserExecutor userExecutor = UserExecutor.getInstance();
        String message;
        if(parameters.isEmpty()){
            message = userExecutor.printInfo();
        }
        else{
            String uid = parameters.get(0);
            message = userExecutor.printInfo(uid);
        }
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        int count = parameters.size();
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, REQUIRED_COUNT.PRINT_INFO_COUNT);

        if(count == 0){
            noArgsValidate();
        } else if(count == 1){
            adminArgsValidate();
        }
    }
    private void noArgsValidate() throws ValidationException{
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        List<String> permissions = new ArrayList<>();
        permissions.add("Student");
        permissions.add("Teacher");
        permissions.add("Administrator");
        permissionValidator.legalityValidate(permissions);

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();
    }
    private void adminArgsValidate() throws ValidationException{
        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        List<String> permissions = new ArrayList<>();
        permissions.add("Administrator");
        permissionValidator.legalityValidate(permissions);

        IdValidator idValidator = IdValidator.getInstance();
        idValidator.legalityValidate(parameters.get(0));
        try {
            idValidator.existenceValidate(parameters.get(0));
        } catch (ValidationException e) {
            return;
        }
        throw new ValidationException("User id does not exist\n");
    }
}
