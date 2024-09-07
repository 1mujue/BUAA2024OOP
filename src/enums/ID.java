package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * &#064;Classname ID
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 20:51
 * &#064;Created MuJue
 */
public enum ID {
    UNDER_GRADUATE("(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!000)[0-9]{3})"),
    S_POST_GRADUATE("SY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})"),
    Z_POST_GRADUATE("ZY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})"),
    DOCTOR("BY(2[1-4])(0[1-9]|[1-3][0-9]|4[0-3])([1-6])((?!00)[0-9]{2})"),
    TEACHER("((?!00000)[0-9]{5})"),
    ADMINISTRATOR("AD((?!000)[0-9]{3})");
    private final String regex;
    ID(String regex){
        this.regex =regex;
    }
    public String getRegex(){
        return this.regex;
    }
    public static ID isMatch(String uid){
        for(ID id : values()){
            Pattern r = Pattern.compile(id.getRegex());
            Matcher m = r.matcher(uid);
            if(m.matches()){
                return id;
            }
        }
        return null;
    }
}
