package executors;

import enums.PERMISSION;
import exceptions.ExecutionException;
import manipulators.StateManipulator;
import manipulators.UserManipulator;
import users.User;

import java.util.List;

/**
 * &#064;Classname UserExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/5 12:43
 * &#064;Created MuJue
 */
public class UserExecutor {
    private static final UserManipulator userManipulator = UserManipulator.getInstance();
    private static final StateManipulator stateManipulator = StateManipulator.getInstance();
    private static final UserExecutor userExecutor = new UserExecutor();
    private UserExecutor(){;}
    public static UserExecutor getInstance(){
        return userExecutor;
    }
    public String register(User user) throws ExecutionException{
        userManipulator.addUser(user);
        return "Register success\n";
    }
    public String login(String uid) throws ExecutionException{
        PERMISSION permission = userManipulator.getUserById(uid).getPermission();
        stateManipulator.clearState();
        stateManipulator.setStateId(uid);
        stateManipulator.setStatePermission(permission);
        userManipulator.addOnlineUser(uid);
        return "Welcome to ACP, " + uid + "\n";
    }
    public String logout(String uid) throws ExecutionException{
        userManipulator.removeOnlineUser(uid);
        if(uid.equals(stateManipulator.getStateId())){
            stateManipulator.clearState();
        }
        return uid + " Bye~\n";
    }
    public String logout() throws ExecutionException{
        String uid = stateManipulator.getStateId();
        userManipulator.removeOnlineUser(uid);
        stateManipulator.clearState();
        return uid + " Bye~\n";
    }
    public String quit() throws ExecutionException{
        List<String> onlineUsers = userManipulator.getOnlineUsers();
        List<String> tmpOnlineUsers = onlineUsers.subList(0, onlineUsers.size());
        StringBuilder message = new StringBuilder();
        for(String uid : tmpOnlineUsers){
            message.append(logout(uid));
        }
        message.append("----- Good Bye! -----\n");
        return message.toString();
    }
    public String printInfo(String uid) throws ExecutionException{
        User user = userManipulator.getUserById(uid);
        return user.toString();
    }
    public String printInfo() throws ExecutionException{
        String uid = stateManipulator.getStateId();
        User user = userManipulator.getUserById(uid);
        return user.toString();
    }
}
