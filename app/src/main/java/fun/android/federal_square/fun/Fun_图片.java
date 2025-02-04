package fun.android.federal_square.fun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.data.able;

public class Fun_图片 {
    public static List<String> 遍历所有图片(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        list.sort((o1, o2) -> {
            String time1 = o1.substring(0, o1.lastIndexOf('_'));
            String time2 = o2.substring(0, o2.lastIndexOf('_'));
            return time2.compareTo(time1);
        });
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        return list;
    }

    public static Bitmap 加载图片(String path){
        return BitmapFactory.decodeFile(new File(path).getAbsolutePath());

    }
}
