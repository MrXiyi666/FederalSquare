package fun.android.federal_square.fun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.widget.Toast;

import androidx.documentfile.provider.DocumentFile;

import java.util.Calendar;
import java.util.Date;

import fun.android.federal_square.data.able;

public class Fun {
    public static void mess(Activity activity, String name){
        able.handler.post(()->{
            Toast.makeText(activity, name, Toast.LENGTH_SHORT).show();
        });
    }

    public static String 获取时间() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        Date date = new Date(System.currentTimeMillis());
        String formattedDate = simpleDateFormat.format(date);
        long nanoTime = System.nanoTime();
        long microTime = nanoTime / 1000;
        return formattedDate + "_" + microTime;
    }
    /*
     * Java文件操作 获取文件扩展名
     * */
    public static String 获取文件扩展名(String filename) {
        if ((filename != null) && (!filename.isEmpty())) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    //获取uri文件名称
    public static String 获取Uri文件名(Activity context, Uri fileUri) {
        if (context == null || fileUri == null) return null;
        DocumentFile documentFile = DocumentFile.fromSingleUri(context, fileUri);
        if (documentFile == null) return null;
        return documentFile.getName();
    }

    public static int 获取Uri文件大小(Activity context, Uri fileUri){
        if(fileUri == null){
            return 0;
        }
        DocumentFile documentFile = DocumentFile.fromSingleUri(context, fileUri);
        if (documentFile == null){
            return 0;
        }
        return (int)documentFile.length();
    }


    public static int 获取状态栏高度(Activity activity){
        int result=0;
        @SuppressLint("InternalInsetResource") 
        int resourceld = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceld > 0){
            result = activity.getResources().getDimensionPixelSize(resourceld);
        }
        return result;
    }
}
