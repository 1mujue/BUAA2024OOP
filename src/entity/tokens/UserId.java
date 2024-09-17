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
    private boolean isUnderGraduate(){
        return isUnderGraduate(value);
    }
    private boolean isUnderGraduate(String userId){
        Pattern pattern = Pattern.compile(underGraduate);
        Matcher matcher = pattern.matcher(userId);
        return matcher.matches();
    }
    private boolean isSYPostGraduate(){
        return isSYPostGraduate(value);
    }
    private boolean isSYPostGraduate(String userId){
        Pattern pattern = Pattern.compile(SYPostGraduate);
        Matcher matcher = pattern.matcher(userId);
        return matcher.matches();
    }
    private boolean isZYPostGraduate(){
        return isZYPostGraduate(value);
    }
    private boolean isZYPostGraduate(String userId){
        Pattern pattern = Pattern.compile(ZYPostGraduate);
        Matcher matcher = pattern.matcher(userId);
        return matcher.matches();
    }
    private boolean isDoctor(){
        return isDoctor(value);
    }
    private boolean isDoctor(String userId){
        Pattern pattern = Pattern.compile(doctor);
        Matcher matcher = pattern.matcher(userId);
        return matcher.matches();
    }
    public int getScore(){
        if(isDoctor()){
            return 4;
        } else if(isSYPostGraduate()){
            return 3;
        } else if(isZYPostGraduate()){
            return 2;
        } else if (isDoctor()) {
            return 1;
        }
        return 0;
    }
    public int getNumber(){
        int ans = 0;
        if(isDoctor() || isSYPostGraduate() || isZYPostGraduate()){
            for(int i = 2;i < 9;++i){
                ans = ans * 10 + value.charAt(i) - '0';
            }
        } else if(isUnderGraduate()){
            for(int i = 0;i < 8;++i){
                ans = ans * 10 + value.charAt(i) - '0';
            }
        }
        return ans;
    }
}
