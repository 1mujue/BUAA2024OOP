package entity.tokens;

import exceptions.ValidationException;
import utils.TokenHandler;

/**
 * &#064;Classname CourseId
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:30
 * &#064;Created MuJue
 */
public class CourseId extends Token<String>{
    private int cid = -1;
    public CourseId(String courseId){
        this.value = courseId;
    }
    public int getCourseId(){
        if(cid == -1){
            TokenHandler tokenHandler = TokenHandler.getInstance();
            tokenHandler.setDividend("-");
            cid =  tokenHandler.getInteger(value).getFirst();
        }
        return cid;
    }
    @Override
    public void validate() throws ValidationException {
        int length = value.length();
        if(length < 3){
            throw new ValidationException("Illegal course id\n");
        }
        if(value.charAt(0) != 'C' || value.charAt(1) != '-'){
            throw new ValidationException("Illegal course id\n");
        }
        for(int i = 2;i < length;++i){
            char ch = value.charAt(i);
            if(!isNumber(ch) || (i == 2 && ch == '0')){
                throw new ValidationException("Illegal course id\n");
            }
        }
    }
    private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }
}
