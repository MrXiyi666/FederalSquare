package fun.android.federal_square;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;

public class View_Collectin extends AppCompatActivity {
    private ImageView return_icon;
    private LinearLayout linear;
    private ScrollView scrollView;
    public AppCompatButton button_top, button_up, button_down, button_update;
    public int Post_Index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.WHITE);
        window.setNavigationBarColor(Color.WHITE);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_collection);
        初始化();
        事件();
    }

    public void 初始化(){
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
        return_icon.setOnClickListener(V->{
            finish();
        });

        scrollView.setOnScrollChangeListener((_, _, _, _, _) -> {
            Fun.刷新当前文章(View_Collectin.this, linear, scrollView);
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
        new Thread(()->{
            Post_Index=0;
            var list = Fun_文章.获取所有收藏集合();
            Fun_文章.释放所有文章内存(linear, this);
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
                this.runOnUiThread(()->{
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    var view = Fun_文章.Create_Post_View(this, finalData, 4);
                    view.setVisibility(View.INVISIBLE);
                    linear.addView(view);
                });
            }
            Fun.回到顶部(scrollView, linear, this);
        }).start();


    }
    public void 上一页(){
        new Thread(()->{
            var list = Fun_文章.获取所有收藏集合();
            var index = Fun.获取我的收藏数量();
            for(var i=0;i<index;i++){
                Post_Index--;
            }
            if(Post_Index < 0){
                Post_Index = 0;
            }
            Fun_文章.释放所有文章内存(linear, this);
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
                this.runOnUiThread(()->{
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    var view = Fun_文章.Create_Post_View(this, finalData, 4);
                    view.setVisibility(View.INVISIBLE);
                    linear.addView(view);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, this);
        }).start();


    }
    public void 下一页(){
        new Thread(()->{
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
                this.runOnUiThread(()->{
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    var view = Fun_文章.Create_Post_View(this, finalData, 4);
                    view.setVisibility(View.INVISIBLE);
                    linear.addView(view);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, this);
        }).start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}