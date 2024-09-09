package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname CourseCredit
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:38
 * &#064;Created MuJue
 */
public class CourseCredit extends Token<Double>{
    public CourseCredit(Double credit){
        this.value = credit;
    }
    @Override
    public void validate() throws ValidationException {
        if(value <= 0 || value > 12){
            throw new ValidationException("Illegal course credit\n");
        }
    }
}
