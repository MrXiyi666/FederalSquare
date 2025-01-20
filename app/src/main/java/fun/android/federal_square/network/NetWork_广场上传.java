package fun.android.federal_square.network;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.view.Post_View;
import okhttp3.FormBody;

public class NetWork_广场上传 extends NetWork_Main {

    private String time;
    private List<Post_Data> post_dataList;
    private AlertDialog dialog;

    public NetWork_广场上传(Activity activity) {
        super(activity);
    }

    public void 传递数据(List<Post_Data> post_dataList, String time, AlertDialog dialog){
        this.dialog = dialog;
        this.time = time;
        this.post_dataList = post_dataList;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("account", Fun_账号.GetID())
                .add("name", time)
                .add("data", able.gson.toJson(post_dataList))
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Publish_Article.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(!string.equals("ok")){
            Fun.mess(activity,  "发表失败");
            return;
        }
        Fun_文件.写入文件(able.app_path + "Square_Data/" + time + ".json", able.gson.toJson(this.post_dataList));
        Fun_文件.写入文件(able.app_path + "Account/Data/" + time + ".json", "");
        Fun.mess(activity, "发表成功");
        this.b_update = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        dialog.dismiss();
        if(able.view_square.linear!=null){
            Post_View view = Fun_文章.Create_Post_View(activity, this.post_dataList, 0);
            able.view_square.linear.addView(view, 0);
            view.setVisibility(View.VISIBLE);
            if(able.view_square.linear.getChildCount() >= Integer.parseInt(Fun_文件.读取文件(able.app_path + "System_Data/Essay_index.txt"))){
                able.view_square.linear.removeViewAt(able.view_square.linear.getChildCount()-1);
            }
        }
        if(able.view_square.scrollView != null){
            able.view_square.scrollView.fullScroll(View.FOCUS_UP);
        }
    }
}