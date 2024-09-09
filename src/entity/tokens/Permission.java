package entity.tokens;

import exceptions.ValidationException;

import java.util.Arrays;
import java.util.List;

/**
 * &#064;Classname Permission
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 22:35
 * &#064;Created MuJue
 */
public class Permission extends Token<String>{
    private static final String student = "Student";
    private static final String teacher = "Teacher";
    private static final String administrator = "Administrator";
    private static final List<String> permissions = Arrays.asList(
            student,
            teacher,
            administrator
    );
    public Permission(String permission){
        this.value = permission;
    }
    @Override
    public void validate() throws ValidationException {
        for(String permission : permissions){
            if (value.equals(permission)) {
                return ;
            }
        }
        throw new ValidationException("Illegal identity\n");
    }
}
