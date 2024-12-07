package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Disk_Grid_Adapter;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_头像_上传;

public class Fun_选择头像 {
    public static void 选择头像(Activity activity, ImageView avatar_img){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_select_image_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        GridView gridview = view.findViewById(R.id.gridview);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        List<String> file_list = 遍历所有图片();
        if(file_list.isEmpty()){
            Fun.mess(activity, "网盘数据为空");
            return;
        }

        gridview.setAdapter(new Disk_Grid_Adapter(activity, file_list));
        gridview.setOnItemClickListener((adapterView, view1, position, l) -> {
            List<Post_Data> post_dataList = Fun_账号.重新生成(Fun_账号.GetID(), Fun_账号.GetPassWord(), Fun_账号.GetName(), Fun_账号.GetSign(), file_list.get(position), Fun_账号.GetBack_Url(), Fun_账号.Get发贴开关(), Fun_账号.Get评论开关());
            if(!post_dataList.isEmpty()){
                NetWork_头像_上传 netWork_头像_上传 = new NetWork_头像_上传(activity);
                netWork_头像_上传.传递参数(post_dataList, file_list.get(position), avatar_img, dialog);
                netWork_头像_上传.start();
            }
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }

    private static List<String> 遍历所有图片(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        Comparator<String> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        List<String> return_list = new ArrayList<>();
        for(String name : list){
            return_list.add(able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + name);
        }
        return return_list;
    }

}
