package fun.android.federal_square.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_读取热门;

public class View_Hot extends View_Main{
    public TextView top_title, di_title;
    private SwipeRefreshLayout swipe_layout;
    public LinearLayout linear;
    public ScrollView scrollView;
    private boolean scrollView_Down = false;
    public int Post_Index = 0;

    public View_Hot(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_hot, null);
        top_title = view.findViewById(R.id.top_title);
        di_title = view.findViewById(R.id.di_title);
        swipe_layout = view.findViewById(R.id.swiperefee);
        scrollView = view.findViewById(R.id.scrollView);
        linear = view.findViewById(R.id.linear);
        able.头条空 = new TextView(activity_main);
        able.头条空.setTextColor(Color.rgb(128, 128, 128));
        able.头条空.setTextSize(15);
        able.头条空.setText("");
        able.头条空.setTextIsSelectable(true);
        able.头条空.setGravity(Gravity.CENTER);
        able.头条空.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void 事件() {
        super.事件();
        ViewGroup.LayoutParams layoutParams = top_title.getLayoutParams();
        layoutParams.height = able.状态栏高度;
        top_title.setLayoutParams(layoutParams);
        swipe_layout.setOnRefreshListener(()->{
            swipe_layout.setRefreshing(false);
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法刷新");
                return;
            }
            var netWork_读取_热门 = new NetWork_读取热门(activity_main);
            netWork_读取_热门.start();
        });

        scrollView.setOnScrollChangeListener((V1, V2, scrollY, V4, V5) -> {
            var screenHeight = scrollView.getHeight();
            var childHeight = scrollView.getChildAt(0).getHeight();
            if(scrollY + screenHeight >= childHeight){
                scrollView_Down = true;
            }else{
                scrollView_Down = false;
            }
            Fun.刷新当前文章(activity_main, linear, scrollView);
        });
        初始化数据();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void 释放() {
        super.释放();
        Fun_文章.释放所有文章内存(linear, activity_main);
    }
    public void 初始化数据(){
        new Thread(()->{
            Fun_文章.释放所有文章内存(linear, activity_main);
            activity_main.runOnUiThread(()->{
                able.头条空.setText("");
            });
            Post_Index=0;
            var list = Fun_文章.获取热门集合();
            var index = Fun.获取热门数量();
            if(Fun_账号.GetID().isEmpty()){
                activity_main.runOnUiThread(()->{
                    able.头条空.setText("没有登陆 无法查看");
                    linear.addView(able.头条空);
                });
                return;
            }
            if(list.isEmpty()){
                activity_main.runOnUiThread(()->{
                    able.头条空.setText("头条为空");
                    linear.addView(able.头条空);
                });
                return;
            }
            for(var i=0;i<list.size();i++){
                if(i >= index){
                    break;
                }
                var str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    // 将数据传递到主线程创建视图
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 111);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
            }
            Fun.回到顶部(scrollView, linear, activity_main);
            scrollView.post(()->{
                scrollView_Down = false;
            });
        }).start();
    }

    public void 上一页(){
        new Thread(()->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法查看");
                return;
            }
            Fun_文章.释放所有文章内存(linear, activity_main);
            var list = Fun_文章.获取热门集合();
            var index = Fun.获取热门数量();
            for(var i=0;i<index;i++){
                Post_Index--;
            }
            if(Post_Index < 0){
                Post_Index = 0;
            }
            var 遍历数量 = 0;
            for(var i=Post_Index;i<list.size();i++){
                if(遍历数量 >= index){
                    break;
                }
                var str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    // 将数据传递到主线程创建视图
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 111);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, activity_main);
            scrollView.post(()->{
                scrollView_Down = false;
            });
            linear.post(()->{
                if(linear.getChildCount() == 0){
                    able.头条空.setText("头条为空");
                    linear.addView(able.头条空);
                }else{
                    able.头条空.setText("");
                }
            });
        }).start();
    }

    public void 下一页(){
        new Thread(()->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法查看");
                return;
            }
            if(able.头条空.getText().toString().equals("头条为空")){
                Fun.mess(activity_main, "到底了");
                return;
            }
            Fun_文章.释放所有文章内存(linear, activity_main);
            var list = Fun_文章.获取热门集合();
            var index = Fun.获取热门数量();
            for(var i=0;i<index;i++){
                Post_Index++;
            }
            var 遍历数量 = 0;
            for(var i=Post_Index;i<list.size();i++){
                if(遍历数量 >= index){
                    break;
                }
                var str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    // 将数据传递到主线程创建视图
                    List<Post_Data> finalData = new ArrayList<>(post_data); // 复制数据确保隔离性
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 111);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, activity_main);
            scrollView.post(()->{
                scrollView_Down = false;
            });
            linear.post(()->{
                if(linear.getChildCount() == 0){
                    able.头条空.setText("头条为空");
                    linear.addView(able.头条空);
                }else{
                    able.头条空.setText("");
                }
            });
        }).start();
    }

    public void 恢复界面(){
        Fun.刷新当前文章(activity_main, linear, scrollView);
    }

}
