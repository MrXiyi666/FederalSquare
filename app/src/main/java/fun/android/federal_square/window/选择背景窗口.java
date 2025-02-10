package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Disk_Grid_Adapter;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_图片;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_背景_上传;
import fun.android.federal_square.view.View_Home_Page;

public class 选择背景窗口 {
    @SuppressLint("StaticFieldLeak")
    private static GridView gridview;
    private static Disk_Grid_Adapter disk_grid_adapter;
    public static void 启动(Activity activity, View_Home_Page view_HomePage){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_select_image_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        gridview = view.findViewById(R.id.gridview);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        List<String> file_list = Fun_图片.遍历所有图片();
        if(file_list.isEmpty()){
            Fun.mess(activity, "网盘数据为空");
            return;
        }
        int Disk_Index = 3;
        String Str_index = Fun_文件.读取文件(able.app_path + "System_Data/Disk_index.txt");
        if(!Str_index.isEmpty()){
            Disk_Index = Integer.parseInt(Str_index);
        }
        gridview.setNumColumns(Disk_Index);
        disk_grid_adapter = new Disk_Grid_Adapter(activity, file_list,Disk_Index);
        gridview.setAdapter(disk_grid_adapter);
        gridview.setOnItemClickListener((_, _, position, l) -> {
            List<Post_Data> post_dataList = Fun_账号.重新生成(Fun_账号.GetID(), Fun_账号.GetPassWord(), Fun_账号.GetName(), Fun_账号.GetSign(), Fun_账号.GetAvatar_Url(), able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position), Fun_账号.Get发贴开关(), Fun_账号.Get评论开关());
            if(!post_dataList.isEmpty()){
                NetWork_背景_上传 netWork_背景_上传 = new NetWork_背景_上传(activity);
                netWork_背景_上传.传递参数(post_dataList, able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position), view_HomePage.back_img, dialog);
                netWork_背景_上传.start();
            }
        });


        dialog.setOnDismissListener(_ -> {
            // 释放GridView资源
            if(gridview != null){
                // 先清除Adapter引用
                gridview.setAdapter(null);
                // 可选：移除所有子视图
                gridview.removeAllViewsInLayout();
                gridview = null;
            }
            // 释放Adapter资源
            if(disk_grid_adapter != null){
                // 执行自定义清理方法
                disk_grid_adapter.clearResources();
                disk_grid_adapter = null;
            }
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((_, keyCode, _) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
