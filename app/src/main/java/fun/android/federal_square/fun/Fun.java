package fun.android.federal_square.fun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.documentfile.provider.DocumentFile;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.URL_PassWord_Data;
import fun.android.federal_square.data.able;

public class Fun {

    public static void mess(Activity activity, String name){

        able.handler.post(()->{
            AlertDialog dialog = new AlertDialog.Builder(activity,  R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            ScrollView scrollView = view.findViewById(R.id.scrollView);
            return_icon.setVisibility(View.GONE);
            view.post(()->{
                if(text_id.getHeight() > able.高度){
                    scrollView.getLayoutParams().height = able.高度 / 2;
                    scrollView.requestLayout();
                }
            });
            text_id.setText(name);

            return_icon.setOnClickListener(V->{
                dialog.dismiss();
            });
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.show();
            new Thread(()->{
                try {
                    Thread.sleep(500);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        });
    }

    public static void mess(Activity activity, String name, int time){

        able.handler.post(()->{
            AlertDialog dialog = new AlertDialog.Builder(activity,  R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            ScrollView scrollView = view.findViewById(R.id.scrollView);
            return_icon.setVisibility(View.GONE);
            view.post(()->{
                if(text_id.getHeight() > able.高度){
                    scrollView.getLayoutParams().height = able.高度 / 2;
                    scrollView.requestLayout();
                }
            });
            text_id.setText(name);

            return_icon.setOnClickListener(V->{
                dialog.dismiss();
            });
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.show();
            new Thread(()->{
                try {
                    Thread.sleep(time);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        });
    }

    public static String 获取时间() {
        int Random_Data = 0;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        Date date = new Date(System.currentTimeMillis());
        String formattedDate = simpleDateFormat.format(date);
        Random random = new Random();
        while(true) {
            int index = random.nextInt(999999999);
            if(Random_Data != index) {
                Random_Data = index;
                break;
            }
        }
        String str = formattedDate + "_" + Random_Data;
        if(str.length() > 50){
            return str.substring(0, 50);
        }
        return str;
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
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(fileUri);
            if(inputStream ==null){
                return 0;
            }
            int fileSize = inputStream.available();
            inputStream.close();
            return fileSize;
        }catch (Exception e){
            return 0;
        }
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

    public static int PXToDP(Activity activity, int px){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return (int)(px / density);
    }

    public static int DPToPX(Activity activity, int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return (int)(dp * density);
    }

    public static boolean StrBoolJSON(String str){
        if(str == null){
            return false;
        }
        if(str.isEmpty()){
            return false;
        }
        boolean for_bool = false;
        try {
            List<Post_Data> pd = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            for_bool = true;
        }catch (JsonSyntaxException ignored){

        }
        if(!for_bool){
            try {
                List<URL_PassWord_Data> pd = able.gson.fromJson(str, new TypeToken<List<URL_PassWord_Data>>(){}.getType());
                for_bool = true;
            }catch (JsonSyntaxException ignored){
            }
        }

        return for_bool;
    }

    public static boolean 视频格式判断(String name){
        if(name.equals("mp4") | name.equals("3gp") | name.equals("mov") | name.equals("avi") | name.equals("mkv") | name.equals("flv") | name.equals("webm")){
            return true;
        }
        return false;
    }

    public static boolean 图片格式判断(String name){
        if(name.equals("jpg") | name.equals("jpeg") | name.equals("png") | name.equals("webp")){
            return true;
        }
        return false;
    }
}
