package fun.android.federal_square.fun;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Fun_文件 {
    public static boolean 写入文件(String path, String data){
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            oStreamWriter.write(data);
            oStreamWriter.close();
            fos.close();
            return true;
        } catch (Exception e) {
            Log.w("写入文件", e);
            return false;
        }
    }

    public static String 读取文件(String path){
        StringBuilder sb = new StringBuilder();
        File urlFile = new File(path);
        if(!urlFile.exists()){
            return sb +"";
        }
        try {
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
        return  sb+"";
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

    /**
     * 复制文件
     *
     * @param context     上下文
     * @param fromUri     源文件Uri
     * @param toFilePath  目标文件路径
     */
    public static void copy_Uri_File(Context context, Uri fromUri, String toFilePath) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            InputStream inputStream = contentResolver.openInputStream(fromUri);
            OutputStream outputStream = new FileOutputStream(toFilePath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 复制文件
     *
     * @param context     上下文
     * @param fromUri     源文件Uri
     * @param toFilePath  目标文件路径
     */
    public static boolean copy_Uri_File(File fromUri, File toFilePath) {
        try {
            Files.copy(fromUri, toFilePath);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public static String 获取后缀(String fileName){
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
    }
}
