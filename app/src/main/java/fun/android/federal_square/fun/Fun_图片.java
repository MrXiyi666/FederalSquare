package fun.android.federal_square.fun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

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
        List<String> return_list = new ArrayList<>();
        int id = 0;
        String Str_index = Fun_文件.读取文件(able.app_path + "System_Data/Disk_Select.txt");
        if(!Str_index.isEmpty()){
            id = Integer.parseInt(Str_index);
        }
        Log.w("网盘编号", id+"");
        for(String name : list){
            String 后缀 = Fun.获取文件扩展名(name);
            if(id == 1){
                if(Fun.图片格式判断(后缀)){
                    return_list.add(name);
                }
                continue;
            }
            if(id == 2){
                if(Fun.视频格式判断(后缀)){
                    return_list.add(name);
                }
                continue;
            }
            return_list.add(name);
        }
        return return_list;
    }

    public static Bitmap 加载图片(String path){
        return BitmapFactory.decodeFile(new File(path).getAbsolutePath());

    }
}
