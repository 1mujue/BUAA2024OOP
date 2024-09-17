package commands;

import entity.tokens.UserId;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.StateExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.StateValidator;
import validators.userValidators.UserIdValidator;

/**
 * &#064;Classname SwitchCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:00
 * &#064;Created MuJue
 */
public class SwitchCommand extends BaseCommand{
    private UserId userId = null;
    private static final SwitchCommand switchCommand = new SwitchCommand();
    private SwitchCommand(){;}
    public static SwitchCommand getInstance(){
        return switchCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        StateExecutor stateExecutor = StateExecutor.getInstance();
        String message = stateExecutor.switchState(userId.getValue());
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "switch");

        userId = new UserId(parameters.get(0));

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);

        userIdValidator.userIdExistenceValidate(userId.getValue());

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate(userId.getValue());
    }
}
