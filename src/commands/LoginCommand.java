package commands;

import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.IdValidator;
import validators.PasswordValidator;
import validators.StateValidator;

import java.util.List;

/**
 * &#064;Classname LoginCommand
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 10:37
 * &#064;Created MuJue
 */
public class LoginCommand extends BaseCommand{
    @Override
    public void execute() throws ExecutionException {
        UserExecutor userExecutor = UserExecutor.getInstance();
        String uid = parameters.get(0);
        String message = userExecutor.login(uid);
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(parameters.size(), REQUIRED_COUNT.LOGIN_COUNT);

        IdValidator idValidator = IdValidator.getInstance();
        idValidator.legalityValidate(parameters.get(0));

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.loginConflictValidate(parameters.get(0));

        try {
            idValidator.existenceValidate(parameters.get(0));
        } catch (ValidationException e) {
            PasswordValidator passwordValidator = PasswordValidator.getInstance();
            passwordValidator.rightPasswordValidate(parameters.get(1), parameters.get(0));
            return ;
        }
        throw new ValidationException("User id does not exists\n");
    }
}
