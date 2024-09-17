package validators;

import commands.BaseCommand;
import enums.COMMAND;
import exceptions.ValidationException;

import java.util.Objects;

/**
 * &#064;Classname CommandValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:26
 * &#064;Created MuJue
 */
public class CommandValidator{
    public BaseCommand getCommand(String name) throws ValidationException{
        this.existenceValidate(name);
        return Objects.requireNonNull(COMMAND.getInstance(name)).getCommand();
    }
    public void existenceValidate(String name) throws ValidationException {
        COMMAND command = COMMAND.getInstance(name);
        if(command == null){
            throw new ValidationException("Command '" + name + "' not found\n");
        }
    }
}
