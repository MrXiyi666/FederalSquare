package fun.android.federal_square;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import net.csdn.roundview.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_查看文章_评论发布;
import fun.android.federal_square.view.Video_ImageView;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.network.NetWork_评论_读取;
import fun.android.federal_square.window.查看视频窗口;
import fun.android.federal_square.window.查看评论窗口;

public class View_Post_Activity extends AppCompatActivity {
    private TextView top_title;
    private String url_txt, PassWord_txt="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.WHITE);
        window.setNavigationBarColor(Color.WHITE);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_post);
        ImageView return_icon = findViewById(R.id.return_icon);
        top_title = findViewById(R.id.top_title);
        LinearLayout linear = findViewById(R.id.linear);
        LinearLayout linear_check = findViewById(R.id.linear_check);
        TextView name_view = findViewById(R.id.name);
        TextView sign_view = findViewById(R.id.sign);
        RoundImageView avatar_img = findViewById(R.id.avatar_img);
        TextView text_time = findViewById(R.id.text_time);
        TextView url_txt_id = findViewById(R.id.url_txt_id);
        AppCompatButton button_ok = findViewById(R.id.button_ok);
        EditText edit_text = findViewById(R.id.edit_text);


        top_title.post(()->{
            top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        });
        return_icon.setOnClickListener(V->{
            finish();
        });
        if(able.传递数据 == null){
            finish();
        }
        if(able.传递数据.isEmpty()){
            finish();
        }
        String time = "";
        String forward="";
        for(Post_Data post_data : able.传递数据){
            switch(post_data.getName()){
                case "name":
                    name_view.setText(post_data.getText());
                    break;
                case "sign":
                    sign_view.setText(post_data.getText());
                    break;
                case "avatar":
                    Glide.with(this)
                            .asBitmap()
                            .load(post_data.getText())
                            .into(avatar_img);
                    break;
                case "text":
                    TextView text1_view = new TextView(this);
                    text1_view.setTextColor(Color.BLACK);
                    text1_view.setTextSize(15);
                    text1_view.setText(post_data.getText());
                    text1_view.setTextIsSelectable(true);
                    text1_view.setPadding(0,0,0, Fun.DPToPX(this, 10));
                    linear.addView(text1_view);
                    break;
                case "img":
                    Video_ImageView img = new Video_ImageView(this);
                    Glide.with(this)
                            .asBitmap()
                            .load(post_data.getText())
                            .apply(new RequestOptions()
                            .error(R.drawable.glide_shibai)
                            .fallback(R.drawable.glide_duqushibai))
                            .into(img);
                    String 后缀 = Fun_文件.获取后缀(post_data.getText());
                    if(Fun.视频格式判断(后缀)){
                        img.for_video = true;
                    }else{
                        img.for_video = false;
                    }
                    img.setOnClickListener(V->{

                        if(Fun.图片格式判断(后缀)){
                            查看图片窗口.启动_Dialog(this, post_data.getText());
                        }
                        if(Fun.视频格式判断(后缀)){
                            查看视频窗口.启动_Dialog(this, post_data.getText());
                        }
                    });
                    img.setPadding(0,0,0,Fun.DPToPX(this, 10));
                    linear.addView(img);
                    break;
                case "time":
                    time = post_data.getText();
                    String[] time_shuzu = post_data.getText().split("_");
                    text_time.setText(time_shuzu[0] + ":" + time_shuzu[1] + ":" + time_shuzu[2] + ":" + time_shuzu[3] + ":" + time_shuzu[4] + ":" + time_shuzu[5]);
                    break;
                case "url":
                    url_txt = post_data.getText();
                    break;
                case "password":
                    PassWord_txt = post_data.getText();
                    break;
                case "forward":
                    forward = post_data.getText();
                    break;
            }
        }
        url_txt_id.setText(url_txt);
        if(!forward.isEmpty()){
            url_txt_id.setText(url_txt + " - 转发");
        }
        url_txt_id.setVisibility(View.VISIBLE);

        if(!time.isEmpty() && !url_txt.isEmpty()){
            NetWork_评论_读取 netWork_讨论_读取 = new NetWork_评论_读取(this);
            netWork_讨论_读取.传递参数(time, linear_check, url_txt, PassWord_txt);
            netWork_讨论_读取.start();
        }

        if(!Fun_账号.GetID().isEmpty()){
            String finalTime = time;
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
                NetWork_查看文章_评论发布 netWork_查看文章_评论发布 = new NetWork_查看文章_评论发布(this);
                netWork_查看文章_评论发布.传递参数(url_txt, PassWord_txt, finalTime, Fun.获取时间(), able.gson.toJson(post_dataList), 查看评论窗口.添加评论布局(this, post_dataList), linear_check);
                netWork_查看文章_评论发布.start();
                edit_text.setText("");
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
