package dev.jcsoftware.utils;

import java.io.*;
import java.util.*;

public class FileUtil {

    public static void copy(File source, File destination) throws IOException {
        if(source.isDirectory()) {
            if(!destination.exists()) {
                destination.mkdir();
            }

            String[] files = source.list();
            if(files == null) return;

            for(String file : files) {
                File newSource = new File(source, file);
                File newDistination = new File(destination, file);
                copy(newSource, newDistination);
            }
        }else{
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

    public static void delete(File file) {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null) return;

            Arrays.stream(files).forEach(FileUtil::delete);
        }

        file.delete();
    }

}
