package fun.android.federal_square.data;

import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.view.View_Home;
import fun.android.federal_square.view.View_Hot;
import fun.android.federal_square.view.View_Main;
import fun.android.federal_square.view.View_Square;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class able {
    public static int pager_id=0;
    public static Handler handler = new Handler();
    public static int 宽度, 高度, 状态栏高度;
    public static String app_path;

    public static String URL ="";

    public static String PassWord ="";
    public static Gson gson = new Gson();

    public static OkHttpClient okHttpClient;

    public static View_Square view_square;
    public static View_Hot view_hot;
    public static View_Home view_home;

    public static List<Post_Data> 传递数据;

    public static RequestOptions requestOptions = new RequestOptions()
            .placeholder(R.drawable.glide_zhanwei)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);

    public static RequestOptions requestOptions_yasuo = new RequestOptions()
            .encodeQuality(50);

}
