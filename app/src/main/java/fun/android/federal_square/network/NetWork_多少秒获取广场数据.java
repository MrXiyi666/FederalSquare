package fun.android.federal_square.network;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.FormBody;

public class NetWork_多少秒获取广场数据 extends NetWork_Main{
    private boolean 是否新内容 = false;
    public NetWork_多少秒获取广场数据(Activity activity) {
        super(activity);
        formBody = new FormBody.Builder()
                .build();
        url = able.URL_Name;
        url_path = "federal-square/Time_Update_Square_New_Post.php";
        b_dialog = false;
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no")){
            return;
        }
        if(Fun_文件.是否存在(able.app_path + "Square_Data/" + string)){
            是否新内容 = false;
        }else{
            是否新内容 = true;
        }
        b_update = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(是否新内容){
            able.view_square.new_icon.setVisibility(View.VISIBLE);
        }else{
            able.view_square.new_icon.setVisibility(View.GONE);
        }

    }

}
