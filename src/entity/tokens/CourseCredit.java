package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname CourseCredit
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:38
 * &#064;Created MuJue
 */
public class CourseCredit extends Token<String>{
    private double credit = -1;
    public CourseCredit(String credit){
        this.value = credit;
    }
    @Override
    public void validate() throws ValidationException {
        try {
            credit = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("Illegal course credit\n");
        }
        if(credit <= 0 || credit > 12){
            throw new ValidationException("Illegal course credit\n");
        }
    }

    public double getCredit() {
        if(credit == -1){
            credit = Double.parseDouble(value);
        }
        return credit;
    }
}
