package manipulators;

import data.UserData;
import enums.PERMISSION;
import users.User;

import java.util.List;

/**
 * &#064;Classname UserManipulator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 21:00
 * &#064;Created MuJue
 */
public class UserManipulator {
    private static final UserData userData = UserData.getInstance();
    private static final UserManipulator userManipulator = new UserManipulator();
    public static UserManipulator getInstance(){
        return userManipulator;
    }
    public boolean isUserExist(String id){
        List<User> users = userData.getUsers();
        for(User user : users){
            if(user.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public User getUserById(String id){
        List<User> users = userData.getUsers();
        for(User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    public void addUser(User user){
        userData.getUsers().add(user);
    }
    public void addOnlineUser(String uid){
        userData.getLoginUsers().add(uid);
    }

    public boolean isUserOnline(String uid){
        List<String> loginUsers = UserData.getInstance().getLoginUsers();
        for(String id : loginUsers){
            if(id.equals(uid)){
                return true;
            }
        }
        return false;
    }
    public void removeOnlineUser(String uid){
        userData.getLoginUsers().remove(uid);
    }
    public List<String> getOnlineUsers(){
        return userData.getLoginUsers();
    }
}
