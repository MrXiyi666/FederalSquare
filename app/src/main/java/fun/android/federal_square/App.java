package fun.android.federal_square;

import android.app.Application;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        able.app_path = Objects.requireNonNull(this.getExternalFilesDir("")).getPath() + "/";
        //able.app_path = Objects.requireNonNull(this.getFilesDir()).getPath() + "/";
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
    }

}