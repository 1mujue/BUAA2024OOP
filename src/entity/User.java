package entity;

/**
 * &#064;Classname User
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 15:24
 * &#064;Created MuJue
 */
public class User {
    private String id;
    private String name;
    private String password;
    private String permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Type: " + permission + "\n";
    }
}
