package fun.android.federal_square.network;

import android.app.Activity;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_我的所有收藏_删除 extends NetWork_Main{
    private String time;
    private View_Collectin view_collectin;
    public NetWork_我的所有收藏_删除(Activity activity) {
        super(activity);
    }
    public void 传递参数(String account_id, String time, View_Collectin view_collectin){
        this.view_collectin = view_collectin;
        this.time = time;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("path", "./Account/" + account_id + "/Collection/" + time + ".json")
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
            return;
        }
        if(string.equals("no_delete")){
            Fun.mess(activity, "删除失败");
            return;
        }
        Fun.mess(activity, "删除成功");
        this.b_update = true;
        Fun_文件.删除文件(able.app_path + "Account/Collection/" + time + ".json");
    }

    @Override
    public void 刷新() {
        super.刷新();
        view_collectin.初始化数据();
    }
}
