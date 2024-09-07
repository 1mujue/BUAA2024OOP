package data;

import users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname UserData
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 20:56
 * &#064;Created MuJue
 */
public class UserData {
    private final List<User> users = new ArrayList<>();
    private final List<String> loginUsers = new ArrayList<>();
    private static final UserData userData = new UserData();
    private UserData(){;}

    public static UserData getInstance(){
        return userData;
    }
    public List<User> getUsers(){
        return users;
    }
    public List<String> getLoginUsers(){
        return loginUsers;
    }
}
