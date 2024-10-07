import commands.BaseCommand;
import enums.PATH;
import exceptions.EndException;
import exceptions.ExecutionException;
import utils.*;

import java.io.File;
import java.util.*;

/**
 * &#064;Classname Test
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 16:55
 * &#064;Created MuJue
 */
public class Test {
    private static Scanner scanner;
    private static final ScannerBuilder scannerBuilder = ScannerBuilder.getInstance();
    private static final Outputer outputer = Outputer.getInstance();
    private static final FileOperator fileOperator = FileOperator.getInstance();
    private static final CommandAnalyzer commandAnalyzer = CommandAnalyzer.getInstance();
    private static final CommandExecutor commandExecutor = CommandExecutor.getInstance();
    private static final List<String> parameters = new ArrayList<>();
    private static void init(){
        scannerBuilder.setPath(PATH.IN.getPath());
        outputer.setPath(PATH.OUT.getPath());
        outputer.setMode(false);
        scanner = scannerBuilder.getScanner();
    }
    public static void main(String[] args){
        init();
        fileOperator.createDirectory(PATH.DATA.getPath());

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tmp = line.split("\\s+");
            if(!Objects.equals(tmp[0], "")){
                parameters.clear();
                parameters.addAll(Arrays.asList(tmp));
                BaseCommand command = commandAnalyzer.analyzeCommand(parameters);
                try{
                    commandExecutor.executeCommand(command);
                }catch (EndException e){
                    break;
                }
                if(command != null){
                    command.clean();
                }
            }
        }

        fileOperator.deleteDirectory(PATH.DATA.getPath());
    }
}
