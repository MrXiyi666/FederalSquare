package fun.android.federal_square.system;

import android.app.Application;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Static.app_path = Objects.requireNonNull(this.getExternalFilesDir("")).getPath() + "/";


        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(64);          // 全局并发：64 条 TCP 连接同时跑
        dispatcher.setMaxRequestsPerHost(20);   // 单域名并发：20 条，既不被 CDN 拉黑又能跑满带宽

        Static.okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)  // TCP 握手 30s 足够
                .writeTimeout(60, TimeUnit.SECONDS)    // 上传/发送 60s
                .readTimeout(120, TimeUnit.SECONDS)    // 下载 2min 没数据再抛异常
                .dispatcher(dispatcher)
                .build();
    }
}
