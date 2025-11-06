package fun.android.federal_square.system;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public static boolean 删除文件夹(File folder) {
        if (folder == null || !folder.exists()) {
            return false;
        }
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 递归删除子文件夹中的内容
                    if (file.isDirectory()) {
                        删除文件夹(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }

        // 删除当前文件夹
        return folder.delete();
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
        int index=0;
        for(int i=fs.length-1; i>=0; i--){
            if(index >= 100000){
                break;
            }
            list.add(fs[i].getName());
            index++;
        }
        return list;
    }

    /**
     * 复制文件（优化版）
     *
     * @param context     上下文
     * @param fromUri     源文件Uri
     * @param toFilePath  目标文件路径
     */
    public static void copy_Uri_File(@NonNull Context context, @NonNull Uri fromUri, @NonNull String toFilePath) {
        final int BUFFER_SIZE = 8 * 1024; // 8KB缓冲区
        ContentResolver contentResolver = context.getContentResolver();

        File targetFile = new File(toFilePath);
        // 确保目标目录存在
        File parentDir = targetFile.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
           return;
        }

        try (InputStream input = contentResolver.openInputStream(fromUri);
             OutputStream output = new FileOutputStream(targetFile)) {
            if (input == null) {
                throw new FileNotFoundException("未找到源文件: " + fromUri);
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.flush();
        } catch (IOException e) {
            // 删除不完整的文件
            if (targetFile.exists() && !targetFile.delete()) {
                Log.w("文件复制", "无法删除未完成的文件: " + targetFile);
            }
        }
    }
    /**
     * 复制文件
     *
     * @param fromUri     源文件Uri
     * @param toFilePath  目标文件路径
     */
    public static boolean copy_Uri_File(File fromUri, File toFilePath) {
        try {
            //Files.copy(fromUri, toFilePath);
            //return true;
        }catch (Exception e){
           // return false;
        }
        return false;
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
