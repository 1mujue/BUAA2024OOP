package commands;

import entity.tokens.Password;
import entity.tokens.Permission;
import entity.tokens.UserId;
import entity.tokens.UserName;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.UserExecutor;
import entity.User;
import utils.Outputer;
import validators.*;

/**
 * &#064;Classname RegisterCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class RegisterCommand extends BaseCommand{
    private UserId userId;
    private UserName userName;
    private Password password;
    private Password rePassword;
    private Permission permission;
    @Override
    public void execute() throws ExecutionException {
        User user = new User();

        user.setId(userId.getValue());
        user.setName(userName.getValue());
        user.setPassword(password.getValue());
        user.setPermission(permission.getValue());

        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = userExecutor.register(user);
        Outputer.PRINT(message);
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "register");

        userId = new UserId(parameters.get(0));
        userName = new UserName(parameters.get(1));
        password = new Password(parameters.get(2));
        rePassword = new Password(parameters.get(3));
        permission = new Permission(parameters.get(4));

        UserValidator userValidator = UserValidator.getInstance();
        userValidator.userTokenValidate(userId);
        userValidator.userIdRegisterExistenceValidate(userId.getValue());

        NameValidator nameValidator = NameValidator.getInstance();
        nameValidator.userNameLegalityValidate(userName.getValue());

        PasswordValidator passwordValidator = PasswordValidator.getInstance();
        passwordValidator.legalityValidate(password.getValue());

        passwordValidator.samePasswordValidate(password.getValue(), rePassword.getValue());

        PermissionValidator permissionValidator = PermissionValidator.getInstance();
        permissionValidator.existenceValidate(permission);
    }
}
