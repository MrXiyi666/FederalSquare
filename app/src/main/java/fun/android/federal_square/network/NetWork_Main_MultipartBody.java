package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.window.加载等待窗口;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetWork_Main_MultipartBody {
    public boolean b_update = false;
    public Activity activity;
    public String class_name;
    public RequestBody body;
    private 加载等待窗口 dialog;
    public Request request;
    public boolean 账号检测 = true;
    public MultipartBody multipartBody;
    public String url;
    public NetWork_Main_MultipartBody(Activity activity){
        this.class_name = this.getClass().getSimpleName();
        this.activity = activity;
    }

    public void 事件(String string){

    }

    public void 失败(){

    }
    public void 刷新(){

    }


    public void start(){
        b_update = false;
        if(账号检测){
            if(Fun_账号.GetID().isEmpty()){
                Log.w(class_name, "null");
                Fun.mess(activity, "未登录");
                return;
            }
        }
        if(multipartBody == null){
            return;
        }
        new Thread(()->{
            dialog = new 加载等待窗口(activity);
            try {
                request = new Request.Builder()
                        .url(url)
                        .post(multipartBody)
                        .build();
                Response response = able.okHttpClient.newCall(request).execute();
                if(response.body() == null){
                    Log.w(class_name, "null");
                    throw new Exception("跳出");
                }
                String string=response.body().string();
                response.close();
                if(string.isEmpty()){
                    throw new Exception("跳出");
                }
                事件(string);
                if(b_update){
                    able.handler.post(()->{
                        刷新();
                    });
                }
            }catch (Exception e){
                Log.w(class_name, e);
                Fun.mess(activity, class_name + e);
                able.handler.post(()->{
                    失败();
                });
            }
            dialog.关闭();
        }).start();
    }
}