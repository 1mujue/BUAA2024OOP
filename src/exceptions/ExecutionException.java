package exceptions;

/**
 * &#064;Classname ExecutionException
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 10:34
 * &#064;Created MuJue
 */
public class ExecutionException extends Exception{
    private static final String message = "execution exception";
    public ExecutionException(String message){
        super(message);
    }
    public ExecutionException(){
        super(ExecutionException.message);
    }
}
