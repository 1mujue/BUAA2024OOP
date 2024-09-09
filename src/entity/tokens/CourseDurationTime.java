package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname CourseDurationTime
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:38
 * &#064;Created MuJue
 */
public class CourseDurationTime extends Token<Integer>{
    public CourseDurationTime(Integer durationTime){
        this.value = durationTime;
    }
    @Override
    public void validate() throws ValidationException {
        if(value <= 0 || value > 1280){
            throw new ValidationException("Illegal course period\n");
        }
    }
}
