package utils;

import java.io.File;
import java.io.IOException;

/**
 * &#064;Classname FileOperator
 * &#064;Description  TODO
 * &#064;Date 2024/9/14 22:47
 * &#064;Created MuJue
 */
public class FileOperator {
    private static final FileOperator fileOperator = new FileOperator();
    private FileOperator(){;}
    public static FileOperator getInstance(){
        return fileOperator;
    }
    public void createDirectory(String path){
        File file = new File(path);
        if(file.mkdirs()){
            //System.out.println("Make directory success!");
        }
    }
    public void deleteDirectory(String path){
        File file = new File(path);
        for(File file1 : file.listFiles()){
            if(file1.isDirectory()){
                deleteDirectory(file1.getPath());
            } else {
                if(file1.delete()){
                    //System.out.println("Delete " + file1.getPath() + " success");
                }
            }
        }
        if(file.delete()){
            //System.out.println("Delete " + file.getPath() + " success");
        }
    }
}
