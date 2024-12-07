package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import java.util.List;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.View_Home_Page;
import okhttp3.FormBody;

public class NetWork_登录 extends NetWork_Main {
    private String PassWord="";
    public NetWork_登录(Activity activity) {
        super(activity);
    }

    public void 传递参数(String id, String password){
        this.PassWord=password;
        formBody = new FormBody.Builder()
                .add("path", "./Account/" + id + "/" + id + ".txt")
                .build();
        url = able.URL_Name;
        url_path = "federal-square/Read_Txt.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            return;
        }
        try {
            List<Post_Data> post_dataList = able.gson.fromJson(string, new TypeToken<List<Post_Data>>(){}.getType());
            if(post_dataList == null){
                return;
            }
            if(post_dataList.isEmpty()){
                return;
            }
            for(Post_Data pd : post_dataList){
                if(pd.getName().equals("PassWord")){
                    if(pd.getText().equals(PassWord)){
                        Fun.mess(activity, "登陆成功");
                        this.b_update = true;
                        Fun_账号.保存账号(post_dataList);
                    }else{
                        Fun.mess(activity, "密码错误");
                    }
                }
            }
        }catch (Exception e){
            this.b_update = false;
        }

    }

    @Override
    public void 刷新() {
        super.刷新();
        able.view_home.linear_main.removeAllViews();
        able.view_home.linear_main.addView(new View_Home_Page((MainActivity) activity).getView());
    }

}
