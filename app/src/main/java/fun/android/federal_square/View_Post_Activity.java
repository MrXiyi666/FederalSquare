package fun.android.federal_square;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import net.csdn.roundview.RoundImageView;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.fun.TimeUtils;
import fun.android.federal_square.network.NetWork_查看文章_评论发布;
import fun.android.federal_square.view.Video_ImageView;
import fun.android.federal_square.window.打开方式窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.network.NetWork_评论_读取;
import fun.android.federal_square.window.查看视频窗口;
import fun.android.federal_square.window.查看评论窗口;

public class View_Post_Activity extends AppCompatActivity {
    private View top_view;
    private String url_txt, PassWord_txt="";
    private LinearLayout linear;
    private boolean 时间切换 = false;
    private String time = "";
    private String forward="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.argb(0,0,0,0));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setNavigationBarColor(Color.rgb(255,255,255));
        setContentView(R.layout.activity_view_post);
        top_view = findViewById(R.id.top_view);
        linear = findViewById(R.id.linear);
        LinearLayout linear_check = findViewById(R.id.linear_check);
        TextView name_view = findViewById(R.id.name);
        TextView sign_view = findViewById(R.id.sign);
        RoundImageView avatar_img = findViewById(R.id.avatar_img);
        TextView text_time = findViewById(R.id.text_time);
        TextView url_txt_id = findViewById(R.id.url_txt_id);
        AppCompatButton button_ok = findViewById(R.id.button_ok);
        EditText edit_text = findViewById(R.id.edit_text);
        ScrollView scrollView = findViewById(R.id.scrollView);
        if(able.传递数据 == null){
            finish();
        }

        if(able.传递数据.isEmpty()){
            finish();
        }

        ViewGroup.LayoutParams layoutParams = top_view.getLayoutParams();
        layoutParams.height = Fun.获取状态栏高度(this);
        top_view.setLayoutParams(layoutParams);

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
                            .load(post_data.getText())
                            .apply(able.requestOptions)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(avatar_img);
                    break;
                case "text":
                    TextView text1_view = new TextView(this);
                    text1_view.setTextColor(Color.BLACK);
                    text1_view.setTextSize(15);
                    text1_view.setText(post_data.getText());
                    text1_view.setTextIsSelectable(true);
                    linear.addView(text1_view);
                    break;
                case "img":
                    Video_ImageView img = new Video_ImageView(this);
                    img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    img.setBackgroundColor(Color.rgb(242,243,247));
                    String 后缀 = Fun_文件.获取后缀(post_data.getText());
                    img.后缀 = 后缀;
                    Glide.with(this)
                            .load(post_data.getText())
                            .apply(able.requestOptions)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .listener(new RequestListener<>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                                    if (!Fun.图片格式判断(img.后缀)) {
                                        img.setBackgroundColor(Color.BLACK);
                                    } else {
                                        img.setBackgroundColor(Color.TRANSPARENT);
                                    }
                                    img.setPadding(0, 0, 0, 0);
                                    return false;
                                }
                            })
                            .into(img);

                    img.setOnClickListener(V->{
                        if(Fun.图片格式判断(后缀)){
                            查看图片窗口.启动_Dialog(this, post_data.getText());
                        }else if(Fun.视频格式判断(后缀)){
                            查看视频窗口.启动_Dialog(this, post_data.getText());
                        }else{
                            打开方式窗口.启动(this, post_data.getText());
                        }
                    });
                    img.setOnLongClickListener(V->{
                        Glide.with(this)
                                .load(post_data.getText())
                                .apply(able.requestOptions)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }
                                    @Override
                                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                                        if(!Fun.图片格式判断(img.后缀)){
                                            img.setBackgroundColor(Color.BLACK);
                                        }else{
                                            img.setBackgroundColor(Color.TRANSPARENT);
                                        }
                                        img.setPadding(0,0,0, 0);
                                        return false;
                                    }
                                })
                                .into(img);
                        return true;
                    });
                    linear.addView(img);
                    TextView textView = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, Fun.DPToPX(this,10));
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setLayoutParams(params);
                    linear.addView(textView);
                    break;
                case "time":
                    time = post_data.getText();
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

        text_time.setText(TimeUtils.calculateRelativeTime(time));
        text_time.setOnClickListener(V->{
            时间切换 = !时间切换;
            if(时间切换){
                String[] time_shuzu = time.split("_");
                StringBuffer time_data = new StringBuffer();
                for(int i=0;i<time_shuzu.length; i++){
                    if(i < 6){
                        if(!time_data.toString().isEmpty()){
                            time_data.append(":");
                        }
                        time_data.append(time_shuzu[i]);
                    }
                }
                text_time.setText(time_data);
            }else{
                text_time.setText(TimeUtils.calculateRelativeTime(time));
            }
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
                NetWork_查看文章_评论发布 netWork_查看文章_评论发布 = new NetWork_查看文章_评论发布(this);
                netWork_查看文章_评论发布.传递参数(url_txt, PassWord_txt, time, Fun.获取时间(), able.gson.toJson(post_dataList), 查看评论窗口.添加评论布局(this, post_dataList), linear_check);
                netWork_查看文章_评论发布.start();
                edit_text.setText("");
            });
        }
        scrollView.setOnScrollChangeListener((V1, V2, scrollY, V4, V5) -> {
            int scrollViewHeight = scrollView.getHeight();
            int linearCount = linear.getChildCount();

            for (int i = 0; i < linearCount; i++) {
                View childView = linear.getChildAt(i);
                // 计算子视图是否在屏幕范围内
                int childY = (int) childView.getY();
                int childHeight = childView.getHeight();

                boolean isVisible = (childY + childHeight > scrollY) && (childY < scrollY + scrollViewHeight);
                int currentVisibility = childView.getVisibility();

                // 根据计算结果更新可见性
                if (isVisible) {
                    if (currentVisibility != View.VISIBLE) {
                        childView.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (currentVisibility != View.INVISIBLE) {
                        childView.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(int i=0; i<linear.getChildCount(); i++){
            if(linear.getChildAt(i) instanceof Video_ImageView imageView){
                imageView.setImageBitmap(null);
            }
        }
        linear.removeAllViews();
    }

}
