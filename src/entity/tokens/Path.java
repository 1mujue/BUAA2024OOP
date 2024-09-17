package entity.tokens;

import enums.PATH;
import exceptions.ValidationException;

/**
 * &#064;Classname Path
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 16:04
 * &#064;Created MuJue
 */
public class Path extends Token<String>{
    public Path(String path){
        this.value = path;
    }
    @Override
    public void validate() throws ValidationException {
        if(value.equals(">")){
            throw new ValidationException("Illegal file path\n");
        }
    }
}
