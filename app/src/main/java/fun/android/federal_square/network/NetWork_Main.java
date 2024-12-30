package fun.android.federal_square.network;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class NetWork_Main {
    public Activity activity;
    public String class_name;
    public boolean b_update = false, b_account = false, b_mess = true;
    private AlertDialog dialog;
    public FormBody formBody;
    public String url, url_path, password;
    public boolean b_dialog = true;
    public List<String> down_list_data = new ArrayList<>();
    public List<String> down_list_collection_data = new ArrayList<>();

    public NetWork_Main(Activity activity){
        this.activity = activity;
        this.class_name = this.getClass().getSimpleName();
        初始化等待窗口();
    }

    private void 初始化等待窗口(){
        able.handler.post(()->{
            dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            return_icon.setVisibility(View.GONE);
            text_id.setText("  ✍  ");

            view.setOnClickListener(V->{
                dialog.dismiss();
                dialog = null;
            });
            if(dialog == null){
                return;
            }
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.TOP);
        });
    }
    public void 事件(String string){

    }

    public void 刷新(){

    }

    public void start(){
        b_update = false;
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
        if(b_dialog){
            if(dialog == null){
                初始化等待窗口();
            }
            able.handler.post(()-> dialog.show());
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
                    return;
                }
                if(response.body() == null){
                    Log.w(class_name, url + "response.body() null");
                    Fun.mess(activity, url + "response.body() null");
                    return;
                }
                String string=response.body().string();
                response.close();
                if(string.isEmpty()){
                    Fun.mess(activity, url + "string null");
                    Log.w(class_name, url + "string null");
                    return;
                }
                if(string.equals("Null_PassWord") | string.equals("Error_PassWord")){
                    Fun.mess(activity, url + "\n" + string);

                    return;
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
                    Fun_文件.写入文件(able.app_path + "Square_Data/" + name, d_string);
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
                    Fun_文件.写入文件(able.app_path + "Account/Collection/" + name, d_string);
                }
                if(b_update){
                    able.handler.post(()->{
                        刷新();
                    });
                    if(b_mess){
                        Fun.mess(activity, "成功刷新");
                    }
                }
            }catch (Exception e){
               Log.w(class_name, e);
            }
            if(b_dialog){
                able.handler.post(()-> {
                    new Thread(()->{
                        try {
                            Thread.sleep(300);
                        } catch (Exception ignored) {

                        }
                        if(dialog == null){
                            return;
                        }
                        dialog.dismiss();
                        dialog = null;
                    }).start();
                });
            }

        }).start();
    }
}