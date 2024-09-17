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
import validators.userValidators.UserNameValidator;
import validators.userValidators.UserPasswordValidator;
import validators.userValidators.UserPermissionValidator;
import validators.userValidators.UserIdValidator;

/**
 * &#064;Classname RegisterCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:11
 * &#064;Created MuJue
 */
public class RegisterCommand extends BaseCommand{
    private UserId userId = null;
    private UserName userName = null;
    private Password password = null;
    private Password rePassword = null;
    private Permission permission = null;
    private static final RegisterCommand registerCommand = new RegisterCommand();
    private RegisterCommand(){;}
    public static RegisterCommand getInstance(){
        return registerCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        User user = new User();

        user.setId(userId.getValue());
        user.setName(userName.getValue());
        user.setPassword(password.getValue());
        user.setPermission(permission.getValue());

        UserExecutor userExecutor = UserExecutor.getInstance();
        String message = userExecutor.register(user);
        Outputer outputer = Outputer.getInstance();
        outputer.PRINT(message);
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

        UserIdValidator userIdValidator = UserIdValidator.getInstance();
        userIdValidator.tokenValidate(userId);
        userIdValidator.userIdRegisterExistenceValidate(userId.getValue());

        UserNameValidator userNameValidator = UserNameValidator.getInstance();
        userNameValidator.userNameLegalityValidate(userName.getValue());

        UserPasswordValidator userPasswordValidator = UserPasswordValidator.getInstance();
        userPasswordValidator.tokenValidate(password);
        userPasswordValidator.samePasswordValidate(password.getValue(), rePassword.getValue());

        UserPermissionValidator userPermissionValidator = UserPermissionValidator.getInstance();
        userPermissionValidator.existenceValidate(permission);
    }
}
