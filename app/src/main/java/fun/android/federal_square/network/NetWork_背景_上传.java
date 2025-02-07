package fun.android.federal_square.network;

import android.app.Activity;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_背景_上传 extends NetWork_Main{
    public NetWork_背景_上传(Activity activity) {
        super(activity);
    }
    private List<Post_Data> post_dataList;
    private String back_url;
    private ImageView back_img;
    private AlertDialog fun_dialog;
    public void 传递参数(List<Post_Data> post_dataList, String back_url, ImageView back_img, AlertDialog fun_dialog){
        this.back_img = back_img;
        this.back_url = back_url;
        this.post_dataList = post_dataList;
        this.fun_dialog = fun_dialog;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path", "./Account/" + Fun_账号.GetID() + "/" + Fun_账号.GetID() + ".txt")
                .add("data", able.gson.toJson(this.post_dataList))
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Write_Txt.php";
        b_mess = false;
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(!string.equals("ok")){
            Fun.mess(activity, string);
            return;
        }
        Fun.mess(activity, "保存成功", 300);
        Fun_账号.保存账号(post_dataList);
        this.b_update = true;
    }

    @Override
    public void 刷新() {
        super.刷新();
        Glide.with(activity)
                .load(back_url)
                .apply(able.requestOptions)
                .into(back_img);
        back_img.setBackgroundResource(R.drawable.gradient_white_to_transparent);
        if(fun_dialog != null){
           fun_dialog.dismiss();
        }

    }
}
