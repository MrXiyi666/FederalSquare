package fun.android.federal_square.view;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_贴子;
import fun.android.federal_square.network.NetWork_我的_收藏_刷新;

public class View_Home_Collection extends View_Main{
    private SwipeRefreshLayout swiperefee;
    public LinearLayout linear;
    public View_Home_Collection(Activity activity) {
        super((MainActivity) activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_collection, null);
        swiperefee = view.findViewById(R.id.swiperefee);
        linear = view.findViewById(R.id.linear);
        初始化收藏();
    }
    @Override
    public void 事件() {
        super.事件();

        swiperefee.setOnRefreshListener(()->{
            NetWork_我的_收藏_刷新 netWork_我的_收藏刷新 = new NetWork_我的_收藏_刷新(activity_main);
            netWork_我的_收藏刷新.传递参数(View_Home_Collection.this);
            netWork_我的_收藏刷新.start();
            swiperefee.setRefreshing(false);
        });
    }

    public void 初始化收藏(){
        List<String> list = Fun_贴子.获取收藏集合();
        able.handler.post(()->{
            linear.removeAllViews();
        });
        int i=0;
        for(String name : list){
            if(i >= 100){
                return;
            }

            try {
                String str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + name);
                List<Post_Data>  post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                able.handler.post(()->{
                    linear.addView(Fun_贴子.创建收藏贴子(activity_main, post_data, View_Home_Collection.this));
                });

                i++;
            }catch (Exception e){

            }


        }

    }
}
