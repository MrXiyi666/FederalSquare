package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
    public View_Home_Collection(Activity activity) {
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
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
    }


    public void 初始化收藏(){
        new Thread(()->{
            List<String> list = Fun_文章.获取收藏集合();
            able.handler.post(()->{
                linear.removeAllViews();
                button_loading.setVisibility(View.VISIBLE);
            });
            int index=50;
            String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Home_Collection_Essay_index.txt");
            if(!sindex.isEmpty()){
                index = Integer.parseInt(sindex);
            }
            for(int i=0;i<index;i++){
                if(i>=list.size()){
                    return;
                }
                String str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
                if(!Fun.StrBoolJSON(str)){
                    Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    continue;
                }
                able.handler.post(()->{
                    linear.addView(Fun_文章.Create_Post_View(activity_main, post_data, 2));
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
