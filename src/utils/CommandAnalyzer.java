package utils;

import commands.*;
import exceptions.ValidationException;
import manipulators.StateManipulator;
import entity.State;
import validators.CommandValidator;

import java.util.List;

/**
 * &#064;Classname CommandAnalyzer
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:13
 * &#064;Created MuJue
 */
public class CommandAnalyzer {
    private static final CommandAnalyzer commandAnalyzer = new CommandAnalyzer();
    public static CommandAnalyzer getInstance(){
        return commandAnalyzer;
    }
    private CommandAnalyzer(){;}
    public BaseCommand analyzeCommand(List<String> parameters){
        try {
            String commandName = parameters.get(0);
            BaseCommand command = new CommandValidator().getCommand(commandName);
            command.setParameters(parameters.subList(1, parameters.size()));
            State state = StateManipulator.getInstance().getState();
            state.validate(command);
            return command;
        } catch (ValidationException e) {
            Outputer.PRINT(e.getMessage());
        }
        return null;
    }
}
