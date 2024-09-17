package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname CourseDurationTime
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:38
 * &#064;Created MuJue
 */
public class CourseDurationTime extends Token<String>{
    private int courseDurationTime = -1;
    public CourseDurationTime(String durationTime){
        this.value = durationTime;
    }
    @Override
    public void validate() throws ValidationException {
        try {
            courseDurationTime = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("Illegal course period\n");
        }
        if(courseDurationTime <= 0 || courseDurationTime > 1280){
            throw new ValidationException("Illegal course period\n");
        }
    }

    public int getCourseDurationTime() {
        if(courseDurationTime == -1){
            courseDurationTime = Integer.parseInt(value);
        }
        return courseDurationTime;
    }
}
