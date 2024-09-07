package commands;

import enums.PERMISSION;
import enums.REQUIRED_COUNT;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import manipulators.UserManipulator;
import users.User;
import utils.Outputer;
import validators.*;

/**
 * &#064;Classname RegisterCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class RegisterCommand extends BaseCommand{
    @Override
    public void execute() throws ExecutionException {
        User user = new User();
        String uid = parameters.get(0);
        String name = parameters.get(1);
        String password = parameters.get(2);
        String permission = parameters.get(4);
        user.setId(uid);
        user.setName(name);
        user.setPassword(password);
        user.setPermission(PERMISSION.getInstance(permission));

        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = userExecutor.register(user);
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(
                parameters.size(), REQUIRED_COUNT.REGISTER_COUNT
        );

        IdValidator idValidator = IdValidator.getInstance();
        idValidator.legalityValidate(parameters.get(0));
        idValidator.existenceValidate(parameters.get(0));

        NameValidator nameValidator = NameValidator.getInstance();
        nameValidator.legalityValidate(parameters.get(1));

        PasswordValidator passwordValidator = PasswordValidator.getInstance();
        passwordValidator.legalityValidate(parameters.get(2));

        passwordValidator.samePasswordValidate(parameters.get(2), parameters.get(3));

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.existenceValidate(parameters.get(4));
    }
}
