package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Essay;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_我的_文章_刷新;

public class View_Home_Essay extends View_Main{
    public LinearLayout linear;
    private SwipeRefreshLayout swiperefee;
    private AppCompatButton button_loading;
    private ScrollView scrollView;

    public View_Home_Essay(Activity activity){
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_essay, null);
        linear = view.findViewById(R.id.linear);
        swiperefee = view.findViewById(R.id.swiperefee);
        scrollView = view.findViewById(R.id.scrollView);
        button_loading = view.findViewById(R.id.button_loading);
        初始化数据();
    }

    @Override
    public void 事件() {
        super.事件();

        swiperefee.setOnRefreshListener(()->{
            NetWork_我的_文章_刷新 netWork_我的_文章刷新 = new NetWork_我的_文章_刷新(activity_main);
            netWork_我的_文章刷新.start();
            swiperefee.setRefreshing(false);
        });
        button_loading.setOnClickListener(V->{
            Intent intent = new Intent(activity_main, View_Essay.class);
            activity_main.startActivity(intent);
        });
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Rect scrollBounds = new Rect();
            scrollView.getHitRect(scrollBounds);
            for(int i=0;i<linear.getChildCount();i++){
                View view = linear.getChildAt(i);
                if (view.getLocalVisibleRect(scrollBounds)) {
                    view.setVisibility(View.VISIBLE);
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
        new Thread(()->{
            List<String> list = Fun_文章.获取我的文章集合();
            able.handler.post(()->{
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
                String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i));
                List<Post_Data> post_data;
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                    continue;
                }
                post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                able.handler.post(()->{
                    View view = Fun_文章.Create_Post_View(activity_main, post_data, 1);
                    if(linear.getChildCount() >= 50){
                        view.setVisibility(View.INVISIBLE);
                    }
                    linear.addView(view);
                });
            }
            able.handler.post(()->{
                if(list.size() > linear.getChildCount()){
                    button_loading.setVisibility(View.VISIBLE);
                }
            });
            able.handler.post(()->{
                scrollView.scrollTo(0, 0);
            });
        }).start();
    }
}