package validators;

import exceptions.ValidationException;
import manipulators.StateManipulator;
import manipulators.UserManipulator;

/**
 * &#064;Classname StateValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 22:28
 * &#064;Created MuJue
 */
public class StateValidator{
    private static final StateValidator stateValidator = new StateValidator();
    private final StateManipulator stateManipulator = StateManipulator.getInstance();
    private final UserManipulator userManipulator = UserManipulator.getInstance();
    private StateValidator(){;}
    public static StateValidator getInstance(){
        return stateValidator;
    }
    public void loginConflictValidate(String uid) throws ValidationException{
        if(isUserOnline(uid)){
            throw new ValidationException(uid + " is online\n");
        }
    }
    public void onlineValidate(String uid) throws ValidationException{
        if(!isUserOnline(uid)){
            throw new ValidationException(uid + " is not online\n");
        }
    }
    public void onlineValidate() throws ValidationException{
        if(!isUserOnline(stateManipulator.getStateId())){
            throw new ValidationException("No one is online\n");
        }
    }
    private boolean isUserOnline(String uid){
        return userManipulator.isUserOnline(uid);
    }
}
