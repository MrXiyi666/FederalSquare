package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_广场刷新 extends NetWork_Main {
    public NetWork_广场刷新(Activity activity) {
        super(activity);
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .build();
        url = able.URL_Name;
        password = able.Read_PassWord;
        url_path = "federal-square/Read_Square_Data_List.php";
        b_mess = false;
    }

    public void 传递参数(String 网址, String PassWord){
        this.url = 网址;
        formBody = null;
        url_path = "federal-square/Read_Square_Data_List.php";
        password = PassWord;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", PassWord)
                .build();
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no")){
            return;
        }
        String[] dd = string.split("\n");
        List<String> filename = new ArrayList<>(Arrays.asList(dd));
        for(String name : filename){
            if(Fun_文件.是否存在(able.app_path + "Square_Data/" + name)){
               continue;
            }
            down_list_data.add(name);
            Log.w(class_name, string);
            b_update = true;
            b_mess = true;
        }
    }

    @Override
    public void 刷新() {
        super.刷新();
        able.view_square.初始化本地数据();
    }
}
