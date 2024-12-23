package fun.android.federal_square.network;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import fun.android.federal_square.fun.Fun;
import okhttp3.FormBody;

public class NetWork_评论_发表 extends NetWork_Main {
    private View view;
    private LinearLayout linear;
    public NetWork_评论_发表(Activity activity) {
        super(activity);
    }

    public void 传递参数(String 网址, String PassWord, String square_time, String discuss_time, String discuss_data, View view, LinearLayout linear){
        this.view = view;
        this.linear = linear;
        formBody = new FormBody.Builder()
                .add("PassWord", PassWord)
                .add("path", "./Discuss_Data/" + square_time + "/" + discuss_time + ".json")
                .add("data", discuss_data)
                .build();
        url = 网址;
        password = PassWord;
        url_path = "federal-square/Write_Txt.php";
        b_mess = false;
    }

    @Override
    public void 刷新() {
        super.刷新();
        linear.addView(view, 0);
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("ok")){
            this.b_update = true;
            Fun.mess(activity, "评价成功");
        }
    }

}
