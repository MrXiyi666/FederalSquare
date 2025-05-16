package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.ArrayList;
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
import fun.android.federal_square.network.NetWork_发表文章;
import fun.android.federal_square.view.Video_ImageView;

public class 发表文章窗口 {
    private LinearLayout linear;
    private List<Post_Data> post_dataList;
    private ScrollView scrollView;
    @SuppressLint("StaticFieldLeak")
    private GridView gridview;
    private Disk_Grid_Adapter disk_grid_adapter;
    public void 创建发表文章窗口(Activity activity ){
        var dialog = new AlertDialog.Builder(activity).create();
        var view = View.inflate(activity, R.layout.window_post_view, null);
        scrollView = view.findViewById(R.id.scrollView);
        var ding_view = view.findViewById(R.id.ding_view);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText edit_text = view.findViewById(R.id.edit_text);
        AppCompatButton add_img = view.findViewById(R.id.add_img);
        AppCompatButton add_text = view.findViewById(R.id.add_text);
        AppCompatButton add_img_or_video_url = view.findViewById(R.id.add_img_or_video_url);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        linear = view.findViewById(R.id.linear);
        ding_view.post(()->{
            ViewGroup.LayoutParams layoutParams = ding_view.getLayoutParams();
            layoutParams.height = Fun.获取状态栏高度(activity);
            ding_view.setLayoutParams(layoutParams);
        });
        post_dataList = new ArrayList<>();
        return_icon.setOnClickListener(V->{
            for(int i=0; i<linear.getChildCount(); i++){
                if(linear.getChildAt(i) instanceof ImageView imageView){
                    imageView.setImageBitmap(null);
                }
            }
            linear.removeAllViews();
            post_dataList.clear();
            dialog.dismiss();
        });
        add_img.setOnClickListener(V->{
            选择图片(activity);
        });
        add_text.setOnClickListener(V->{
            var text_data = edit_text.getText().toString();
            if(!text_data.isEmpty()){
                var post_data = new Post_Data();
                post_data.setName("text");
                post_data.setText(text_data);
                var textView = new TextView(activity);
                textView.setTextSize(15);
                textView.setText(text_data);
                textView.setTextColor(Color.BLACK);
                var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 10);
                textView.setLayoutParams(params);
                textView.setOnLongClickListener(tV->{
                    删除窗口.发表文章删除元素窗口(activity, linear, textView, post_dataList, post_data);
                    return true;
                });
                post_dataList.add(post_data);
                linear.addView(textView);
                edit_text.setText("");
                Fun.回到底部(activity, scrollView);
            }else{
                Fun.mess(activity, "数据为空");
            }

        });

        add_img_or_video_url.setOnClickListener(V->{
            图片链接输入窗口(activity);
        });

