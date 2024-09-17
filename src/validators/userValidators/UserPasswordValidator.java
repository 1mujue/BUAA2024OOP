package validators.userValidators;

import exceptions.ValidationException;
import manipulators.UserManipulator;
import validators.TokenValidator;

/**
 * &#064;Classname UserPasswordValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:37
 * &#064;Created MuJue
 */
public class UserPasswordValidator extends TokenValidator {
    private final UserManipulator userManipulator = new UserManipulator();
    private static final UserPasswordValidator passwordValidator = new UserPasswordValidator();
    private UserPasswordValidator(){;}
    public static UserPasswordValidator getInstance(){
        return passwordValidator;
    }
    public void samePasswordValidate(String pass1, String pass2) throws ValidationException{
        if (!pass1.equals(pass2)) {
            throw new ValidationException("Passwords do not match\n");
        }
    }
    public void rightPasswordValidate(String password, String uid) throws ValidationException{
        String password2 = userManipulator.getUserById(uid).getPassword();
        if(!password2.equals(password)){
            throw new ValidationException("Wrong password\n");
        }
    }

}
