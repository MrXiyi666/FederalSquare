package fun.android.federal_square.view;

import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
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
import fun.android.federal_square.data.URL_PassWord_Data;
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
    private SwipeRefreshLayout swiperefee;
    public ScrollView scrollView;
    private RelativeLayout button_add, button_url_setting;
    public LinearLayout linear;
    public 发表文章窗口 _发表文章窗口;
    private boolean b_time_update = true;
    private boolean scrollView_Down_Y = false, scrollView_Up_Y;
    private Thread time_thread=null;
    public int view_id;
    public List<String> 所有文章 = new ArrayList<>();
    public int 第一个文章编号 = 0;
    public TextView di_title;
    public View_Square(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();

        view = View.inflate(activity_main, R.layout.view_square, null);
        top_title = view.findViewById(R.id.top_title);
        new_icon = view.findViewById(R.id.new_icon);
        swiperefee = view.findViewById(R.id.swiperefee);
        button_add = view.findViewById(R.id.button_add);
        button_url_setting = view.findViewById(R.id.button_url_setting);
        linear = view.findViewById(R.id.linear);
        scrollView = view.findViewById(R.id.scrollView);
        di_title = view.findViewById(R.id.di_title);
        初始化本地数据();
        new_icon.setVisibility(View.GONE);

    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        swiperefee.setOnRefreshListener(()->{
            if(able.view_square.new_icon.getVisibility() == View.VISIBLE){
                able.view_square.new_icon.setVisibility(View.GONE);
            }
            NetWork_广场刷新 netWork_广场刷新 = new NetWork_广场刷新(activity_main);
            netWork_广场刷新.start();
            for(URL_PassWord_Data url_passWord_data : 引用列表窗口.获取引用列表()){
                NetWork_广场刷新 zi_network = new NetWork_广场刷新(activity_main);
                zi_network.传递参数(url_passWord_data.getURL(), url_passWord_data.getPassWord());
                zi_network.start();
            }
            swiperefee.setRefreshing(false);
        });

        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Rect scrollBounds = new Rect();
            scrollView.getHitRect(scrollBounds);
            int childHeight = scrollView.getChildAt(0).getHeight();
            int scrollViewHeight = scrollView.getHeight();
            if(scrollY == 0){
                scrollView_Up_Y = true;
            }else{
                scrollView_Up_Y = false;
            }
            if(scrollY + scrollViewHeight >= childHeight){
                scrollView_Down_Y = true;
            }else{
                scrollView_Down_Y = false;
            }
            for(int i=0;i<linear.getChildCount();i++){
                Post_View view = (Post_View)linear.getChildAt(i);
                if (view.getLocalVisibleRect(scrollBounds)) {
                    view.setVisibility(View.VISIBLE);
                    view_id = i;
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


        button_add.setOnClickListener(V->{
            if(!Fun_账号.GetID().isEmpty()){
                _发表文章窗口 = new 发表文章窗口();
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
        b_time_update = true;
        if(time_thread != null){
            time_thread.interrupt();
            time_thread = null;
        }
        int index;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Time_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        } else {
            index = 5000;
        }
        time_thread = new Thread(()->{
            NetWork_多少秒获取广场数据 fun_多少秒获取广场数据 = new NetWork_多少秒获取广场数据(activity_main);
            while (b_time_update){
                fun_多少秒获取广场数据.start();
                try {
                    Thread.sleep(index);
                }catch (Exception e){
                }
            }

        });
        time_thread.start();
    }
    @Override
    public void onStop() {
        super.onStop();
        b_time_update = false;
        if(time_thread!=null){
            time_thread.interrupt();
            time_thread = null;
        }
    }

    @Override
    public void 释放() {
        super.释放();
    }
    public void 初始化本地数据(){
        scrollView.fullScroll(View.FOCUS_UP);
        linear.removeAllViews();
        第一个文章编号=0;
        String url = Fun.获取域名();
        所有文章 = Fun_文章.获取广场所有集合();
        int index = Fun.获取广场文章数量();
        int 遍历数量 = 0;
        if(url.isEmpty()){
            return;
        }
        for(int i=0;i<所有文章.size();i++){
            if(Fun_账号.GetID().isEmpty()){
                if(遍历数量 >= 10){
                    return;
                }
            }
            if(遍历数量 >= index){
                return;
            }
            //读取json判断json格式是否正确
            String txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
            if(!Fun.StrBoolJSON(txt)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
            //获取文章内的域名
            String url_data="";
            for(Post_Data pd : post_data){
                if(pd.getName().equals("url")){
                    url_data = pd.getText();
                }
            }
            if(url_data.isEmpty()){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
            }
            Post_View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
            遍历数量++;
        }
    }

    public void 上一页(){
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法使用");
            return;
        }
        scrollView.fullScroll(View.FOCUS_UP);
        linear.removeAllViews();
        String url = Fun.获取域名();
        所有文章 = Fun_文章.获取广场所有集合();
        int index = Fun.获取广场文章数量();
        if(url.isEmpty()){
            return;
        }
        for(int i=0;i<index;i++){
            第一个文章编号--;
        }
        if(第一个文章编号 < 0){
            第一个文章编号 = 0;
        }

        int 遍历数量 = 0;
        for(int i=第一个文章编号; i<所有文章.size(); i++){
            if(Fun_账号.GetID().isEmpty()){
                if(遍历数量>=10){
                    return;
                }
            }
            if(遍历数量 >= index){
                return;
            }
            String txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
            if(!Fun.StrBoolJSON(txt)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
            Post_View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
            遍历数量++;
        }
    }

    public void 下一页(){
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法使用");
            return;
        }
        scrollView.fullScroll(View.FOCUS_UP);
        linear.removeAllViews();
        String url = Fun.获取域名();
        所有文章 = Fun_文章.获取广场所有集合();
        int index = Fun.获取广场文章数量();
        if(url.isEmpty()){
            return;
        }
        for(int i=0;i<index;i++){
            第一个文章编号++;
        }
        int 遍历数量=0;
        for(int i=第一个文章编号;i<所有文章.size();i++){
            if(Fun_账号.GetID().isEmpty()){
                if(遍历数量 >= 10){
                    return;
                }
            }
            if(遍历数量 >= index){
                return;
            }
            //读取json判断json格式是否正确
            String txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + 所有文章.get(i));
            if(!Fun.StrBoolJSON(txt)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + 所有文章.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
            Post_View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
            遍历数量++;
        }
    }
    public void 恢复界面(){
        int ii=0;
        int 当前编号;
        if(view_id > 10){
            当前编号 = view_id - 10;
        }else{
            当前编号 = 0;
        }
        for(int i=0;i<linear.getChildCount();i++){
            if(ii>=20){
                return;
            }
            if(当前编号 >= 0 && 当前编号 < linear.getChildCount()){
                View view = linear.getChildAt(当前编号);
                view.setVisibility(View.VISIBLE);
                ii++;
                当前编号++;
            }
        }
    }

    public void 修改底部空间(){
        ViewGroup.LayoutParams params = di_title.getLayoutParams();
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