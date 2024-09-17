package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname Redirector
 * &#064;Description  TODO
 * &#064;Date 2024/9/16 16:05
 * &#064;Created MuJue
 */
public class Redirector extends Token<String> {
    public Redirector(String redirector){
        this.value = redirector;
    }
    @Override
    public void validate() throws ValidationException {
        if(!value.equals(">")){
            throw new ValidationException("Illegal redirector\n");
        }
    }
}
