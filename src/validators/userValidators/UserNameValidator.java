package validators.userValidators;

import exceptions.ValidationException;
import validators.TokenValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname UserNameValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 21:16
 * &#064;Created MuJue
 */
public class UserNameValidator extends TokenValidator {
    private static final String userNameRegex = "[a-zA-Z][a-zA-Z_]{3,15}";
    private static final UserNameValidator nameValidator = new UserNameValidator();
    private UserNameValidator(){;}
    public static UserNameValidator getInstance(){
        return nameValidator;
    }

    public void userNameLegalityValidate(String name) throws ValidationException {
        int length = name.length();
        if(length < 4 || length > 16){
            throw new ValidationException("Illegal user name\n");
        }
        Pattern r = Pattern.compile(userNameRegex);
        Matcher m = r.matcher(name);
        if(!m.matches()){
            throw new ValidationException("Illegal user name\n");
        }
    }

}
