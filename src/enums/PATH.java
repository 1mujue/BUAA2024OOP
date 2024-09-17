package enums;

/**
 * &#064;Classname PATH
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 14:19
 * &#064;Created MuJue
 */
public enum PATH {
    IN("./src/in.txt"),
    OUT("./src/out.txt"),
    DATA("./data/");
    private final String path;
    PATH(String path){
        this.path = path;
    }
    public String getPath(){
        return this.path;
    }
}
