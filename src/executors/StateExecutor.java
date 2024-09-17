package executors;

import entity.User;
import exceptions.ExecutionException;
import manipulators.StateManipulator;
import manipulators.UserManipulator;

/**
 * &#064;Classname StateExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/14 21:55
 * &#064;Created MuJue
 */
public class StateExecutor {
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final StateExecutor stateExecutor = new StateExecutor();
    private StateExecutor(){;}
    public static StateExecutor getInstance(){
        return stateExecutor;
    }
    public String switchState(String uid) throws ExecutionException{
        String permission = userManipulator.getUserPermission(uid);
        stateManipulator.clearState();
        stateManipulator.setStateId(uid);
        stateManipulator.setStatePermission(permission);
        return "Switch to " + uid + '\n';
    }
}
