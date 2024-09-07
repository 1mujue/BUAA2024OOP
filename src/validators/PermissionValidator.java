package validators;

import enums.PERMISSION;
import exceptions.ValidationException;

import java.util.List;

/**
 * &#064;Classname PermissionValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:34
 * &#064;Created MuJue
 */
public class PermissionValidator{
    private PERMISSION currentPermission;
    private static final PermissionValidator permissionValidator = new PermissionValidator();
    private  PermissionValidator(){;}
    public static PermissionValidator getInstance(){
        return permissionValidator;
    }

    public void setCurrentPermission(PERMISSION currentPermission) {
        this.currentPermission = currentPermission;
    }

    public void existenceValidate(String permission) throws ValidationException {
        if(PERMISSION.getInstance(permission) == null){
            throw new ValidationException("Illegal identity\n");
        }
    }
    public void legalityValidate(List<String> requiredPermission) throws ValidationException {
        int flag = 0;
        for(String permission : requiredPermission){
            if(currentPermission.equals(PERMISSION.getInstance(permission))){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            throw new ValidationException("Permission denied\n");
        }
    }
}
