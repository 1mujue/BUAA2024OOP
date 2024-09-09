package commands;

import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;

import java.util.List;

/**
 * &#064;Classname QuitCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:12
 * &#064;Created MuJue
 */
public class QuitCommand extends BaseCommand{
    @Override
    public void execute() throws ExecutionException {
        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = userExecutor.quit();
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "quit");
    }
}
