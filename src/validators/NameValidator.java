package validators;

import exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname NameValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 21:16
 * &#064;Created MuJue
 */
public class NameValidator{
    private static final String userNameRegex = "[a-zA-Z][a-zA-Z_]{3,15}";
    private static final NameValidator nameValidator = new NameValidator();
    private NameValidator(){;}
    public static NameValidator getInstance(){
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
