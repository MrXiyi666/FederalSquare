package fun.android.federal_square.network;

import android.app.Activity;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.View_Home_Article;
import okhttp3.FormBody;

public class NetWork_我的_文章_刷新 extends NetWork_Main {
    private View_Home_Article view_home_article;
    public NetWork_我的_文章_刷新(Activity activity) {
        super(activity);
        this.b_account = true;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("Account", Fun_账号.GetID())
                .build();
        url = able.URL_Name;
        password = able.Read_PassWord;
        url_path = "federal-square/Read_The_Account_List.php";
        b_mess = false;
    }

    public void 传递参数(View_Home_Article view_home_article){
        this.view_home_article = view_home_article;
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no")){
            view_home_article.linear.post(()-> view_home_article.linear.removeAllViews());
            Fun_文件.删除文件夹(new File(able.app_path + "Account/Data"));
            Fun_文件.创建文件夹(able.app_path + "Account/Data");
            return;
        }
        String[] dd = string.split("\n");
        List<String> filename = new ArrayList<>(Arrays.asList(dd));
        for(String name : filename){
            if(!Fun_文件.是否存在(able.app_path + "Square_Data/" + name)){
                down_list_data.add(name);
            }
            if(!Fun_文件.是否存在(able.app_path + "Account/Data/" + name)){
                Fun_文件.写入文件(able.app_path + "Account/Data/" + name, "");
                this.b_update = true;
                b_mess = true;
            }
        }
    }

    @Override
    public void 刷新() {
        super.刷新();
        view_home_article.初始化数据();
    }

}
