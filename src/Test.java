import commands.BaseCommand;
import utils.CommandAnalyzer;
import utils.CommandExecutor;
import utils.Outputer;
import utils.ScannerBuilder;

import java.util.*;

/**
 * &#064;Classname Test
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 16:55
 * &#064;Created MuJue
 */
public class Test {
    private static Scanner scanner;
    private static CommandAnalyzer commandAnalyzer;
    private static CommandExecutor commandExecutor;
    private static List<String> parameters = new ArrayList<>();
    private static void init(){
        ScannerBuilder.setPath("./src/in.txt");
        Outputer.setPath("./src/out.txt");
        scanner = ScannerBuilder.getScanner();
        commandAnalyzer = CommandAnalyzer.getInstance();
        commandExecutor = CommandExecutor.getInstance();
    }
    public static void main(String[] args){
        init();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tmp = line.split("\\s+");
            if(!Objects.equals(tmp[0], "")){
                parameters.clear();
                parameters.addAll(Arrays.asList(tmp));
                BaseCommand command = commandAnalyzer.analyzeCommand(parameters);
                commandExecutor.executeCommand(command);
            }
        }
    }
}
