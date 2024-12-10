package fun.android.federal_square.network;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import java.io.IOException;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetWork_Main_MultipartBody {
    public boolean b_update = false;
    public Activity activity;
    public String class_name = "";
    public RequestBody body;
    private AlertDialog dialog;
    public Request request;
    public boolean 账号检测 = false;
    public MultipartBody multipartBody;
    public String url;
    public NetWork_Main_MultipartBody(Activity activity){
        this.class_name = this.getClass().getSimpleName();
        this.activity = activity;
        able.handler.post(()->{
            dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            text_id.setText("→→→  加载中  ←←←");
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        });
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
        able.handler.post(()->{
            dialog.show();
        });
        if(multipartBody == null){
            return;
        }
        request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        Call call = able.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                dialog.dismiss();
                Log.w(class_name, e);
                Fun.mess(activity, class_name + e);
                able.handler.post(()->{
                    失败();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                dialog.dismiss();
                if(response.body() == null){
                    Log.w(class_name, "null");
                    return;
                }
                String string=response.body().string();
                response.close();
                if(string.isEmpty()){
                    return;
                }
                事件(string);
                if(b_update){
                    able.handler.post(()->{
                        刷新();
                    });
                }
            }
        });
    }
}
