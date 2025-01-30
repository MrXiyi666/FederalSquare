package fun.android.federal_square;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.view.Post_View;

public class View_Collectin extends AppCompatActivity {
    private ImageView return_icon;
    private TextView top_title;
    private LinearLayout linear;
    private ScrollView scrollView;
    public AppCompatButton button_top, button_up, button_down, button_update;
    public int Post_Index = 0;
    private boolean scrollView_update = false;
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
        scrollView = findViewById(R.id.scrollView);
        button_top = findViewById(R.id.button_top);
        button_up = findViewById(R.id.button_up);
        button_down = findViewById(R.id.button_down);
        button_update = findViewById(R.id.button_update);
        初始化数据();
    }

    public void 事件(){
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        return_icon.setOnClickListener(V->{
            finish();
        });

        scrollView.setOnScrollChangeListener((_, _, scrollY, _, _) -> {
            int screenHeight = scrollView.getHeight();
            if(scrollY == 0){
                scrollView_update = true;
            }else {
                scrollView_update = false;
            }
            for (int i = 0; i < linear.getChildCount(); i++) {
                Post_View view = (Post_View) linear.getChildAt(i);
                int childTop = view.getTop();
                int childBottom = view.getBottom();
                if (childTop < scrollY + screenHeight && childBottom > scrollY) {
                    if(view.getVisibility() == View.INVISIBLE){
                        view.setVisibility(View.VISIBLE);
                    }
                } else {
                    if(view.getVisibility() == View.VISIBLE){
                        view.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        button_top.setOnClickListener(V->{
            scrollView.smoothScrollTo(0,0);
        });
        button_up.setOnClickListener(V->{
            上一页();
        });
        button_down.setOnClickListener(V->{
            下一页();
        });
        button_update.setOnClickListener(V->{
            初始化数据();
        });
    }
    public void 初始化数据(){
        Post_Index=0;
        var list = Fun_文章.获取所有收藏集合();
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        var index = Fun.获取我的收藏数量();
        for(var i=0; i<list.size(); i++){
            if(i >= index){
                break;
            }
            var str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            var view = Fun_文章.Create_Post_View(this, post_data, 4);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.post(()->{
                    scrollView.scrollTo(0, 1);
                });
                Fun.回到顶部(scrollView);
            }
        });
    }
    public void 上一页(){
        var list = Fun_文章.获取所有收藏集合();
        var index = Fun.获取我的收藏数量();
        for(var i=0;i<index;i++){
            Post_Index--;
        }
        if(Post_Index < 0){
            Post_Index = 0;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        var 遍历数量 = 0;
        for(var i=Post_Index; i<list.size(); i++){
            if(遍历数量 >= index){
                break;
            }
            var str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            var view = Fun_文章.Create_Post_View(this, post_data, 4);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
            遍历数量++;
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.post(()->{
                    scrollView.scrollTo(0, 1);
                });
                Fun.回到顶部(scrollView);
            }
        });
    }
    public void 下一页(){
        if(linear.getChildCount() == 0){
            Fun.mess(this, "到底了");
            return;
        }
        var list = Fun_文章.获取所有收藏集合();
        var index = Fun.获取我的收藏数量();
        for(var i=0;i<index;i++){
            Post_Index++;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        var 遍历数量 = 0;
        for(var i=Post_Index; i<list.size(); i++){
            if(遍历数量 >= index){
                break;
            }
            var str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            var view = Fun_文章.Create_Post_View(this, post_data, 4);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
            遍历数量++;
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.post(()->{
                    scrollView.scrollTo(0, 1);
                });
                Fun.回到顶部(scrollView);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}