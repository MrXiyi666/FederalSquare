package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fun.android.federal_square.data.able;

public class Fun_图片 {

    public static Bitmap 图片底部写文字(Activity activity, Bitmap bitmap){
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        TextView tt = new TextView(activity);
        tt.setTextSize(15);
        paint.setTextSize(tt.getTextSize());

        paint.setColor(Color.WHITE);
        canvas.drawText("视频文件", 0, paint.getTextSize(), paint);


        return bitmap;
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
