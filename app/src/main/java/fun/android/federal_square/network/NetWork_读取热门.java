package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_贴子;
import okhttp3.FormBody;

public class NetWork_读取热门 extends NetWork_Main {
    public NetWork_读取热门(Activity activity ) {
        super(activity);

        url = able.URL_Name;
        url_path = "federal-square/Read_Hot_List.php";
        formBody = new FormBody.Builder()
                .build();
        b_mess = false;
    }


    @Override
    public void 刷新() {
        super.刷新();
        able.view_hot.初始化数据();
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no") | string.equals("no_list") | string.equals("no_size")){
            Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(new ArrayList<>()));
            able.view_hot.linear.post(()-> able.view_hot.linear.removeAllViews());
            return;
        }
        List<String> filename = new ArrayList<>(Arrays.asList(string.split("\n")));
        Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(filename));
        for(String name : filename){
            if(Fun_文件.是否存在(able.app_path + "Square_Data/" + name + ".json")){
                continue;
            }
            down_list_data.add(name + ".json");
        }
        b_update = true;
    }
}
