package fun.android.federal_square.view;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonSyntaxException;
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
    private TextView top_title;
    private SwipeRefreshLayout swiperefee;
    public LinearLayout linear;
    public ScrollView scrollView;
    private int view_id=0;
    public int 第一个文章编号 = 0;
    public View_Hot(MainActivity activity) {
        super(activity);

    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_hot, null);
        top_title = view.findViewById(R.id.top_title);
        swiperefee = view.findViewById(R.id.swiperefee);
        scrollView = view.findViewById(R.id.scrollView);
        linear = view.findViewById(R.id.linear);
        初始化数据();
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        swiperefee.setOnRefreshListener(()->{
            NetWork_读取热门 netWork_读取_热门 = new NetWork_读取热门(activity_main);
            netWork_读取_热门.start();
            swiperefee.setRefreshing(false);
        });
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Rect scrollBounds = new Rect();
            scrollView.getHitRect(scrollBounds);
            for(int i=0;i<linear.getChildCount();i++){
                View view = linear.getChildAt(i);
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
        第一个文章编号=0;
        linear.removeAllViews();
        List<String> list = Fun_文章.获取热门集合();
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法查看");
            return;
        }
        int index;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Hot_Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }else{
            index = 10;
        }
        int 遍历数量 = 0;
        for(int i=0;i<list.size();i++){
            if(遍历数量 >= index){
                return;
            }
            String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
            List<Post_Data> post_data;
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                continue;
            }
            post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
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
        scrollView.fullScroll(View.FOCUS_UP);
        linear.removeAllViews();
        List<String> list = Fun_文章.获取热门集合();
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法查看");
            return;
        }
        int index;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Hot_Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }else{
            index = 10;
        }
        int 遍历数量 = 0;
        for(int i=0;i<index;i++){
            第一个文章编号--;
        }
        if(第一个文章编号 < 0){
            第一个文章编号 = 0;
        }
        for(int i=第一个文章编号;i<list.size();i++){
            if(遍历数量 >= index){
                return;
            }
            String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
            List<Post_Data> post_data;
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                continue;
            }
            post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
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
        scrollView.fullScroll(View.FOCUS_UP);
        linear.removeAllViews();
        List<String> list = Fun_文章.获取热门集合();
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity_main, "没有登陆 无法查看");
            return;
        }
        int index;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Hot_Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }else{
            index = 10;
        }
        int 遍历数量 = 0;
        for(int i=0;i<index;i++){
            第一个文章编号++;
        }
        for(int i=第一个文章编号;i<list.size();i++){
            if(遍历数量 >= index){
                return;
            }
            String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
            List<Post_Data> post_data;
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i) + ".json");
                continue;
            }
            post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            View view = Fun_文章.Create_Post_View(activity_main, post_data, 0);
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
}
