package fun.android.federal_square.view;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import fun.android.federal_square.network.后台判断新内容;
import fun.android.federal_square.window.发表文章窗口;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_广场刷新;
import fun.android.federal_square.window.引用列表窗口;
import fun.android.federal_square.window.重新设置域名窗口;

public class View_Square extends View_Main{

    private TextView top_title;
    public ImageView new_icon;
    private SwipeRefreshLayout swipe_layout;
    public ScrollView scrollView;
    private RelativeLayout button_add;
    private ImageView button_url_setting;
    public LinearLayout linear;
    public TextView di_title;

    public int Post_Index = 0;
    private List<后台判断新内容> 后台判断集合 = new ArrayList<>();

    private Handler 滑动监听_handler = new Handler(Looper.getMainLooper());
    private Runnable 滑动监听_scrollEndRunnable = () -> {
        View_Main_Pager v = (View_Main_Pager)able.view_main;
        v.menu_open.setAlpha(0.2f);
    };
    public View_Square(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_square, null);
        top_title = view.findViewById(R.id.top_title);
        new_icon = view.findViewById(R.id.new_icon);
        swipe_layout = view.findViewById(R.id.swiperefee);
        button_add = view.findViewById(R.id.button_add);
        button_url_setting = view.findViewById(R.id.button_url_setting);
        linear = view.findViewById(R.id.linear);
        scrollView = view.findViewById(R.id.scrollView);
        di_title = view.findViewById(R.id.di_title);
        new_icon.setVisibility(View.GONE);

        able.广场空 = new TextView(activity_main);
        able.广场空.setTextColor(Color.rgb(128, 128, 128));
        able.广场空.setTextSize(15);
        able.广场空.setText(" 广场为空 ");
        able.广场空.setPadding(0,10,0,10);
        able.广场空.setTextIsSelectable(true);
        able.广场空.setGravity(Gravity.CENTER);
        able.广场空.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void 事件() {
        super.事件();
        ViewGroup.LayoutParams layoutParams = top_title.getLayoutParams();
        layoutParams.height = able.状态栏高度;
        if(!Fun.是否全屏(activity_main)){
            top_title.setBackgroundColor(Color.rgb(128,128,128));
        }

        top_title.setLayoutParams(layoutParams);
        swipe_layout.setOnRefreshListener(()->{
            if(new_icon.getVisibility() == View.VISIBLE){
                new_icon.setVisibility(View.GONE);
            }
            var netWork_广场刷新 = new NetWork_广场刷新(activity_main);
            netWork_广场刷新.start();
            for(var url_passWord_data : 引用列表窗口.获取引用列表()){
                NetWork_广场刷新 zi_network = new NetWork_广场刷新(activity_main);
                zi_network.传递参数(url_passWord_data.getURL(), url_passWord_data.getPassWord());
                zi_network.start();
            }
            swipe_layout.setRefreshing(false);
        });
        scrollView.setOnScrollChangeListener((V1, V2, scrollY, V4, V5) -> {
            int screenHeight = scrollView.getHeight();
            var childHeight = scrollView.getChildAt(0).getHeight();
            Fun.刷新当前文章(activity_main, linear, scrollView);
            View_Main_Pager v = (View_Main_Pager)able.view_main;
            if(v.menu_list_view.getVisibility() == View.VISIBLE){
                v.menu_open.setAlpha(1.0f);
            }
            滑动监听_handler.removeCallbacks(滑动监听_scrollEndRunnable);
            滑动监听_handler.postDelayed(滑动监听_scrollEndRunnable, 200);
        });
        button_add.setOnClickListener(V->{
            if(!Fun_账号.GetID().isEmpty()){
                var _发表文章窗口 = new 发表文章窗口();
                _发表文章窗口.创建发表文章窗口(activity_main);

            }else{
                Fun.mess(activity_main, "请先登录");
            }

        });
        if(Fun_文件.是否存在(able.app_path + "System_Data/url_setting_boolean.txt")){
            button_url_setting.setVisibility(View.GONE);
        }
        button_url_setting.setOnClickListener(V->{
            new 重新设置域名窗口().启动(activity_main, button_url_setting);
        });
        初始化本地数据();
    }
    @Override
    public void onStart() {
        super.onStart();
        if(able.URL.isEmpty()){
            return;
        }
        启动刷新();
    }

