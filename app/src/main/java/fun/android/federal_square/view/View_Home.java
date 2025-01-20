package fun.android.federal_square.view;

import android.view.View;
import android.widget.LinearLayout;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class View_Home extends View_Main{
    public LinearLayout linear_main;
    public View_Home_Page view_home_page;
    public View_Home(MainActivity activity) {
        super(activity);

    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home, null);
        linear_main = view.findViewById(R.id.linear_main);
        view_home_page = new View_Home_Page(activity_main);
    }

    @Override
    public void 事件() {
        super.事件();
        if(!Fun_文件.是否存在(able.app_path + "Account/account.json")){
            linear_main.removeAllViews();
            linear_main.addView(new View_Login(activity_main).getView());
        }else{
            linear_main.removeAllViews();

            linear_main.addView(view_home_page.getView());
        }


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
}
