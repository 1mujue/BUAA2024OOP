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
 * &#064;Classname PrintInfoCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class PrintInfoCommand extends BaseCommand{
    private UserId userId = null;
    private static final PrintInfoCommand printInfoCommand = new PrintInfoCommand();
    private PrintInfoCommand(){;}
    public static PrintInfoCommand getInstance(){
        return printInfoCommand;
    }
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
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
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
    }
}
