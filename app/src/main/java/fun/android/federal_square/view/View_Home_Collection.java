package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_我的_收藏_刷新;

public class View_Home_Collection extends View_Main{
    private SwipeRefreshLayout swiperefee;
    public LinearLayout linear;
    private AppCompatButton button_loading;
    private ScrollView scrollView;
    public View_Home_Collection(Activity activity) {
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_collection, null);
        swiperefee = view.findViewById(R.id.swiperefee);
        scrollView = view.findViewById(R.id.scrollView);
        linear = view.findViewById(R.id.linear);
        button_loading = view.findViewById(R.id.button_loading);
    }
    @Override
    public void 事件() {
        super.事件();
        button_loading.setVisibility(View.GONE);
        swiperefee.setOnRefreshListener(()->{
            var netWork_我的_收藏刷新 = new NetWork_我的_收藏_刷新(activity_main);
            netWork_我的_收藏刷新.start();
            swiperefee.setRefreshing(false);
        });

        button_loading.setOnClickListener(V->{
            var intent = new Intent(activity_main, View_Collectin.class);
            activity_main.startActivity(intent);
        });

        scrollView.setOnScrollChangeListener((_, _, _, _, _) -> {
            Fun.刷新当前文章(activity_main, linear, scrollView);
        });
        初始化收藏();
    }

    public void 初始化收藏(){
        new Thread(()->{
            var list = Fun_文章.获取我的收藏集合();
            Fun_文章.释放所有文章内存(linear, activity_main);
            if(list.isEmpty()){
                activity_main.runOnUiThread(()->{
                    button_loading.setVisibility(View.GONE);
                    var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    var textView = new TextView(activity_main);
                    textView.setTextColor(Color.rgb(128, 128, 128));
                    textView.setTextSize(15);
                    textView.setText("收藏为空");
                    textView.setTextIsSelectable(true);
                    textView.setGravity(Gravity.CENTER);
                    textView.setLayoutParams(params);
                    linear.addView(textView);
                });
                return;
            }
            for(var i=0; i<list.size(); i++){
                var str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    // 将数据传递到主线程创建视图
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 2);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                    if(linear.getChildCount() >= 10){
                        button_loading.setVisibility(View.VISIBLE);
                    }
                });
            }
            Fun.回到顶部(scrollView, linear, activity_main);
        }).start();
    }


    @Override
    public void 释放() {
        super.释放();
        Fun_文章.释放所有文章内存(linear, activity_main);
    }

    public void 恢复界面(){
        Fun.刷新当前文章(activity_main, linear, scrollView);
    }
}
