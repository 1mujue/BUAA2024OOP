package entity.tokens;

import exceptions.ValidationException;
import manipulators.UserCourseManipulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname UserName
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:26
 * &#064;Created MuJue
 */
public class UserName extends Token<String>{
    public UserName(String name){
        this.value = name;
    }
    private static final String regex = "[a-zA-Z][a-zA-Z_]{3,15}";
    @Override
    public void validate() throws ValidationException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches()){
            return ;
        }
        throw new ValidationException("Illegal user name\n");
    }
}
