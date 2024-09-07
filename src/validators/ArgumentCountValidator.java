package validators;

import enums.REQUIRED_COUNT;
import exceptions.ValidationException;

/**
 * &#064;Classname ArgumentCountValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:40
 * &#064;Created MuJue
 */
public class ArgumentCountValidator {
    private static final ArgumentCountValidator argumentCountValidator = new ArgumentCountValidator();
    private ArgumentCountValidator(){;}
    public static ArgumentCountValidator getInstance(){
        return argumentCountValidator;
    }
    public void legalityValidate(int currentCount, REQUIRED_COUNT targetCount) throws ValidationException {
        if(currentCount < targetCount.getLowLimit() || currentCount > targetCount.getHighLimit()){
            throw  new ValidationException("Illegal argument count\n");
        }
    }
}
