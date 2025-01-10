package fun.android.federal_square;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.csdn.roundview.RoundImageView;

import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看评论窗口;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_添加收藏;


public class View_Essay extends AppCompatActivity {
    private ImageView return_icon;
    private LinearLayout linear;
    private TextView top_title;
    private ScrollView scrollView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_essay);
        初始化();
        事件();
    }

    public void 初始化(){
        return_icon = findViewById(R.id.return_icon);
        linear = findViewById(R.id.linear);
        top_title = findViewById(R.id.top_title);
        scrollView = findViewById(R.id.scrollView);
        初始化数据();

    }

    public void 事件(){
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        return_icon.setOnClickListener(V->{
            finish();
        });
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Rect scrollBounds = new Rect();
            scrollView.getHitRect(scrollBounds);
            for(int i=0;i<linear.getChildCount();i++){
                View view = linear.getChildAt(i);
                if (view.getLocalVisibleRect(scrollBounds)) {
                    view.setVisibility(View.VISIBLE);
                    // 子控件至少有一个像素在可视范围内
                    if (scrollBounds.bottom >= (view.getHeight() / 2)) {
                        // 子控件的可见区域是否超过了50%

                    }
                } else {
                    view.setVisibility(View.INVISIBLE);
                    // 子控件完全不在可视范围内
                }
            }
        });
    }
    public void 初始化数据(){
        new Thread(()->{
            List<String> list = Fun_文章.获取我的文章集合();
            able.handler.post(()->{
                linear.removeAllViews();
            });
            for(int i=0;i<list.size();i++){
                String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i));
                List<Post_Data> post_data;
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                able.handler.post(()->{
                    View view = Fun_文章.Create_Post_View(this, post_data, 3);
                    if(linear.getChildCount() >= 10){
                        view.setVisibility(View.INVISIBLE);
                    }
                    linear.addView(view);
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}