package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Essay;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_我的_文章_刷新;

public class View_Home_Essay extends View_Main{
    public LinearLayout linear;
    private SwipeRefreshLayout swiperefee;
    private AppCompatButton button_loading;
    public View_Home_Essay(Activity activity){
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_essay, null);
        linear = view.findViewById(R.id.linear);
        swiperefee = view.findViewById(R.id.swiperefee);
        button_loading = view.findViewById(R.id.button_loading);
        初始化数据();
    }

    @Override
    public void 事件() {
        super.事件();

        swiperefee.setOnRefreshListener(()->{
            NetWork_我的_文章_刷新 netWork_我的_文章刷新 = new NetWork_我的_文章_刷新(activity_main);
            netWork_我的_文章刷新.传递参数(View_Home_Essay.this);
            netWork_我的_文章刷新.start();
            swiperefee.setRefreshing(false);
        });
        button_loading.setOnClickListener(V->{
            Intent intent = new Intent(activity_main, View_Essay.class);
            activity_main.startActivity(intent);
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
        List<String> list = Fun_文章.获取我的文章集合();
        linear.post(()->{
            linear.removeAllViews();button_loading.setVisibility(View.VISIBLE);
        });
        int index=50;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Home_Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }
        for(int i=0;i<index;i++){
            if(i >= list.size()){
                return;
            }
            try {
                String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i));
                if(str.isEmpty()){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                linear.post(()->{
                    linear.addView(Fun_文章.创建我的文章(activity_main, post_data, View_Home_Essay.this));
                });
            }catch (Exception e){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
            }
        }
        linear.post(()->{
            if(list.size() > linear.getChildCount()){
                button_loading.setVisibility(View.VISIBLE);
            }
        });

    }
}