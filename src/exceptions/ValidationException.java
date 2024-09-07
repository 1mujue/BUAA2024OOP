package exceptions;

/**
 * &#064;Classname ValidationException
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 10:30
 * &#064;Created MuJue
 */
public class ValidationException extends Exception{
    private static final String message = "validation exception";
    public ValidationException(String message){
        super(message);
    }
    public ValidationException(){
        super(ValidationException.message);
    }
}
