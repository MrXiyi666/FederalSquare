package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
            var intent = new Intent(activity_main, View_Essay.class);
            activity_main.startActivity(intent);
        });



        scrollView.setOnScrollChangeListener((_, _, scrollY, _, _) -> {
            int screenHeight = scrollView.getHeight();
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
        var list = Fun_文章.获取我的文章集合();
        Fun_文章.释放所有文章内存(linear);
        linear.removeAllViews();
        button_loading.setVisibility(View.GONE);
        if(list.isEmpty()){
            var params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            var textView = new TextView(activity_main);
            textView.setTextColor(Color.rgb(128, 128, 128));
            textView.setTextSize(15);
            textView.setText("文章为空");
            textView.setTextIsSelectable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            linear.addView(textView);
            return;
        }
        for(int i=0; i< list.size(); i++){
            var str = Fun_文件.读取文件(able.app_path + "Square_Data/" + list.get(i));
            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + list.get(i));
                Fun_文件.删除文件(able.app_path + "Account/Data/" + list.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            var view = Fun_文章.Create_Post_View(activity_main, post_data, 1);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
        }
        if(linear.getChildCount() >= 10){
            button_loading.setVisibility(View.VISIBLE);
        }
        linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.post(()->{
                    scrollView.scrollTo(0, 1);
                });
                Fun.回到顶部(scrollView);
            }
        });
    }
}