        button_ok.setOnClickListener(V->{
            if(!post_dataList.isEmpty()){
                Post_Data name = new Post_Data();
                name.setName("name");
                name.setText(Fun_账号.GetName());
                Post_Data sign = new Post_Data();
                sign.setName("sign");
                sign.setText(Fun_账号.GetSign());
                Post_Data avatar = new Post_Data();
                avatar.setName("avatar");
                avatar.setText(Fun_账号.GetAvatar_Url());
                Post_Data time = new Post_Data();
                time.setName("time");
                time.setText(Fun.获取时间());
                Post_Data url = new Post_Data();
                url.setName("url");
                url.setText(able.URL);
                Post_Data password = new Post_Data();
                password.setName("password");
                password.setText(able.PassWord);
                post_dataList.add(name);
                post_dataList.add(sign);
                post_dataList.add(avatar);
                post_dataList.add(time);
                post_dataList.add(url);
                post_dataList.add(password);
                var netWork_发表文章 = new NetWork_发表文章(activity);
                netWork_发表文章.传递数据(post_dataList, time.getText(), dialog);
                netWork_发表文章.start();
            }else{
                Fun.mess(activity, "内容为空");
            }
        });
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int scrollViewHeight = scrollView.getHeight();
            int linearCount = linear.getChildCount();
            for (int i = 0; i < linearCount; i++) {
                View zi_view = linear.getChildAt(i);
                // 计算子视图是否在屏幕范围内
                int childY = (int) zi_view.getY();
                int childHeight = zi_view.getHeight();
                boolean isVisible = (childY + childHeight > scrollY) && (childY < scrollY + scrollViewHeight);
                int currentVisibility = zi_view.getVisibility();
                // 根据计算结果更新可见性
                if (isVisible) {
                    if (currentVisibility != View.VISIBLE) {
                        zi_view.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (currentVisibility != View.INVISIBLE) {
                        zi_view.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
    }
    public void 选择图片(Activity activity){
        var 选择图片窗口句柄 = new AlertDialog.Builder(activity).create();
        var view = View.inflate(activity, R.layout.window_select_image_view, null);
        var ding_view = view.findViewById(R.id.ding_view);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        ding_view.post(()->{
            ViewGroup.LayoutParams layoutParams = ding_view.getLayoutParams();
            layoutParams.height = Fun.获取状态栏高度(activity);
            ding_view.setLayoutParams(layoutParams);
        });

        gridview = view.findViewById(R.id.gridview);
        return_icon.setOnClickListener(V->{
            选择图片窗口句柄.dismiss();
        });
        var list = Fun_图片.遍历所有图片();
        if(list.isEmpty()){
            Fun.mess(activity, "网盘数据为空");
            return;
        }
        var Disk_Index = Fun.获取网盘数量();
        gridview.setNumColumns(Disk_Index);
        disk_grid_adapter = new Disk_Grid_Adapter(activity, list);
        gridview.setAdapter(disk_grid_adapter);
        gridview.setOnItemClickListener((adapterView, view1, position, l) -> {
            var post_data = new Post_Data();
            post_data.setName("img");
            post_data.setText(able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + list.get(position));
            var imageView = new Video_ImageView(activity);
            Glide.with(activity)
                    .load(able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + list.get(position))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(able.占位_request)
                    .into(imageView);
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Fun.屏幕宽度(activity) / 2);
            params.setMargins(0, 0, 0, 10);
            imageView.后缀 = Fun_文件.获取后缀(post_data.getText());
            imageView.setLayoutParams(params);
            imageView.setOnLongClickListener(V->{
                删除窗口.发表文章删除元素窗口(activity,linear,imageView,post_dataList,post_data);
                return true;
            });
            imageView.setOnClickListener(V->{
                if(Fun.图片格式判断(imageView.后缀)){
                    查看图片窗口.启动_Dialog(activity, post_data.getText());
                }else if(Fun.视频格式判断(imageView.后缀)){
                    查看视频窗口.启动_Dialog(activity, post_data.getText());
                }else{
                    打开方式窗口.启动(activity, post_data.getText());
                }
            });
            post_dataList.add(post_data);
            linear.addView(imageView);
            选择图片窗口句柄.dismiss();
            Fun.回到底部(activity, scrollView);
        });
        选择图片窗口句柄.setOnDismissListener(V -> {
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
        选择图片窗口句柄.setView(view);
        选择图片窗口句柄.setCancelable(true);
        选择图片窗口句柄.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        选择图片窗口句柄.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        选择图片窗口句柄.getWindow().setGravity(Gravity.CENTER);
        选择图片窗口句柄.show();

    }
    public void 图片链接输入窗口(Activity activity){
        var dialog = new AlertDialog.Builder(activity).create();
        var view = View.inflate(activity, R.layout.window_post_add_img_url_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        EditText edit_url = view.findViewById(R.id.edit_url);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            var str_url = edit_url.getText().toString();
            if(str_url.isEmpty()){
                return;
            }
            var post_data = new Post_Data();
            post_data.setName("img");
            post_data.setText(str_url);
            var imageView = new ImageView(activity);
            Glide.with(activity)
                    .load(str_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(able.占位_request)
                    .into(imageView);
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Fun.屏幕宽度(activity) / 2);
            params.setMargins(0, 0, 0, 10);
            imageView.setLayoutParams(params);
            imageView.setOnLongClickListener(V1->{
                var vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(10);
                imageView.setImageBitmap(null);
                linear.removeView(imageView);
                post_dataList.remove(post_data);
                return true;
            });
            imageView.setOnClickListener(V1->{
                var 后缀 = Fun_文件.获取后缀(str_url);
                if(Fun.图片格式判断(后缀)){
                    查看图片窗口.启动_Dialog(activity, str_url);
                }else if(Fun.视频格式判断(后缀)){
                    查看视频窗口.启动_Dialog(activity, str_url);
                }else{
                    打开方式窗口.启动(activity, str_url);
                }
            });
            post_dataList.add(post_data);
            linear.addView(imageView);
            dialog.dismiss();
            Fun.回到底部(activity, scrollView);
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}