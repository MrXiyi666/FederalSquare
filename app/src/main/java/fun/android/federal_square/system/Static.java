package fun.android.federal_square.system;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import fun.android.federal_square.view.Popular_View;
import fun.android.federal_square.view.TimeLine_View;
import fun.android.federal_square.view.View_Main;
import okhttp3.OkHttpClient;

public class Static {
    public static String app_path="";
    public static String url="http://mrxiyi.top/federal-square-7-1/";
    public static String Example_Name="federal-square-7-1";

    public static OkHttpClient okHttpClient;


    public static ScrollView main;
    public static View_Main view_main;
    public static TimeLine_View timeLine_view ;
    public static Popular_View popular_view;
    public static int timeLine_view_y=0, popular_view_y=0;


    //主题颜色 文字颜色: 背景颜色: 前景颜色: 按钮按下颜色: 默认颜色: 控件描边颜色:

    public static String[] 极简灰色 = {"#111111", "#FAFAFA", "#F0F0F0", "#CCCCCC", "#7F8C8D", "#E0E0E0"};
    public static String[] 活力橙色 = {"#2E2E2E", "#FFF8F0", "#FFFFFF", "#F8C471", "#E67E22", "#FDEDEC"};
    public static String[] 柔和蓝色 = {"#1B4F72", "#EBF5FB", "#FFFFFF", "#85C1E9", "#2980B9", "#D6EAF8"};
    public static String[] 现代深色 = {"#ECF0F1", "#1A1A1A", "#2C2C2C", "#34495E", "#1ABC9C", "#3A3A3A"};
    public static String[] 经典浅色 = {"#2C3E50", "#F5F7FA", "#FFFFFF", "#BDC3C7", "#3498DB", "#E0E6ED"};

    public static void create(Context context){

    }
}
