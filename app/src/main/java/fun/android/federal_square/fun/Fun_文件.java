package fun.android.federal_square.fun;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Fun_文件 {
    List<File> files = new ArrayList<>();
    public static boolean 写入文件(String path, String data){
        File file = new File( path);
        try {
            if (file.exists()) {
                if(!file.delete()){
                    return false;
                }
            }
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            oStreamWriter.append(data);
            oStreamWriter.flush();
            oStreamWriter.close();
            return true;
        } catch (Exception e) {
            Log.w("写入文件", e);
            return false;
        }
    }

    public static String 读取文件(String path){
        StringBuilder sb = new StringBuilder();
        try {
            File urlFile = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            Log.w("读取文件", e);
        }
        return  sb.toString();
    }


    public static boolean 创建文件夹(String path){
        return new File(path).mkdirs();
    }

    public static boolean 删除文件(String path){
        return new File(path).delete();
    }

    public static boolean 删除文件夹(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] files = file.listFiles();
        if(files !=null){
            for (File f : files) {
                if (f.isDirectory()) {
                    删除文件夹(f);
                }
                f.delete();
            }
            file.delete();
        }
        if (file.exists()) {
            return false;
        }else{
            return true;
        }
    }



    public static boolean 是否存在(String path){
        return new File(path).exists();
    }

    public static List<String> 遍历文件夹(String path){
        File file = new File(path);
        File[] fs = file.listFiles();
        List<String> list = new ArrayList<>();
        if(fs == null){
            return list;
        }
        for(File f : fs){
            list.add(f.getName());
        }

        return list;
    }

}
