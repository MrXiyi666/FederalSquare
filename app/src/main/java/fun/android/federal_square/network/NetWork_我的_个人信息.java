package fun.android.federal_square.network;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.View_Home_Page;
import okhttp3.FormBody;

public class NetWork_我的_个人信息 extends NetWork_Main {
    public NetWork_我的_个人信息(Activity activity) {
        super(activity);
    }
    private AlertDialog dialog;
    private List<Post_Data> post_dataList;
    public void 传递参数(String Name, String PassWord, String Sign, AlertDialog dialog){
        this.dialog = dialog;
        post_dataList = Fun_账号.重新生成(Fun_账号.GetID(), PassWord, Name, Sign, Fun_账号.GetAvatar_Url(), Fun_账号.GetBack_Url(), Fun_账号.Get发贴开关(), Fun_账号.Get评论开关());
        if(!post_dataList.isEmpty()){
            formBody = new FormBody.Builder()
                    .add("PassWord", able.PassWord)
                    .add("path", "./Account/" + Fun_账号.GetID() + "/" + Fun_账号.GetID() + ".txt")
                    .add("data", able.gson.toJson(post_dataList))
                    .build();
            url = able.URL;
            password = able.PassWord;
            url_path = "federal-square/Write_Txt.php";
        }
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(!string.equals("ok")){
            Fun.mess(activity, "修改失败");
            return;
        }
        Fun_文件.写入文件(able.app_path + "Account/account.json", able.gson.toJson(post_dataList));
        this.b_update = true;
        Fun.mess(activity, "修改成功");
        able.handler.post(()->{
            this.dialog.dismiss();
        });
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(able.view_home.linear_main!=null){
            able.view_home.linear_main.removeAllViews();
            able.view_home.linear_main.addView(new View_Home_Page((MainActivity) activity).getView());
        }
    }

}
