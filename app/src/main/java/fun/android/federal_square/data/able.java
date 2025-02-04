package fun.android.federal_square.data;

import android.annotation.SuppressLint;
import android.os.Handler;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.view.View_Home;
import fun.android.federal_square.view.View_Hot;
import fun.android.federal_square.view.View_Square;
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
    @SuppressLint("StaticFieldLeak")
    public static View_Square view_square;
    @SuppressLint("StaticFieldLeak")
    public static View_Hot view_hot;
    @SuppressLint("StaticFieldLeak")
    public static View_Home view_home;
    public static List<Post_Data> 传递数据;
    public static RequestOptions requestOptions = new RequestOptions()
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);
    public static RequestOptions 占位_request = new RequestOptions()
            .placeholder(R.drawable.glide_zhanwei)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);
    public static RequestOptions 原图_request = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .skipMemoryCache(true)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);
}
