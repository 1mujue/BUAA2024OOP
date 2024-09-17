package utils;

import commands.BaseCommand;
import exceptions.ExecutionException;

/**
 * &#064;Classname CommandExecutor
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:24
 * &#064;Created MuJue
 */
public class CommandExecutor {
    private static final CommandExecutor commandExecutor = new CommandExecutor();
    private static final Outputer outputer = Outputer.getInstance();
    public static CommandExecutor getInstance(){
        return commandExecutor;
    }
    private CommandExecutor(){;}
    public  void executeCommand(BaseCommand command){
        try {
            if(command != null) {
                command.execute();
            }
        } catch (ExecutionException e) {
            outputer.PRINT(e.getMessage());
        }
    }
}
