package utils;

import java.io.*;

/**
 * &#064;Classname Outputer
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 17:02
 * &#064;Created MuJue
 */
public class Outputer {
    private String path = null;
    private boolean mode = false;
    private FileOutputStream fos = null;
    private static final Outputer outputer = new Outputer();
    private Outputer(){;}
    public static Outputer getInstance(){
        return outputer;
    }
    public void setPath(String path){
        if(fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fos = null;
        }
        this.path = path;
    }
    public void setMode(Boolean mode){
        this.mode = mode;
    }
    public void PRINT(String content){
        if(fos == null){
            if(path != null){
                try {
                    File file = new File(path);
                    if(!file.exists()){
                        if(!file.createNewFile()){
                            throw new IOException();
                        }
                    }
                    fos = new FileOutputStream(path, mode);
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
