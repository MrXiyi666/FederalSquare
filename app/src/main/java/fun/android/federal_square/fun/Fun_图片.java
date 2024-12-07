package fun.android.federal_square.fun;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;

import fun.android.federal_square.data.able;

public class Fun_图片 {
    public static String 保存缓存图片(Bitmap bitmap, String 后缀){
        String path="";
        try {
            String sdCardDir = able.app_path + "cache";
            new File(sdCardDir).mkdirs();
            File file;
            FileOutputStream fos;
            switch (后缀){
                case "png":
                    path = able.app_path + "/cache/cache.png";
                    file = new File(sdCardDir,  "cache.png");
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                    fos.flush();
                    fos.close();
                    break;
                case "jpg":
                    path = able.app_path + "/cache/cache.jpg";
                    file = new File(sdCardDir,  "cache.jpg");
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                    fos.flush();
                    fos.close();
                    break;
                case "jpeg":
                    path = able.app_path + "/cache/cache.jpeg";
                    file = new File(sdCardDir,  "cache.jpeg");
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                    fos.flush();
                    fos.close();
                    break;
                case "webp":
                    path = able.app_path + "/cache/cache.webp";
                    file = new File(sdCardDir,  "cache.webp");
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 50, fos);
                    fos.flush();
                    fos.close();
                    break;
                default:
            }
        }catch (Exception e){
            Log.w("保存图片", e);
        }
        return path;
    }
}
