package fun.android.federal_square.network;

import android.app.Activity;
import android.widget.LinearLayout;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.window.查看评论窗口;
import okhttp3.FormBody;

public class NetWork_评论_读取 extends NetWork_Main {
    public NetWork_评论_读取(Activity activity) {
        super(activity);
    }
    private LinearLayout linear;
    private String string;
    public void 传递参数(String square_time, LinearLayout linear, String 网址 , String PassWord){
        this.linear = linear;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", PassWord)
                .add("path", "./Discuss_Data/" + square_time)
                .build();
        url = 网址;
        password = PassWord;
        url_path = "federal-square/Read_Discuss_List.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);

        if(string.equals("no")){
            return;
        }
        if(string.equals("no_folder")){
            return;
        }
        this.string = string;
        this.b_update = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        String[] dd = string.split("\n");
        List<String> filename = new ArrayList<>(Arrays.asList(dd));
        linear.removeAllViews();
        for(String data : filename){
            if(!Fun.StrBoolJSON(data)){
                continue;
            }
            linear.addView(查看评论窗口.添加评论布局(activity, able.gson.fromJson(data, new TypeToken<List<Post_Data>>(){}.getType())));
        }
    }

}
