package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * &#064;Classname Outputer
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:02
 * &#064;Created MuJue
 */
public class Outputer {
    private static String path = null;
    private static FileOutputStream fos = null;
    private Outputer(){;}
    public static void setPath(String path){
        if(fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fos = null;
        }
        Outputer.path = path;
    }
    public static void PRINT(String content){
        if(fos == null){
            if(path != null){
                try {
                    fos = new FileOutputStream(new File(path));
                    fos.write(content.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                System.out.print(content);
            }
        }
        else{
            try {
                fos.write(content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
