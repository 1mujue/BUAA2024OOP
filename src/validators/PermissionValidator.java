package validators;

import entity.User;
import entity.tokens.Permission;
import exceptions.ValidationException;
import manipulators.StateManipulator;
import manipulators.UserManipulator;

import java.util.List;

/**
 * &#064;Classname PermissionValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:34
 * &#064;Created MuJue
 */
public class PermissionValidator{
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final PermissionValidator permissionValidator = new PermissionValidator();
    private  PermissionValidator(){;}
    public static PermissionValidator getInstance(){
        return permissionValidator;
    }

    public void existenceValidate(Permission permission) throws ValidationException {
        permission.validate();
    }
    public void legalityValidate(List<String> requiredPermission) throws ValidationException {
        int flag = 0;
        String currentPermission = stateManipulator.getStatePermission();
        for(String permission : requiredPermission){
            if(currentPermission.equals(permission)){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            throw new ValidationException("Permission denied\n");
        }
    }
    public void legalityValidate(String uid, List<String> requiredPermission) throws ValidationException{
        int flag = 0;
        User user = userManipulator.getUserById(uid);
        String currentPermission = user.getPermission();
        for(String permission : requiredPermission){
            if(currentPermission.equals(permission)){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            throw new ValidationException("User id does not belong to a Teacher\n");
        }
    }
}
