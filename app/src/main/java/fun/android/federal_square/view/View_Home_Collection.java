package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_我的_收藏_刷新;

public class View_Home_Collection extends View_Main{
    private SwipeRefreshLayout swiperefee;
    public LinearLayout linear;
    private AppCompatButton button_loading;
    private ScrollView scrollView;
    private Handler handler;
    public View_Home_Collection(Activity activity) {
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        handler = new Handler();
        view = View.inflate(activity_main, R.layout.view_home_collection, null);
        swiperefee = view.findViewById(R.id.swiperefee);
        scrollView = view.findViewById(R.id.scrollView);
        linear = view.findViewById(R.id.linear);
        button_loading = view.findViewById(R.id.button_loading);
        初始化收藏();
    }
    @Override
    public void 事件() {
        super.事件();

        swiperefee.setOnRefreshListener(()->{
            NetWork_我的_收藏_刷新 netWork_我的_收藏刷新 = new NetWork_我的_收藏_刷新(activity_main);
            netWork_我的_收藏刷新.start();
            swiperefee.setRefreshing(false);
        });

        button_loading.setOnClickListener(V->{
            Intent intent = new Intent(activity_main, View_Collectin.class);
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

    public void 初始化收藏(){
        List<String> list = Fun_文章.获取我的收藏集合();
        linear.removeAllViews();
        button_loading.setVisibility(View.VISIBLE);
        if(list.isEmpty()){
            button_loading.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(activity_main);
            textView.setTextColor(Color.rgb(128, 128, 128));
            textView.setTextSize(15);
            textView.setText("收藏为空");
            textView.setTextIsSelectable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            linear.addView(textView);
            return;
        }else{
            button_loading.setVisibility(View.VISIBLE);
        }
        for(int i=0; i<list.size(); i++){
            String str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));

            if(!Fun.StrBoolJSON(str)){
                Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                continue;
            }
            List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
            View view = Fun_文章.Create_Post_View(activity_main, post_data, 2);
            if(linear.getChildCount() >= 10){
                view.setVisibility(View.INVISIBLE);
            }else{
                view.setVisibility(View.VISIBLE);
            }
            linear.addView(view);
        }
    }


}
