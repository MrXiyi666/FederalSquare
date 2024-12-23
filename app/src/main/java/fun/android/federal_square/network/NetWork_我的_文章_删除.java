package fun.android.federal_square.network;

import android.app.Activity;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.view.View_Home_Essay;
import okhttp3.FormBody;

public class NetWork_我的_文章_删除 extends NetWork_Main{
    private View_Home_Essay view_HomeArticle;
    private String time="";
    public NetWork_我的_文章_删除(Activity activity) {
        super(activity);
    }
    public void 传递参数(String account_id, String time, View_Home_Essay view_home_essay){
        this.time = time;
        this.view_HomeArticle = view_home_essay;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + account_id + "/Data/" + time + ".json")
                .build();
        url = able.URL;
        password = able.PassWord;
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
        view_HomeArticle.初始化数据();
    }


}
