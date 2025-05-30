package fun.android.federal_square.data;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.view.View_Home;
import fun.android.federal_square.view.View_Hot;
import fun.android.federal_square.view.View_Main;
import fun.android.federal_square.view.View_Square;
import okhttp3.OkHttpClient;

public class able {
    //=====================================颜色数据======================================
    public static int 描边颜色 = Color.argb(128,128,128,128);
    public static View_Main view_main;
    public static TextView 广场空, 头条空;
    public static int pager_id=0;
    public static Handler handler = new Handler();
    public static String app_path;
    public static String URL ="";
    public static String PassWord ="";
    public static Gson gson = new Gson();
    public static OkHttpClient okHttpClient;
    @SuppressLint("StaticFieldLeak")
    public static View_Square view_square;
    @SuppressLint("StaticFieldLeak")
    public static View_Hot view_hot;
    @SuppressLint("StaticFieldLeak")
    public static View_Home view_home;
    public static List<Post_Data> 传递数据;
    public static RequestOptions requestOptions = new RequestOptions()
            .frame(0)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);
    public static RequestOptions 原图_request = new RequestOptions()
            .frame(0)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .skipMemoryCache(true)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);
}