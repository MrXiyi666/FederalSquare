package fun.android.federal_square.network;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_转发功能 extends NetWork_Main{
    public NetWork_转发功能(Activity activity) {
        super(activity);
        b_account = true;
        b_update = false;
        b_mess = false;
    }
    private String time;
    private List<Post_Data> postData;
    private LinearLayout button_forward;
    private View post_view;
    public void 传递参数(List<Post_Data> postData, String time, LinearLayout button_forward, View post_view){
        this.button_forward = button_forward;
        this.post_view = post_view;
        this.time = time;
        this.postData = postData;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Square_Data/" + time + ".json")
                .add("data", able.gson.toJson(postData))
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Write_Txt.php";
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(!string.equals("ok")){
            Fun.mess(activity, string);
        }
        Fun_文件.写入文件(able.app_path + "Square_Data/" + time + ".json", able.gson.toJson(this.postData));
        Fun.mess(activity, "转发成功");
        able.view_square.初始化本地数据();
    }

    @Override
    public void 刷新() {
        super.刷新();

    }
}
