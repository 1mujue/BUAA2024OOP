package entity.tokens;

import exceptions.ValidationException;

/**
 * &#064;Classname Password
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:24
 * &#064;Created MuJue
 */
public class Password extends Token<String>{
    public Password(String password){
        this.value = password;
    }
    @Override
    public void validate() throws ValidationException {
        int length = value.length();
        if(length < 6 || length > 16){
            throw new ValidationException("Illegal password\n");
        }
        boolean isSpecial = false, isAlphabet = false, isNumber = false, isLegal = true;
        for(int i = 0;i < length;++i){
            char ch = value.charAt(i);
            if((ch == '@' || ch == '$' || ch == '_' || ch == '%')){
                isSpecial = true;
            }
            else if((ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')){
                isAlphabet = true;
            }
            else if((ch >= '0' && ch <= '9')){
                isNumber = true;
            }
            else {
                isLegal = false;
                break;
            }
        }
        if(!isLegal || !(isSpecial && isAlphabet && isNumber)){
            throw new ValidationException("Illegal password\n");
        }
    }
}
