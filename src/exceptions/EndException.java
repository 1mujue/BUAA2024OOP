package exceptions;

/**
 * &#064;Classname EndException
 * &#064;Description  TODO
 * &#064;Date 2024/10/7 21:11
 * &#064;Created MuJue
 */
public class EndException extends Exception{
    private static final String message = "end";
    public EndException(String message){super(message);}
    public EndException(){ super(message);}
}
