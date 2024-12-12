package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Disk_Grid_Adapter;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_背景_上传;
import fun.android.federal_square.view.View_Home_Page;

public class Fun_选择背景 {

    public static void 启动(Activity activity, View_Home_Page view_HomePage){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_select_image_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        GridView gridview = view.findViewById(R.id.gridview);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        List<String> file_list = Fun_图片.遍历所有图片不带域名();
        if(file_list.isEmpty()){
            Fun.mess(activity, "网盘数据为空");
            return;
        }
        try {

            gridview.setAdapter(new Disk_Grid_Adapter(activity, file_list));
            gridview.setOnItemClickListener((adapterView, view1, position, l) -> {
                List<Post_Data> post_dataList = Fun_账号.重新生成(Fun_账号.GetID(), Fun_账号.GetPassWord(), Fun_账号.GetName(), Fun_账号.GetSign(), Fun_账号.GetAvatar_Url(), able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position), Fun_账号.Get发贴开关(), Fun_账号.Get评论开关());
                if(!post_dataList.isEmpty()){
                    NetWork_背景_上传 netWork_背景_上传 = new NetWork_背景_上传(activity);
                    netWork_背景_上传.传递参数(post_dataList, able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position), view_HomePage.back_img, dialog);
                    netWork_背景_上传.start();
                }
            });
        }catch (Exception e){

        }




        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
