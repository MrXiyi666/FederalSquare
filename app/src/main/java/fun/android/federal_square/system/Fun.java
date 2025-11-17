package fun.android.federal_square.system;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.widget.AppCompatButton;

public class Fun {
    /**
     * 从 URL 中提取文件名
     * @param url 下载链接
     * @return 提取的文件名（默认返回 "downloadfile"）
     */
    public static String getFileNameFromUrl(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        // 去掉 URL 中的参数（如 ?token=xxx）
        url = url.split("\\?")[0];
        // 从最后一个 "/" 后面截取字符串作为文件名
        int lastSlashIndex = url.lastIndexOf("/");
        if (lastSlashIndex != -1 && lastSlashIndex < url.length() - 1) {
            return url.substring(lastSlashIndex + 1);
        }
        // 如果没有找到有效文件名，返回默认值
        return null;
    }

    public static int 屏幕宽度(Context activity){
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.widthPixels;
    }

    public static int 屏幕高度(Context activity){
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int 获取状态栏高度(Context activity){
        int result=0;
        @SuppressLint({"InternalInsetResource", "DiscouragedApi"})
        int resourceld = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceld > 0){
            result = activity.getResources().getDimensionPixelSize(resourceld);
        }
        return result;
    }

    /**
     * 将 px 值转换为 dp 值
     * @param context 上下文（用于获取屏幕密度）
     * @param pxValue 需要转换的 px 值
     * @return 转换后的 dp 值（float 类型，保留精度）
     */
    public static float PXToDP(Context context, float pxValue) {
        // 获取设备的屏幕密度（density = dpi / 160）
        float density = context.getResources().getDisplayMetrics().density;
        // 计算 dp 值（px / density）
        return pxValue / density;
    }

    /**
     * 将 dp 值转换为 px（像素）
     * @param context 上下文（用于获取屏幕密度）
     * @param dpValue 需要转换的 dp 值
     * @return 转换后的 px 值（int 类型，通常用于设置 View 尺寸）
     */
    public static int DPToPX(Context context, float dpValue) {
        // 获取设备的屏幕密度信息
        float density = context.getResources().getDisplayMetrics().density;
        // 计算 px 值（四舍五入取整）
        return (int) (dpValue * density + 0.5f);
    }

}