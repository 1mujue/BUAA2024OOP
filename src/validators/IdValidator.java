package validators;

import enums.ID;
import exceptions.ValidationException;
import manipulators.UserManipulator;

import java.util.List;

/**
 * &#064;Classname IdValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:37
 * &#064;Created MuJue
 */
public class IdValidator{
    private static final IdValidator idValidator = new IdValidator();
    private IdValidator(){;}
    public static IdValidator getInstance(){
        return idValidator;
    }
    public void existenceValidate(String uid) throws ValidationException {
        if(UserManipulator.getInstance().isUserExist(uid)){
            throw new ValidationException("User id exists\n");
        }
    }
    public void legalityValidate(String uid) throws ValidationException {
        if(ID.isMatch(uid) == null){
            throw new ValidationException("Illegal user id\n");
        }
    }
}
