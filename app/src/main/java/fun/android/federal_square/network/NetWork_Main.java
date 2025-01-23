package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.window.加载等待窗口;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class NetWork_Main {
    public Activity activity;
    public String class_name;
    public boolean b_update = false, b_account = false, b_mess = true;
    private 加载等待窗口 dialog=null;
    public FormBody formBody=null;
    public String url, url_path, password;
    public boolean b_dialog = true;
    public List<String> down_list_data = new ArrayList<>();
    public List<String> down_list_collection_data = new ArrayList<>();


    public NetWork_Main(Activity activity){
        this.activity = activity;
        this.class_name = this.getClass().getSimpleName();
    }

    public void 事件(String string){

    }

    public void 刷新(){
        关闭等待窗口();
    }

    public void start(){
        b_update = false;
        if(b_dialog){
            dialog = null;
            dialog = new 加载等待窗口(activity);
        }
        if(b_account){
            if(Fun_账号.GetID().isEmpty()){
                Log.w(class_name, "null");
                Fun.mess(activity, "未登录");
                return;
            }
        }
        if(formBody == null){
            return;
        }
        if(url == null){
            return;
        }
        if(url.isEmpty()){
            return;
        }
        new Thread(()->{
            try {
                Request request = new Request.Builder()
                        .url(url + url_path)
                        .post(formBody)
                        .build();
                Response response = able.okHttpClient.newCall(request).execute();
                if(!response.isSuccessful()){
                    Log.w(class_name, url + " isSuccessfulnull");
                    Fun.mess(activity, url + "isSuccessfulnull");
                    throw new Exception("跳出");
                }
                if(response.body() == null){
                    Log.w(class_name, url + "response.body() null");
                    Fun.mess(activity, url + "response.body() null");
                    throw new Exception("跳出");
                }
                String string=response.body().string();
                response.close();
                if(string.isEmpty()){
                    Fun.mess(activity, url + "string null");
                    Log.w(class_name, url + "string null");
                    throw new Exception("跳出");
                }
                if(string.equals("Null_PassWord")){
                    Fun.mess(activity, url + "\n没有密码");
                    throw new Exception("跳出");
                }
                if(string.equals("Error_PassWord")){
                    Fun.mess(activity, url + "\n密码错误");
                    throw new Exception("跳出");
                }

                事件(string);
                for(String name : down_list_data){
                    FormBody d_formBody = new FormBody.Builder()
                            .add("PassWord", password)
                            .add("path", "./Square_Data/" + name)
                            .build();
                    Request d_request = new Request.Builder()
                            .url(this.url + "federal-square/Read_Txt.php")
                            .post(d_formBody)
                            .build();
                    Response d_response = able.okHttpClient.newCall(d_request).execute();
                    if(!d_response.isSuccessful()){
                        Log.w(class_name + "下载", this.url + "isSuccessful null");
                        continue;
                    }
                    if(d_response.body() == null){
                        Log.w(class_name + "下载", this.url + "response.body() null");
                        continue;
                    }
                    String d_string=d_response.body().string();
                    d_response.close();
                    if(d_string.isEmpty()){
                        Log.w(class_name + "下载", "string null");
                        continue;
                    }
                    if(d_string.equals("no_file")){
                        Log.w(class_name + "下载", "no_file");
                        continue;
                    }
                    if(Fun.StrBoolJSON(d_string)){
                        Fun_文件.写入文件(able.app_path + "Square_Data/" + name, d_string);
                    }
                }
                for(String name : down_list_collection_data){
                    FormBody d_formBody = new FormBody.Builder()
                            .add("PassWord", password)
                            .add("path", "./Account/" + Fun_账号.GetID() + "/Collection/" + name)
                            .build();
                    Request d_request = new Request.Builder()
                            .url(this.url + "federal-square/Read_Txt.php")
                            .post(d_formBody)
                            .build();
                    Response d_response = able.okHttpClient.newCall(d_request).execute();
                    if(!d_response.isSuccessful()){
                        Log.w(class_name + "下载", "null");
                        continue;
                    }
                    if(d_response.body() == null){
                        Log.w(class_name + "下载", "null");
                        continue;
                    }
                    String d_string=d_response.body().string();
                    d_response.close();
                    if(d_string.isEmpty()){
                        Log.w(class_name + "下载", "null");
                        continue;
                    }
                    if(d_string.equals("no_file")){
                        Log.w(class_name + "下载", "no_file");
                        continue;
                    }
                    if(Fun.StrBoolJSON(d_string)){
                        Fun_文件.写入文件(able.app_path + "Account/Collection/" + name, d_string);
                    }
                }
                if(b_update){
                    able.handler.post(()->{
                        刷新();
                    });
                }
            }catch (Exception e){
               Log.w(class_name, e);
            }
            关闭等待窗口();
        }).start();
    }

    private void 关闭等待窗口(){
        if(b_dialog){
            new Thread(()->{
                try{
                    Thread.sleep(300);
                }catch(Exception ignored) {}
                if(dialog==null){
                    return;
                }
                dialog.关闭();
                dialog = null;
                if(b_mess){
                    Fun.mess(activity, "成功刷新");
                }
            }).start();

        }
    }

}