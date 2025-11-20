package fun.android.federal_square.NetWork;

import android.app.Activity;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Fun_文件;
import fun.android.federal_square.system.Static;
import fun.android.federal_square.window.Window_Progress_Bar;
import fun.android.federal_square.window.Window_View_Image;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetWork_Main {
    private final Activity activity;
    private final String class_name;
    private String file_name;
    private Window_Progress_Bar window_progress_bar;
    public NetWork_Main(Activity activity){
        this.activity = activity;
        this.class_name = this.getClass().getSimpleName();
    }

    public void 事件(String string){

    }

    public void create(){
        window_progress_bar = new Window_Progress_Bar(activity);
    }
    /**
     * 刷新一些内容内容
     * @param progress 下载进度
     */
    public void update(int progress){
        activity.runOnUiThread(()->{
            window_progress_bar.setProgress(progress);
        });
    }

    public void start(String url){
        Log.w(class_name, "线程开启");
        Request request = new Request.Builder().url(url).build();

        file_name = Fun.getFileNameFromUrl(url);
        create();
        if (file_name == null || file_name.isEmpty()) {
            Log.w(class_name, "未找到文件名 下载取消");
            return;
        }
        Static.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    if (response.body() != null) {
                        response.body().close();
                    }
                    Log.w(class_name, "下载失败1");
                    activity.runOnUiThread(()->{
                        window_progress_bar.close();
                    });
                    return;
                }
                ResponseBody body = response.body();
                if (body == null) {
                    Log.w(class_name, "下载失败2");
                    activity.runOnUiThread(()->{
                        window_progress_bar.close();
                    });
                    return;
                }
                写入文件(body);

            }
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMsg = "请求出错: " + e.getMessage();
                Log.w(class_name, errorMsg);
                activity.runOnUiThread(()->{
                    window_progress_bar.close();
                });
            }
        });
    }

    private void 写入文件(ResponseBody body) throws IOException {
        // 6. 流写入文件（核心逻辑）
        Log.w(class_name, "保存文件");
        InputStream in = body.byteStream();

        Log.w(class_name, "保存路径" + Static.app_path + Static.Example_Name + "/" + file_name);
        Fun_文件.创建文件夹(Static.app_path + Static.Example_Name);
        FileOutputStream out = new FileOutputStream(new File(Static.app_path + Static.Example_Name, file_name));

        byte[] buffer = new byte[1024 * 4]; // 4KB 缓冲区
        int len;
        long total = 0;
        long fileSize = body.contentLength(); // 文件总大小

        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
            total += len;
            // 计算进度（仅打印日志，更新 UI 需切换线程）
            int progress = (int) (total * 100 / fileSize);
            Log.d(class_name, "下载进度：" + progress + "%");
            this.activity.runOnUiThread(()->{
                update(progress);
            });
        }
        Log.w(class_name, "下载结束");
        // 7. 关闭流
        out.flush();
        out.close();
        in.close();
        body.close();
        this.activity.runOnUiThread(()->{
            window_progress_bar.close();
        });
    }

}