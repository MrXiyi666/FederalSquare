package fun.android.federal_square.fun;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public static List<String> 遍历所有图片不带域名(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        Comparator<String> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        return list;
    }

    public static List<String> 遍历所有图片带域名(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        Comparator<String> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        List<String> return_url = new ArrayList<>();
        for(String name : list){
            return_url.add(able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + name);
        }
        return return_url;
    }
}
