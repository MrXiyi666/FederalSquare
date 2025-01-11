package fun.android.federal_square;

import android.app.Application;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import java.io.File;
import java.util.concurrent.TimeUnit;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class App extends Application {
    private static SimpleCache simpleCache;
    @Override
    public void onCreate() {
        super.onCreate();
        able.app_path = this.getExternalFilesDir("").getPath() + "/";
        //able.app_path = Objects.requireNonNull(this.getFilesDir()).getPath() + "/";
        Fun_文件.创建文件夹(able.app_path + "image_cache");
        Fun_文件.创建文件夹(able.app_path + "cache");
        Fun_文件.创建文件夹(able.app_path + "System_Data");
        Fun_文件.创建文件夹(able.app_path + "Disk_Data");
        Fun_文件.创建文件夹(able.app_path + "Account");
        Fun_文件.创建文件夹(able.app_path + "Account/Data");
        Fun_文件.创建文件夹(able.app_path + "Account/Collection");
        Fun_文件.创建文件夹(able.app_path + "Square_Data");
        Fun_文件.创建文件夹(able.app_path + "Hot_Data");
        Fun_文件.创建文件夹(able.app_path + "YinYong_Data");
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(9999);
        dispatcher.setMaxRequestsPerHost(9999);
        able.okHttpClient = new OkHttpClient().newBuilder()
                .cookieJar(CookieJar.NO_COOKIES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .dispatcher(dispatcher)
                .build();
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Essay_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Essay_index.txt", "50");
        }
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Hot_Essay_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Hot_Essay_index.txt", "10");
        }
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Home_Essay_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Home_Essay_index.txt", "10");
        }
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Home_Collection_Essay_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Home_Collection_Essay_index.txt", "10");
        }
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Time_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Time_index.txt", "5000");
        }
        if(!Fun_文件.是否存在(able.app_path + "System_Data/Disk_index.txt")){
            Fun_文件.写入文件(able.app_path + "System_Data/Disk_index.txt", "3");
        }
        LeastRecentlyUsedCacheEvictor leastRecentlyUsedCacheEvictor = new
                LeastRecentlyUsedCacheEvictor(100 * 1024 * 1024);
        simpleCache = new SimpleCache(new File(able.app_path + "video_cache"), leastRecentlyUsedCacheEvictor, new ExoDatabaseProvider(this));
    }

    public static SimpleCache getVideoCache(){
        return simpleCache;
    }

}