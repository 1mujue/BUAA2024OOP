package validators;

import entity.tokens.Path;
import entity.tokens.Token;
import exceptions.ValidationException;

import java.io.File;

/**
 * &#064;Classname FileValidator
 * &#064;Description  TODO
 * &#064;Date 2024/9/14 22:23
 * &#064;Created MuJue
 */
public class FileValidator extends TokenValidator {
    private static final FileValidator fileValidator = new FileValidator();
    private FileValidator(){;}
    public static FileValidator getInstance(){
        return fileValidator;
    }
    public void isPathFileExists(Path path) throws ValidationException {
        File file = new File(path.getValue());
        if(!file.exists()){
            throw new ValidationException("File does not exist\n");
        }
    }
    public void isPathFileDirectory(Path path) throws ValidationException{
        File file = new File(path.getValue());
        if(file.isDirectory()){
            throw new ValidationException("File is a directory\n");
        }
    }
}
