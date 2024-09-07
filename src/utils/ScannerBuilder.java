package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * &#064;Classname ScannerBuilder
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 16:57
 * &#064;Created MuJue
 */
public class ScannerBuilder {
    private static String path = null;
    private static Scanner scanner = null;
    public static void setPath(String path){
        if(scanner != null){
            scanner.close();
            scanner = null;
        }
        ScannerBuilder.path = path;
    }
    private ScannerBuilder(){;}
    public static Scanner getScanner(){
        if(scanner == null){
            if(path == null){
                scanner = new Scanner(System.in);
            }
            else{
                try {
                    scanner = new Scanner(new FileInputStream(new File(path)));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return scanner;
    }
}
