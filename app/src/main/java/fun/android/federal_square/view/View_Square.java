package fun.android.federal_square.view;

import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import fun.android.federal_square.data.URL_PassWord_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_发贴;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.fun.Fun_贴子;
import fun.android.federal_square.network.NetWork_多少秒获取广场数据;
import fun.android.federal_square.network.NetWork_广场刷新;
import fun.android.federal_square.window.引用列表窗口;
import fun.android.federal_square.window.重新设置域名窗口;

public class View_Square extends View_Main{
    private TextView top_title;
    public ImageView new_icon;
    private SwipeRefreshLayout swiperefee;
    private RelativeLayout button_add, button_url_setting;
    public LinearLayout linear;
    public Fun_发贴 fun_发贴;
    private boolean b_time_update = true;
    private Thread time_thread=null;
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
        初始化本地数据();
        new_icon.setVisibility(View.GONE);
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        swiperefee.setOnRefreshListener(()->{
            NetWork_广场刷新 netWork_广场刷新 = new NetWork_广场刷新(activity_main);
            netWork_广场刷新.传递参数(able.URL_Name, able.Read_PassWord);
            netWork_广场刷新.start();
            for(URL_PassWord_Data url_passWord_data : 引用列表窗口.获取引用列表()){
                NetWork_广场刷新 zi_network = new NetWork_广场刷新(activity_main);
                zi_network.传递参数(url_passWord_data.getURL(), url_passWord_data.getPassWord());
                zi_network.start();
            }

            swiperefee.setRefreshing(false);
        });
        button_add.setOnClickListener(V->{
            if(!Fun_账号.GetID().isEmpty()){
                fun_发贴 = new Fun_发贴();
                fun_发贴.创建发贴窗口(activity_main);
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
        if(able.URL_Name.isEmpty()){
            return;
        }
        b_time_update = true;
        if(time_thread == null){
            time_thread = new Thread(()->{
                NetWork_多少秒获取广场数据 fun_多少秒获取广场数据 = new NetWork_多少秒获取广场数据(activity_main);
                while (b_time_update){
                    fun_多少秒获取广场数据.start();
                    try {
                        Thread.sleep(able.square_time_index);
                    }catch (Exception e){
                    }
                }

            });

        }
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
        List<String> list = Fun_贴子.获取广场集合();
        linear.post(()->{
            linear.removeAllViews();
        });
        int index=50;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }
        for(int i=0;i<index;i++){
            if(i >= list.size()){
                return;
            }
            try {
                String txt = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i));
                if(txt.isEmpty()){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(txt, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                linear.post(()->{
                    linear.addView(Fun_贴子.创建贴子(activity_main, post_data));
                });
            }catch (Exception e){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
            }
        }
    }
}
