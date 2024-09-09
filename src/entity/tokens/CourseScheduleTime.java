package entity.tokens;

import exceptions.ValidationException;
import utils.TokenHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname CourseScheduleTime
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:37
 * &#064;Created MuJue
 */
public class CourseScheduleTime extends Token<String>{
    private int weekTime = -1;
    private int fromTime = -1;
    private int toTime = -1;
    public CourseScheduleTime(String scheduleTime){
        this.value = scheduleTime;
    }
    public void init(){
        TokenHandler tokenHandler = TokenHandler.getInstance();
        tokenHandler.setDividend("-_");
        List<Integer> times = tokenHandler.getInteger(value);
        weekTime = times.get(0);
        fromTime = times.get(1);
        toTime = times.get(2);
    }
    public int getWeekTime() {
        if (weekTime == -1) {
            init();
        }
        return weekTime;
    }

    public int getFromTime() {
        if(fromTime == -1){
            init();
        }
        return fromTime;
    }

    public int getToTime() {
        if(toTime == -1){
            init();
        }
        return toTime;
    }

    @Override
    public void validate() throws ValidationException {
        int length = value.length();
        List<Integer> times = new ArrayList<>();
        int number = 0;
        boolean isNumberExist = false;
        boolean isLegal = true;
        for(int i = 0;i < length;++i){
            char ch = value.charAt(i);
            if(isNumber(ch)){
                if(!isNumberExist){
                    isNumberExist = true;
                }
                number = number * 10 + ch - '0';
            }
            else if((ch == '_' || ch == '-') && isNumberExist){
                isNumberExist =false;
                times.add(number);
                number = 0;
            }
            else {
                isLegal = false;
                break;
            }
            if(i == length - 1 && isNumberExist){
                isNumberExist =false;
                times.add(number);
                number = 0;
            }
        }
        if(times.size() != 3){
            isLegal = false;
        }
        else{
            if(!isLegalWeekTime(times.get(0))){
                isLegal = false;
            }
            if(!isLegalDayTime(times.get(1)) || !isLegalDayTime(times.get(2))){
                isLegal = false;
            }
            if(!isLegalClassTime(times.get(1), times.get(2))){
                isLegal = false;
            }
        }

        if(!isLegal){
            throw new ValidationException("Illegal course time\n");
        }
    }
    private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }
    private boolean isLegalWeekTime(int time){
        return time >= 1 && time <= 7;
    }
    private boolean isLegalDayTime(int time){
        return time >= 1 && time <= 14;
    }
    private boolean isLegalClassTime(int t1, int t2){
        return t1 <= t2;
    }
}
