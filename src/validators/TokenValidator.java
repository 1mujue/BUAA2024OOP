package validators;

import entity.tokens.Token;
import exceptions.ValidationException;

/**
 * &#064;Classname TokenValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/11 11:18
 * &#064;Created MuJue
 */
public abstract class TokenValidator {
    public void tokenValidate(Token token) throws ValidationException {
        token.validate();
    }
}
