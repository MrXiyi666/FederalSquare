package fun.android.federal_square.network;

import android.app.Activity;
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
    private String n_PassWord="";
    public NetWork_登录(Activity activity) {
        super(activity);
    }

    public void 传递参数(String id, String password1){
        this.n_PassWord=password1;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + id + "/" + id + ".txt")
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Read_Txt.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            Fun.mess(activity, "登陆失败 账号不存在", 300);
            return;
        }
        if(!Fun.StrBoolJSON(string)){
            Fun.mess(activity, "数据出错 请重试", 300);
            return;
        }
        List<Post_Data> post_dataList = able.gson.fromJson(string, new TypeToken<List<Post_Data>>(){}.getType());
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("PassWord")){
                if(pd.getText().equals(n_PassWord)){
                    Fun.mess(activity, "登陆成功", 300);
                    this.b_update = true;
                    Fun_账号.保存账号(post_dataList);
                }else{
                    Fun.mess(activity, "密码错误", 300);
                }
            }
        }

    }

    @Override
    public void 刷新() {
        super.刷新();
        if(able.view_home!=null){
            able.view_home.linear_main.removeAllViews();
            able.view_home.view_home_page = new View_Home_Page((MainActivity) activity);
            able.view_home.linear_main.addView(able.view_home.view_home_page.getView());
        }
    }

}
