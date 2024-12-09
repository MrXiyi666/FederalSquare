package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.view.View_Login;
import okhttp3.FormBody;

public class NetWork_注册 extends NetWork_Main {
    public NetWork_注册(Activity activity) {
        super(activity);
    }
    private List<Post_Data> post_dataList;
    public void 传递参数(List<Post_Data> account_data){
        this.post_dataList = account_data;
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("account", post_dataList.get(0).getText())
                .add("path", "./Account/" + post_dataList.get(0).getText())
                .add("data", able.gson.toJson(post_dataList))
                .build();
        url = able.URL_Name;
        url_path = "federal-square/Create_Account.php";
        b_mess = false;
        b_account = false;
        Log.w(class_name, url);
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(!string.equals("ok")){
            Fun.mess(activity, string);
            return;
        }
        this.b_update = true;
        Fun.mess(activity, "注册成功");
    }

    @Override
    public void 刷新() {
        super.刷新();
        able.view_home.linear_main.removeAllViews();
        able.view_home.linear_main.addView(new View_Login((MainActivity) activity).getView());
    }
}
