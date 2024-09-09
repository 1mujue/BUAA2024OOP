package entity.tokens;

import exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname CourseName
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:37
 * &#064;Created MuJue
 */
public class CourseName extends Token<String>{
    private static final String regex = "[A-Za-z]([a-zA-Z0-9_-]*)";
    public CourseName(String courseName){
        this.value= courseName;
    }
    @Override
    public void validate() throws ValidationException {
        int length = value.length();
        if(length < 1 || length > 20){
            throw new ValidationException("Illegal course name\n");
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches()){
            throw new ValidationException("Illegal course name\n");
        }
    }
}
