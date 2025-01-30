package fun.android.federal_square.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
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
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_读取热门;

public class View_Hot extends View_Main{
    public TextView top_title, di_title;
    private SwipeRefreshLayout swipe_layout;
    public LinearLayout linear;
    public ScrollView scrollView;
    private boolean scrollView_Di = false;
    public int Post_Index = 0;

    public int scrollView_Y=0;
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
        初始化数据();
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        swipe_layout.setOnRefreshListener(()->{
            swipe_layout.setRefreshing(false);
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity_main, "没有登陆 无法刷新");
                return;
            }
            var netWork_读取_热门 = new NetWork_读取热门(activity_main);
            netWork_读取_热门.start();
        });

        scrollView.setOnScrollChangeListener((_, _, scrollY, _, _) -> {
            scrollView_Y = scrollY;
            var screenHeight = scrollView.getHeight();
            var childHeight = scrollView.getChildAt(0).getHeight();
            if(scrollY + screenHeight >= childHeight){
                scrollView_Di = true;
            }else{
                scrollView_Di = false;
            }
            for (int i = 0; i < linear.getChildCount(); i++) {
                Post_View view = (Post_View) linear.getChildAt(i);
                int childTop = view.getTop();
                int childBottom = view.getBottom();
                if (childTop < scrollY + screenHeight && childBottom > scrollY) {
                    if(view.getVisibility() == View.INVISIBLE){
                        view.setVisibility(View.VISIBLE);
                    }
                } else {
                    if(view.getVisibility() == View.VISIBLE){
                        view.setVisibility(View.INVISIBLE);
                    }
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
        Fun_文章.释放所有文章内存(linear);
    }
    public void 初始化数据(){
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        Post_Index=0;
        var list = Fun_文章.获取热门集合();
        var index = Fun.获取热门数量();
        if(list.isEmpty()){
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            var textView = new TextView(activity_main);
            textView.setTextColor(Color.rgb(128, 128, 128));
            textView.setTextSize(15);
            textView.setText("头条为空");
            textView.setTextIsSelectable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            linear.addView(textView);
            return;
        }
        if(Fun_账号.GetID().isEmpty()){
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            var textView = new TextView(activity_main);
            textView.setTextColor(Color.rgb(128, 128, 128));
            textView.setTextSize(15);
            textView.setText("没有登录 无法查看");
            textView.setTextIsSelectable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            linear.addView(textView);
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 1111);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
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
            Fun.mess(activity_main, "没有登陆 无法查看");
            return;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 1111);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
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
            Fun.mess(activity_main, "没有登陆 无法查看");
            return;
        }
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
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
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 1111);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
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
        scrollView.post(()->{
            scrollView.scrollTo(0, scrollView_Y);
        });
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
        if(scrollView_Di){
            scrollView.post(()->{
                var childHeight = scrollView.getChildAt(0).getHeight();
                var scrollViewHeight = scrollView.getHeight();
                scrollView.smoothScrollTo(0, childHeight-scrollViewHeight);
            });
        }
    }
}
