package fun.android.federal_square.network;

import android.app.Activity;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.view.View_Home_Collection;
import okhttp3.FormBody;

public class NetWork_我的_收藏_删除 extends NetWork_Main{
    private String time;
    public NetWork_我的_收藏_删除(Activity activity ) {
        super(activity);
    }

    public void 传递参数(String account_id, String time){
        this.time = time;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + account_id + "/Collection/" + time + ".json")
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Delete_File.php";
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            Fun.mess(activity, "删除成功", 300);
            this.b_update = true;
            Fun_文件.删除文件(able.app_path + "Account/Collection/" + time + ".json");
            return;
        }
        if(string.equals("no_delete")){
            Fun.mess(activity, "删除失败", 300);
            return;
        }
        if(!string.equals("yes")){
            Fun.mess(activity, string);
            return;
        }
        Fun.mess(activity, "删除成功", 300);
        this.b_update = true;
        Fun_文件.删除文件(able.app_path + "Account/Collection/" + time + ".json");
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(able.view_home.view_home_page.view_home_collection!=null){
            able.view_home.view_home_page.view_home_collection.初始化收藏();
        }
    }


}
