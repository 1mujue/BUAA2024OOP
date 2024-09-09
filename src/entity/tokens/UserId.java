package entity.tokens;

import exceptions.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname UserId
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:16
 * &#064;Created MuJue
 */
public class UserId extends Token<String>{
    private static final String underGraduate = "(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!000)[0-9]{3})";
    private static final String SYPostGraduate = "SY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})";
    private static final String ZYPostGraduate = "ZY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})";
    private static final String doctor = "BY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})";
    private static final String teacher = "(?!00000)[0-9]{5}";
    private static final String administrator = "AD((?!000)[0-9]{3})";
    private static final List<String> rules = Arrays.asList(
            underGraduate,
            SYPostGraduate,
            ZYPostGraduate,
            doctor,
            teacher,
            administrator
    );
    public UserId(String userId){
        this.value = userId;
    }
    @Override
    public void validate() throws ValidationException {
        Pattern pattern;
        Matcher matcher;

        for(String rule : rules){
            pattern = Pattern.compile(rule);
            matcher = pattern.matcher(value);
            if(matcher.matches()){
                return ;
            }
        }
        throw new ValidationException("Illegal user id\n");
    }
}
