package commands;

import entity.tokens.Password;
import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.userValidators.UserPasswordValidator;
import validators.StateValidator;
import validators.userValidators.UserIdValidator;

/**
 * &#064;Classname LoginCommand
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 10:37
 * &#064;Created MuJue
 */
public class LoginCommand extends BaseCommand{
    private UserId userId = null;
    private Password password = null;
    private static final LoginCommand loginCommand = new LoginCommand();
    private LoginCommand(){;}
    public static LoginCommand getInstance(){
        return loginCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = userExecutor.login(userId.getValue());
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "login");

        userId = new UserId(parameters.get(0));
        password = new Password(parameters.get(1));

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);
        userIdValidator.userIdExistenceValidate(userId.getValue());

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.loginConflictValidate(userId.getValue());

        UserPasswordValidator userPasswordValidator = UserPasswordValidator.getInstance();
        userPasswordValidator.rightPasswordValidate(password.getValue(), userId.getValue());
    }
}
