package fun.android.federal_square.data;

import android.os.Handler;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import fun.android.federal_square.view.View_Home;
import fun.android.federal_square.view.View_Hot;
import fun.android.federal_square.view.View_Main;
import fun.android.federal_square.view.View_Square;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class able {
    public static Handler handler = new Handler();
    public static int 宽度, 高度, 状态栏高度;
    public static String app_path;
    public static int square_time_index = 5000; //广场五秒钟刷新一次
    public static String URL_Name="", Read_PassWord="";
    public static Gson gson = new Gson();

    public static OkHttpClient okHttpClient;

    public static View_Square view_square;
    public static View_Hot view_hot;
    public static View_Home view_home;

    public static List<Post_Data> 传递数据;

}
