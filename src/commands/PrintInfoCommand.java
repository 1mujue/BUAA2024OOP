package commands;

import entity.User;
import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.PermissionValidator;
import validators.StateValidator;
import validators.UserValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname PrintInfoCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class PrintInfoCommand extends BaseCommand{
    private UserId userId;
    @Override
    public void execute() throws ExecutionException {
        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = null;
        if(count == 0){
            message = userExecutor.printInfo();
        }
        else if (count == 1){
            message = userExecutor.printInfo(userId.getValue());
        }
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "printInfo");

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

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();
    }
    private void adminArgsValidate() throws ValidationException{
        userId = new UserId(parameters.get(0));

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.legalityValidate(List.of("Administrator"));

        UserValidator userValidator = UserValidator.getInstance();
        userValidator.userIdExistenceValidate(userId.getValue());
    }
}
