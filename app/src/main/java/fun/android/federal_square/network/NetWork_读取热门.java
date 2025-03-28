package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_读取热门 extends NetWork_Main {
    public NetWork_读取热门(Activity activity ) {
        super(activity);
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Read_Hot_List.php";
        formBody = new FormBody.Builder().add("PassWord", able.PassWord).build();

    }


    @Override
    public void 刷新() {
        super.刷新();
        if(able.view_hot != null){
            able.view_hot.初始化数据();
        }
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        b_update = true;
        if(string.equals("no")){
            Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(new ArrayList<>()));
            return;
        }
        if(string.equals("no_list")){
            Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(new ArrayList<>()));
            return;
        }
        if(string.equals("no_size")){
            Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(new ArrayList<>()));
            return;
        }
        var network_list = new ArrayList<>(Arrays.asList(string.split("\n")));
        if(network_list.isEmpty()){
            Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(new ArrayList<>()));
            return;
        }
        Fun_文件.写入文件(able.app_path + "Hot_Data/list.json", able.gson.toJson(network_list));
        for(var name : network_list){
            if(Fun_文件.是否存在(able.app_path + "Square_Data/" + name + ".json")){
                continue;
            }
            down_list_data.add(name + ".json");
        }
    }
}
