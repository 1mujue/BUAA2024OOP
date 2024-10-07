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
    private static final ScannerBuilder scannerBuilder = new ScannerBuilder();
    private ScannerBuilder(){;}
    public static ScannerBuilder getInstance(){
        return scannerBuilder;
    }
    private String path = null;
    public void setPath(String path){
        scannerBuilder.path = path;
    }

    public Scanner getScanner(){
        try {
            if(path == null){
                return new Scanner(System.in);
            }
            else{
                return new Scanner(new FileInputStream(path));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
