package fun.android.federal_square.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_贴子;
import fun.android.federal_square.network.NetWork_我的_文章_刷新;

public class View_Home_Article extends View_Main{
    public LinearLayout linear;
    private SwipeRefreshLayout swiperefee;
    public View_Home_Article(Activity activity){
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_article, null);
        linear = view.findViewById(R.id.linear);
        swiperefee = view.findViewById(R.id.swiperefee);
        初始化数据();
    }

    @Override
    public void 事件() {
        super.事件();

        swiperefee.setOnRefreshListener(()->{
            NetWork_我的_文章_刷新 netWork_我的_文章刷新 = new NetWork_我的_文章_刷新(activity_main);
            netWork_我的_文章刷新.传递参数(View_Home_Article.this);
            netWork_我的_文章刷新.start();
            swiperefee.setRefreshing(false);
        });
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
    }

    public void 初始化数据(){
        List<String> list = Fun_贴子.获取文章集合();
        able.handler.post(()->{
            linear.removeAllViews();
        });

        int i=0;
        for(String name : list){
            if(i>=100){
                return;
            }
            try {
                String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + name);
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                able.handler.post(()->{
                    linear.addView(Fun_贴子.创建文章贴子(activity_main, post_data, View_Home_Article.this));
                });
                i++;
            }catch (Exception e){

            }

        }
    }
}