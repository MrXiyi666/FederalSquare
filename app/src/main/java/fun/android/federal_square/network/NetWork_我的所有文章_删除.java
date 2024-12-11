package fun.android.federal_square.network;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import fun.android.federal_square.View_Article;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_我的所有文章_删除 extends NetWork_Main{
    private String time;
    private View_Article view_article;
    public NetWork_我的所有文章_删除(Activity activity) {
        super(activity);
    }
    public void 传递参数(String account_id, String time, View_Article view_article){
        this.time = time;
        this.view_article = view_article;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("path", "./Account/" + account_id + "/Data/" + time + ".json")
                .build();
        url = able.URL_Name;
        password = able.Read_PassWord;
        url_path = "federal-square/Delete_File.php";
        b_mess = false;
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            Fun.mess(activity, "文件不存在");
            Fun_文件.删除文件(able.app_path + "Account/Data/" + time + ".json");
            return;
        }
        if(string.equals("no_delete")){
            Fun.mess(activity, "删除失败");
            return;
        }
        Fun.mess(activity, "删除成功");
        this.b_update = true;
        Fun_文件.删除文件(able.app_path + "Account/Data/" + time + ".json");
        b_mess = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        view_article.初始化数据();
    }

}
