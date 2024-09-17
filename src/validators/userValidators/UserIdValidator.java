package validators.userValidators;

import entity.User;
import exceptions.ValidationException;
import manipulators.UserManipulator;
import validators.TokenValidator;

/**
 * &#064;Classname UserIdValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/11 14:06
 * &#064;Created MuJue
 */
public class UserIdValidator extends TokenValidator {
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final UserIdValidator userIdValidator = new UserIdValidator();
    private UserIdValidator(){;}
    public static UserIdValidator getInstance(){
        return userIdValidator;
    }
    public void userIdRegisterExistenceValidate(String uid) throws ValidationException {
        if(isUserIdExist(uid)){
            throw new ValidationException("User id exists\n");
        }
    }
    public void userIdExistenceValidate(String uid) throws ValidationException{
        if(!isUserIdExist(uid)){
            throw new ValidationException("User does not exist\n");
        }
    }
    public void isUserIdBelongsToStudent(String uid) throws ValidationException{
        User user = userManipulator.getUserById(uid);
        if(!user.getPermission().equals("Student")){
            throw new ValidationException("User id does not belong to a Student\n");
        }
    }
    private boolean isUserIdExist(String uid) {
        return userManipulator.isUserExist(uid);
    }
}
