package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * &#064;Classname TokenHandler
 * &#064;Description  TODO
 * &#064;Date 2024/9/8 10:56
 * &#064;Created MuJue
 */
public class TokenHandler {
    private String dividend;
    private static final TokenHandler tokenHandler = new TokenHandler();
    private TokenHandler(){;}
    public static TokenHandler getInstance(){
        return tokenHandler;
    }
    public void setDividend(String dividend){
        this.dividend = dividend;
    }
    public List<Integer> getInteger(String rawString){
        List<Integer> ans = new ArrayList<>();
        int currentNumber = 0;
        int length = rawString.length();
        boolean isNumberExist = false;
        for(int i = 0;i < length;++i){
            char ch = rawString.charAt(i);
            if(isNumber(ch)){
                if(!isNumberExist){
                    isNumberExist = true;
                }
                currentNumber = currentNumber * 10 + ch - '0';
            }
            if(isNumberExist && (isDividend(ch) || i == length - 1)){
                ans.add(currentNumber);
                currentNumber = 0;
            }
        }
        return ans;
    }
    public boolean isNumber(char ch){
        return ch >= '0' && ch <='9';
    }
    public boolean isDividend(char ch){
        int length = dividend.length();
        for(int i = 0;i < length;++i){
            if (ch == dividend.charAt(i)) {
                return true;
            }
        }
        return false;
    }

}
