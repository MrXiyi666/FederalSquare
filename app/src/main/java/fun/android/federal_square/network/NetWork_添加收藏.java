package fun.android.federal_square.network;

import android.app.Activity;
import java.util.List;

import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_添加收藏 extends NetWork_Main {

    public NetWork_添加收藏(Activity activity) {
        super(activity);
       b_account = true;
    }
    private String post_time;
    private List<Post_Data> post_data;
    public void 传递参数(String post_time, List<Post_Data> post_data){
        this.post_time = post_time;
        this.post_data = post_data;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("post_time", post_time)
                .add("data", able.gson.toJson(this.post_data))
                .add("account_id", Fun_账号.GetID())
                .build();
        url = able.URL_Name;
        password = able.Read_PassWord;
        url_path = "federal-square/Add_Collection.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("cunzai")){
            Fun.mess(activity, "已收藏");
            return;
        }
        if(string.equals("no_file")){
            Fun.mess(activity, "收藏失败");
            return;
        }
        if(string.equals("yes_file")){
            Fun.mess(activity, "收藏成功");
            Fun_文件.写入文件(able.app_path + "Account/Collection/" + post_time + ".json", able.gson.toJson(this.post_data));
        }
    }

}