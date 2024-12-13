package fun.android.federal_square.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_文章;
import fun.android.federal_square.network.NetWork_读取热门;

public class View_Hot extends View_Main{
    private TextView top_title;
    private SwipeRefreshLayout swiperefee;
    public LinearLayout linear;
    public View_Hot(MainActivity activity) {
        super(activity);

    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_hot, null);
        top_title = view.findViewById(R.id.top_title);
        swiperefee = view.findViewById(R.id.swiperefee);
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
        List<String> filename = Fun_文章.获取热门集合();
        linear.post(()->{
            linear.removeAllViews();
        });

        if(filename == null){
            return;
        }
        int index=50;
        String sindex = Fun_文件.读取文件(able.app_path + "System_Data/Hot_Essay_index.txt");
        if(!sindex.isEmpty()){
            index = Integer.parseInt(sindex);
        }
        for(int i=0;i<index;i++){
            if(i >= filename.size()){
                return;
            }
            try {
                String str = Fun_文件.读取文件(able.app_path + "Square_Data/" + filename.get(i) + ".json");
                if(str.isEmpty()){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + filename.get(i) + ".json");
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    Fun_文件.删除文件(able.app_path + "Square_Data/" + filename.get(i) + ".json");
                    continue;
                }
                linear.post(()->{
                    linear.addView(Fun_文章.创建文章(activity_main,post_data));
                });
            }catch (Exception e){
                Fun_文件.删除文件(able.app_path + "Square_Data/" + filename.get(i) + ".json");
            }
        }



    }
}
