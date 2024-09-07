package validators;

import exceptions.ValidationException;
import manipulators.UserManipulator;

/**
 * &#064;Classname PasswordValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:37
 * &#064;Created MuJue
 */
public class PasswordValidator{
    private final UserManipulator userManipulator = new UserManipulator();
    private static final PasswordValidator passwordValidator = new PasswordValidator();
    private PasswordValidator(){;}
    public static PasswordValidator getInstance(){
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
    public void legalityValidate(String password) throws ValidationException {
        int length = password.length();
        boolean isSpecial = false, isAlphabet = false, isNumber = false, isLegal = true;
        for(int i = 0;i < length;++i){
            char ch = password.charAt(i);
            if(ch == '@' || ch == '$' || ch == '_' || ch == '%'){
                isSpecial = true;
            }
            else if(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z'){
                isAlphabet = true;
            }
            else if(ch >= '0' && ch <= '9'){
                isNumber = true;
            }
            else {
                isLegal = false;
                break;
            }
        }
        if(!isLegal || !(isSpecial && isAlphabet && isNumber)){
            throw new ValidationException("Illegal password\n");
        }
    }
}
