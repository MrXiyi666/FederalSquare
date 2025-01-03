package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_评论_发表;
import fun.android.federal_square.network.NetWork_评论_读取;

public class 查看评论窗口 {
    public static void 查看评论窗口(Activity activity, String time_name, String 网址, String PassWord){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_check_reviews, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        LinearLayout linear = view.findViewById(R.id.linear);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        EditText edit_text = view.findViewById(R.id.edit_text);
        SwipeRefreshLayout swiperefee = view.findViewById(R.id.swiperefee);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        if(!Fun_账号.GetID().isEmpty()){
            button_ok.setOnClickListener(V->{
                String text = edit_text.getText().toString();
                if(text.isEmpty()){
                    return;
                }
                Post_Data post_data_name = new Post_Data();
                post_data_name.setName("Name");
                post_data_name.setText(Fun_账号.GetName());
                Post_Data post_data_sign = new Post_Data();
                post_data_sign.setName("Sign");
                post_data_sign.setText(Fun_账号.GetSign());
                Post_Data post_data_text = new Post_Data();
                post_data_text.setName("Text");
                post_data_text.setText(text);
                List<Post_Data> post_dataList = new ArrayList<>();
                post_dataList.add(post_data_name);
                post_dataList.add(post_data_sign);
                post_dataList.add(post_data_text);
                发布新的评论(activity, linear, post_dataList, time_name, 网址, PassWord);
                edit_text.setText("");
            });
        }
        NetWork_评论_读取 netWork_讨论_读取 = new NetWork_评论_读取(activity);
        netWork_讨论_读取.传递参数(time_name, linear, 网址, PassWord);
        netWork_讨论_读取.start();
        swiperefee.setOnRefreshListener(()->{
            netWork_讨论_读取.start();
            swiperefee.setRefreshing(false);
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
    public static void 发布新的评论(Activity activity, LinearLayout linear, List<Post_Data> post_dataList, String time_name, String 网址, String PassWord){
        View view = 添加评论布局(activity, post_dataList);
        NetWork_评论_发表 netWork_讨论_发表 = new NetWork_评论_发表(activity);
        netWork_讨论_发表.传递参数(网址, PassWord, time_name, Fun.获取时间(), able.gson.toJson(post_dataList), view, linear);
        netWork_讨论_发表.start();

    }
    public static View 添加评论布局(Activity activity, List<Post_Data> post_dataList){
        View view = View.inflate(activity, R.layout.discuss_child_layout_view, null);
        TextView name = view.findViewById(R.id.name);
        TextView sign = view.findViewById(R.id.sign);
        TextView text = view.findViewById(R.id.text);
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("Name")){
                name.setText(pd.getText());
            }
            if(pd.getName().equals("Sign")){
                sign.setText(pd.getText());
            }
            if(pd.getName().equals("Text")){
                text.setText(pd.getText());
            }
        }
        return view;
    }
}
