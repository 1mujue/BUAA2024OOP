package commands;

import entity.tokens.Path;
import entity.tokens.Redirector;
import enums.PATH;
import exceptions.ExecutionException;
import exceptions.ValidationException;
import executors.FileExecutor;
import utils.Outputer;
import validators.ArgumentCountValidator;
import validators.FileValidator;
import validators.StateValidator;

import java.io.File;

/**
 * &#064;Classname OpenFileCommand
 * &#064;Description  TODO
 * &#064;Date 2024/9/7 12:03
 * &#064;Created MuJue
 */
public class OpenFileCommand extends BaseCommand{
    private Path filePath = null;
    private Redirector redirector= null;
    private Path redirectFilePath= null;
    private static final OpenFileCommand openFileCommand = new OpenFileCommand();
    private OpenFileCommand(){;}
    public static OpenFileCommand getInstance(){
        return openFileCommand;
    }
    @Override
    public void execute() throws ExecutionException {
        FileExecutor fileExecutor = FileExecutor.getInstance();
        String message = fileExecutor.openFile(filePath);
        Outputer outputer = Outputer.getInstance();
        if(count == 1 || count == 2){
            outputer.PRINT(message);
        } else if(count == 3){
            outputer.setPath(redirectFilePath.getValue());
            outputer.setMode(false);
            outputer.PRINT(message);
            outputer.setPath(PATH.OUT.getPath());
            outputer.setMode(true);
        }
        outputer.PRINT("Open file success\n");
    }

    @Override
    public void validate() throws ValidationException {
        ArgumentCountValidator argumentCountValidator = ArgumentCountValidator.getInstance();
        argumentCountValidator.legalityValidate(count, "openFile");

        StateValidator stateValidator = StateValidator.getInstance();
        stateValidator.onlineValidate();

        if(count == 1){
            filePath = new Path(parameters.get(0));
            oneArgsValidate();
        } else if(count == 2){
            filePath = new Path(parameters.get(0));
            redirector = new Redirector(parameters.get(1));
            twoArgsValidate();
        } else if(count == 3){
            filePath = new Path(parameters.get(0));
            redirector = new Redirector(parameters.get(1));
            redirectFilePath = new Path(parameters.get(2));
            threeArgsValidate();
        }
    }
    private void oneArgsValidate() throws ValidationException{
        FileValidator fileValidator = FileValidator.getInstance();
        fileValidator.tokenValidate(filePath);
        filePath.setValue(PATH.DATA.getPath() + filePath.getValue());
        fileValidator.isPathFileExists(filePath);
        fileValidator.isPathFileDirectory(filePath);
    }
    private void twoArgsValidate() throws ValidationException{
        FileValidator fileValidator = FileValidator.getInstance();
        fileValidator.tokenValidate(filePath);
        fileValidator.tokenValidate(redirector);
        filePath.setValue(PATH.DATA.getPath() + filePath.getValue());
        fileValidator.isPathFileExists(filePath);
        fileValidator.isPathFileDirectory(filePath);
    }
    private void threeArgsValidate() throws ValidationException{
        FileValidator fileValidator = FileValidator.getInstance();
        fileValidator.tokenValidate(filePath);
        fileValidator.tokenValidate(redirector);
        filePath.setValue(PATH.DATA.getPath() + filePath.getValue());
        fileValidator.isPathFileExists(filePath);
        fileValidator.isPathFileDirectory(filePath);
        redirectFilePath.setValue(PATH.DATA.getPath() + redirectFilePath.getValue());
    }
}
