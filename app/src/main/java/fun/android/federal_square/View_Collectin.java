package fun.android.federal_square;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
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

public class View_Collectin extends AppCompatActivity {
    private ImageView return_icon;
    private TextView top_title;
    private LinearLayout linear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_collection);
        初始化();
        事件();
    }

    public void 初始化(){
        top_title = findViewById(R.id.top_title);
        return_icon = findViewById(R.id.return_icon);
        linear = findViewById(R.id.linear);
        初始化数据();
    }

    public void 事件(){
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        return_icon.setOnClickListener(V->{
            finish();
        });
    }

    public void 初始化数据(){
        new Thread(()->{
            List<String> list = Fun_文章.获取收藏集合();
            able.handler.post(()->{
                linear.removeAllViews();
            });
            for(int i=0;i<list.size();i++){
                String str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                    continue;
                }
                able.handler.post(()->{
                    linear.addView(Fun_文章.Create_Post_View(this, post_data, 4));
                });
            }
        }).start();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(查看图片窗口.photoView != null && 查看图片窗口.photoView.getVisibility() == View.VISIBLE){
                查看图片窗口.photoView.setVisibility(View.GONE);
                return false;
            }

        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
