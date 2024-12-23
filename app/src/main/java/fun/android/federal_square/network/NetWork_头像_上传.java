package fun.android.federal_square.network;

import android.app.Activity;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_头像_上传 extends NetWork_Main {
    public NetWork_头像_上传(Activity activity ) {
        super(activity);
    }
    private List<Post_Data> post_dataList;
    private String tou_url;
    private ImageView avatar_img;
    private AlertDialog fun_dialog;
    public void 传递参数(List<Post_Data> post_dataList, String tou_url, ImageView avatar_img, AlertDialog dialog){
        this.post_dataList = post_dataList;
        this.tou_url = tou_url;
        this.avatar_img = avatar_img;
        this.fun_dialog = dialog;
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
            Fun.mess(activity, "保存失败");
            return;
        }
        Fun.mess(activity, "保存成功");
        Fun_账号.保存账号(post_dataList);
        this.b_update = true;

    }

    @Override
    public void 刷新() {
        super.刷新();
        if(!tou_url.isEmpty()){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.glide_zhanwei)
                    .circleCropTransform()
                    .error(R.drawable.glide_shibai)
                    .fallback(R.drawable.glide_duqushibai);
            Glide.with(activity)
                    .load(tou_url)
                    .apply(requestOptions)
                    .into(avatar_img);
        }else {
            avatar_img.setImageResource(R.mipmap.ic_launcher_round);
        }

        if(fun_dialog != null){
            fun_dialog.dismiss();
        }
    }
}