    public void 启动刷新(){
        if(后台判断集合.isEmpty()){
            后台判断集合.add(new 后台判断新内容(activity_main));
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        for(后台判断新内容 s : 后台判断集合){
            s.close();
        }
        后台判断集合.clear();
    }

    @Override
    public void 释放() {
        super.释放();
        Fun_文章.释放所有文章内存(linear,activity_main);
    }
    public void 初始化本地数据(){
        new Thread(()->{
            Fun_文章.释放所有文章内存(linear, activity_main);
            Post_Index=0;
            var url = Fun.获取域名();
            var 所有文章 = Fun_文章.获取广场所有集合();
            var index = Fun.获取广场文章数量();
            if(url.isEmpty()){
                return;
            }
            if(所有文章.isEmpty()){
                activity_main.runOnUiThread(()->{
                    linear.addView(able.广场空);
                });
                return;
            }
            for(int i=0; i< 所有文章.size(); i++){
                if(Fun_账号.GetID().isEmpty()){
                    if(i >= 10){
                        break;
                    }
                }
                if(i >= index){
                    break;
                }
                //读取json判断json格式是否正确
                var txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                if(!Fun.StrBoolJSON(txt)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
                var url_data="";
                for(Post_Data pd : post_data){
                    if(pd.getName().equals("url")){
                        url_data = pd.getText();
                    }
                }
                if(url_data.isEmpty()){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                    continue;
                }
                activity_main.runOnUiThread(() -> {
                    List<Post_Data> finalData = new ArrayList<>(post_data);
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 0);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
            }
            Fun.回到顶部(scrollView, linear, activity_main);

            linear.post(()->{
                if(linear.getChildCount() == 0) {
                    linear.addView(able.广场空);
                }
            });
        }).start();
    }

    public void 上一页(){
        new Thread(()->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法使用");
                return;
            }
            Fun_文章.释放所有文章内存(linear, activity_main);
            var url = Fun.获取域名();
            var 所有文章 = Fun_文章.获取广场所有集合();
            var index = Fun.获取广场文章数量();
            if(url.isEmpty()){
                return;
            }
            for(int i=0;i<index;i++){
                Post_Index--;
            }
            if(Post_Index < 0){
                Post_Index = 0;
            }
            var 遍历数量 = 0;
            for(var i=Post_Index; i<所有文章.size(); i++){
                if(Fun_账号.GetID().isEmpty()){
                    if(遍历数量>=10){
                        break;
                    }
                }
                if(遍历数量 >= index){
                    break;
                }
                var txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                if(!Fun.StrBoolJSON(txt)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    List<Post_Data> finalData = new ArrayList<>(post_data);
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 0);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, activity_main);
            linear.post(()->{
                if(linear.getChildCount() == 0) {
                    linear.addView(able.广场空);
                }
            });
        }).start();
    }

    public void 下一页(){
        new Thread(()->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法使用");
                return;
            }
            Fun_文章.释放所有文章内存(linear, activity_main);
            var url = Fun.获取域名();
            var 所有文章 = Fun_文章.获取广场所有集合();
            var index = Fun.获取广场文章数量();
            if(url.isEmpty()){
                return;
            }
            if(Post_Index >= 所有文章.size()){
                Fun.mess(activity_main, "到底了");
            }
            for(int i=0;i<index;i++){
                Post_Index++;
            }
            var 遍历数量=0;
            for(int i=Post_Index;i<所有文章.size();i++){
                if(Fun_账号.GetID().isEmpty()){
                    if(遍历数量 >= 10){
                        break;
                    }
                }
                if(遍历数量 >= index){
                    break;
                }
                //读取json判断json格式是否正确
                var txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                if(!Fun.StrBoolJSON(txt)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
                activity_main.runOnUiThread(() -> {
                    List<Post_Data> finalData = new ArrayList<>(post_data);
                    View 动态视图 = Fun_文章.Create_Post_View(activity_main, finalData, 0);
                    动态视图.setVisibility(View.INVISIBLE);
                    linear.addView(动态视图);
                });
                遍历数量++;
            }
            Fun.回到顶部(scrollView, linear, activity_main);
            linear.post(()->{
                if(linear.getChildCount() == 0) {
                    linear.addView(able.广场空);
                }
            });
        }).start();
    }
    public void 恢复界面(){
        Fun.刷新当前文章(activity_main, linear, scrollView);
    }
}