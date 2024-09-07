package enums;

/**
 * &#064;Classname PERMISSION
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 19:53
 * &#064;Created MuJue
 */
public enum PERMISSION {
    STUDENT("Student"),
    TEACHER("Teacher"),
    ADMINISTRATOR("Administrator");
    // no one is online.
    private final String name;
    PERMISSION(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public static PERMISSION getInstance(String name){
        for(PERMISSION permission : values()){
            if(permission.name.equals(name)){
                return permission;
            }
        }
        return null;
    }
}
