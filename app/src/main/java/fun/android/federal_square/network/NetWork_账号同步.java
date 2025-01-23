package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.View_Home_Page;
import okhttp3.FormBody;

public class NetWork_账号同步 extends NetWork_Main{
    private AlertDialog alertDialog;
    public NetWork_账号同步(Activity activity) {
        super(activity);
        b_account = true;
        b_mess = false;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + Fun_账号.GetID() + "/" + Fun_账号.GetID() + ".txt")
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Read_Txt.php";
    }

    public void 传递参数(AlertDialog alertDialog){
        this.alertDialog = alertDialog;
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            Fun.mess(activity, "同步失败", 300);
            return;
        }
        List<Post_Data> post_dataList = new ArrayList<>();
        if(Fun.StrBoolJSON(string)){
            post_dataList = able.gson.fromJson(string, new TypeToken<List<Post_Data>>(){}.getType());
        }
        if(post_dataList==null){
            Fun.mess(activity, "同步失败", 300);
            return;
        }
        if(post_dataList.isEmpty()){
            Fun.mess(activity, "同步失败", 300);
            return;
        }
        Fun_账号.保存账号(string);

        this.b_update = true;
        Fun.mess(activity, "同步成功", 300);
    }

    @Override
    public void 刷新() {
        super.刷新();
        this.alertDialog.dismiss();
        able.view_home.linear_main.removeAllViews();
        able.view_home.linear_main.addView(new View_Home_Page((MainActivity) activity).getView());
    }

    @Override
    public void start() {
        super.start();
    }
}
