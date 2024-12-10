package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_多少秒获取广场数据 extends NetWork_Main{
    private boolean 是否新内容 = false;
    public NetWork_多少秒获取广场数据(Activity activity) {
        super(activity);
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("path", "./Square_Data")
                .build();
        url = able.URL_Name;
        password = able.Read_PassWord;
        url_path = "federal-square/Read_Folder_List.php";
        b_dialog = false;
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
        String[] dd = string.split("\n");
        List<String> filename = new ArrayList<>(Arrays.asList(dd));
        if(Fun_文件.是否存在(able.app_path + "Square_Data/" + filename.get(filename.size()-1))){
            是否新内容 = false;
        }else{
            是否新内容 = true;
        }
        b_update = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(是否新内容){
            able.view_square.new_icon.setVisibility(View.VISIBLE);
        }else{
            able.view_square.new_icon.setVisibility(View.GONE);
        }

    }

}
