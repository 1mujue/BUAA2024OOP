package validators;

import entity.tokens.Token;
import exceptions.ValidationException;
import manipulators.UserManipulator;

/**
 * &#064;Classname UserValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 22:11
 * &#064;Created MuJue
 */
public class UserValidator {
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final UserValidator userValidator = new UserValidator();
    private UserValidator(){;}
    public static UserValidator getInstance(){
        return userValidator;
    }
    public void userIdRegisterExistenceValidate(String uid) throws ValidationException{
        if(isUserIdExist(uid)){
            throw new ValidationException("User id exists\n");
        }
    }
    public void userIdExistenceValidate(String uid) throws ValidationException{
        if(!isUserIdExist(uid)){
            throw new ValidationException("User id does not exist\n");
        }
    }
    private boolean isUserIdExist(String uid) {
        return userManipulator.isUserExist(uid);
    }
    public void userTokenValidate(Token token) throws ValidationException{
        token.validate();
    }
}
