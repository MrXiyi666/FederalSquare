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

    public void 传递参数(String 网址, String square_time, String discuss_time, String discuss_data, View view, LinearLayout linear){
        this.view = view;
        this.linear = linear;
        formBody = new FormBody.Builder()
                .add("square_time", square_time)
                .add("discuss_time", discuss_time)
                .add("discuss_data", discuss_data)
                .build();
        url = 网址;
        url_path = "federal-square/Create_Discuss.php";
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
        if(string.equals("no_discuss")){
            Fun.mess(activity, "传输失败");
            return;
        }
        if(string.equals("no_square")){
            Fun.mess(activity, "评论失败");
            return;
        }
        if(string.equals("create_file_error")){
            Fun.mess(activity, "评论失败");
            return;
        }
        this.b_update = true;
        Fun.mess(activity, "评价成功");
    }

}
