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
    private static final String regex = "^(?!_)[a-zA-Z_]{4,16}";
    private static final NameValidator nameValidator = new NameValidator();
    private NameValidator(){;}
    public static NameValidator getInstance(){
        return nameValidator;
    }

    public void legalityValidate(String name) throws ValidationException {
        int length = name.length();
        if(length < 4 || length > 16){
            throw new ValidationException("Illegal name\n");
        }
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(name);
        if(!m.matches()){
            throw new ValidationException("Illegal name\n");
        }
    }
}
