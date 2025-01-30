package fun.android.federal_square.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.window.发表文章窗口;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_多少秒获取广场数据;
import fun.android.federal_square.network.NetWork_广场刷新;
import fun.android.federal_square.window.引用列表窗口;
import fun.android.federal_square.window.重新设置域名窗口;

public class View_Square extends View_Main{

    private TextView top_title;
    public ImageView new_icon;
    private SwipeRefreshLayout swipe_layout;
    public ScrollView scrollView;
    private RelativeLayout button_add, button_url_setting;
    public LinearLayout linear;
    public TextView di_title;
    public boolean scrollView_Down_Y = false;
    private Thread time_thread=null;
    public int view_id;
    public int Post_Index = 0;

    public int scrollView_Y=0;

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
        初始化本地数据();
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        swipe_layout.setOnRefreshListener(()->{
            if(able.view_square.new_icon.getVisibility() == View.VISIBLE){
                able.view_square.new_icon.setVisibility(View.GONE);
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
        scrollView.setOnScrollChangeListener((_, _, scrollY, _, _) -> {
            int screenHeight = scrollView.getHeight(); // 获取 ScrollView 的高度
            var childHeight = scrollView.getChildAt(0).getHeight();
            if(scrollY + screenHeight >= childHeight){
                scrollView_Down_Y = true;
            }else{
                scrollView_Down_Y = false;
            }
            for (int i = 0; i < linear.getChildCount(); i++) {
                Post_View view = (Post_View) linear.getChildAt(i); // 获取 LinearLayout 的子视图
                int childTop = view.getTop(); // 获取子视图的顶部位置
                int childBottom = view.getBottom(); // 获取子视图的底部位置
                if (childTop < scrollY + screenHeight && childBottom > scrollY) {
                    if(view.getVisibility() == View.INVISIBLE){
                        view.setVisibility(View.VISIBLE);
                    }
                    view_id = i;
                } else {
                    if(view.getVisibility() == View.VISIBLE){
                        view.setVisibility(View.INVISIBLE);
                    }
                }
            }
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
        if(time_thread != null){
            time_thread.interrupt();
            time_thread = null;
        }
        time_thread = new Thread(()->{
            var fun_多少秒获取广场数据 = new NetWork_多少秒获取广场数据(activity_main);
            while (true){
                fun_多少秒获取广场数据.start();
                try {
                    Thread.sleep(Fun.获取广场计时数量());
                }catch (Exception ignored){
                }
            }

        });
        time_thread.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        if(time_thread!=null){
            time_thread.interrupt();
            time_thread = null;
        }
    }

    @Override
    public void 释放() {
        super.释放();
        Fun_文章.释放所有文章内存(linear);
    }
    public void 初始化本地数据(){
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        Post_Index=0;
        var url = Fun.获取域名();
        var 所有文章 = Fun_文章.获取广场所有集合();
        var index = Fun.获取广场文章数量();
        if(url.isEmpty()){
            return;
        }
        if(所有文章.isEmpty()){
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            var textView = new TextView(activity_main);
            textView.setTextColor(Color.rgb(128, 128, 128));
            textView.setTextSize(15);
            textView.setText("广场为空");
            textView.setTextIsSelectable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            linear.addView(textView);
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            view.setVisibility(View.INVISIBLE);
            linear.addView(view);
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Fun.回到顶部(scrollView);
            }
        });
    }

    public void 上一页(){
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法使用");
            return;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            view.setVisibility(View.INVISIBLE);
            linear.addView(view);
            遍历数量++;
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Fun.回到顶部(scrollView);
            }
        });
    }

    public void 下一页(){
        if(linear.getChildCount() == 0){
            Fun.mess(activity_main, "到底了");
            return;
        }
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法使用");
            return;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        var url = Fun.获取域名();
        var 所有文章 = Fun_文章.获取广场所有集合();
        var index = Fun.获取广场文章数量();
        if(url.isEmpty()){
            return;
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            view.setVisibility(View.INVISIBLE);
            linear.addView(view);
            遍历数量++;
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Fun.回到顶部(scrollView);
            }
        });
    }

    public void 恢复界面(){
        var 遍历数量=0;
        var 当前编号 = 0;
        if(view_id >= 10){
            当前编号 = view_id - 10;
        }
        for(var i=0;i<linear.getChildCount();i++){
            if(遍历数量>=10){
                return;
            }
            var view = linear.getChildAt(当前编号);
            view.setVisibility(View.VISIBLE);
            遍历数量++;
            当前编号++;
        }
    }
    public void 修改底部空间(){
        var params = di_title.getLayoutParams();
        if(activity_main.square_menu.getVisibility() == View.VISIBLE){
            params.height = Fun.DPToPX(activity_main, 95);
            di_title.setLayoutParams(params);
        }else{
            params.height = Fun.DPToPX(activity_main, 35);
            di_title.setLayoutParams(params);
        }
        if(scrollView_Down_Y){
            scrollView.post(()->{
               int childHeight = scrollView.getChildAt(0).getHeight();
               int scrollViewHeight = scrollView.getHeight();
               scrollView.smoothScrollTo(0, childHeight-scrollViewHeight);
            });
        }
    }
}