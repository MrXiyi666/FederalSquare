package fun.android.federal_square.network;

import android.app.Activity;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_我的_文章_刷新 extends NetWork_Main {
    public NetWork_我的_文章_刷新(Activity activity) {
        super(activity);
        this.b_account = true;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + Fun_账号.GetID() + "/Data")
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Read_Folder_List.php";
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no")){
            this.b_update = true;
            Fun_文件.删除文件夹(new File(able.app_path + "Account/Data"));
            Fun_文件.创建文件夹(able.app_path + "Account/Data");
            return;
        }
        if(string.equals("no_folder")){
            this.b_update = true;
            Fun_文件.删除文件夹(new File(able.app_path + "Account/Data"));
            Fun_文件.创建文件夹(able.app_path + "Account/Data");
        }
        String[] dd = string.split("\n");
        List<String> filename = new ArrayList<>(Arrays.asList(dd));
        if(filename.isEmpty()){
            return;
        }
        for(String name : filename){
            if(!Fun_文件.是否存在(able.app_path + "Square_Data/" + name)){
                down_list_data.add(name);
            }
            if(!Fun_文件.是否存在(able.app_path + "Account/Data/" + name)){
                Fun_文件.写入文件(able.app_path + "Account/Data/" + name, "");
                this.b_update = true;
            }
        }
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(able.view_home.view_home_page.view_home_essay!=null){
            able.view_home.view_home_page.view_home_essay.初始化数据();
        }
    }
}