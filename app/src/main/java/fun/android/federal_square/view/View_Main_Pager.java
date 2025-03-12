package fun.android.federal_square.view;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Main_Pager_Adapter;
import fun.android.federal_square.data.able;

public class View_Main_Pager extends View_Main{
    private ViewPager pager;
    private LinearLayout menu_square, menu_hot, menu_home;
    private ImageView img_square, img_hot, img_home;
    public LinearLayout linear_menu, menu_list_view, linear_menu_view;
    public TextView menu_text;
    public AppCompatButton button_top, button_up, button_down, button_update;

    public View_Main_Pager(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_main_pager, null);
        pager = view.findViewById(R.id.pager);
        linear_menu = view.findViewById(R.id.linear_menu);
        img_square = view.findViewById(R.id.img_square);
        img_hot = view.findViewById(R.id.img_hot);
        img_home = view.findViewById(R.id.img_home);
        menu_square = view.findViewById(R.id.menu_square);
        menu_hot = view.findViewById(R.id.menu_hot);
        menu_home = view.findViewById(R.id.menu_home);
        menu_text = view.findViewById(R.id.menu_text);
        menu_list_view = view.findViewById(R.id.menu_list_view);
        button_top = view.findViewById(R.id.button_top);
        button_up = view.findViewById(R.id.button_up);
        button_down = view.findViewById(R.id.button_down);
        button_update = view.findViewById(R.id.button_update);
        linear_menu_view = view.findViewById(R.id.linear_menu_view);
        List<View> pager_view = new ArrayList<>();
        able.view_square = new View_Square(activity_main);
        able.view_hot = new View_Hot(activity_main);
        able.view_home = new View_Home(activity_main);
        pager_view.add(able.view_square.getView());
        pager_view.add(able.view_hot.getView());
        pager_view.add(able.view_home.getView());
        var adapter = new Main_Pager_Adapter(pager_view);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        img_square.setImageResource(R.drawable.square_checked_true_icon);
        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
        img_home.setImageResource(R.drawable.hot_checked_false_icon);
        able.pager_id = 0;
        able.view_square.启动刷新();
    }

    @Override
    public void 事件() {
        super.事件();
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                able.pager_id = position;
                switch (position){
                    case 0:
                        linear_menu_view.setVisibility(View.VISIBLE);
                        img_square.setImageResource(R.drawable.square_checked_true_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
                        img_home.setImageResource(R.drawable.hot_checked_false_icon);
                        able.view_square.恢复界面();
                        able.view_square.修改底部空间();
                        break;
                    case 1:
                        linear_menu_view.setVisibility(View.VISIBLE);
                        img_square.setImageResource(R.drawable.square_checked_false_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_true_icon);
                        img_home.setImageResource(R.drawable.hot_checked_false_icon);
                        able.view_hot.恢复界面();
                        able.view_square.修改底部空间();
                        break;
                    case 2:
                        linear_menu_view.setVisibility(View.GONE);
                        img_square.setImageResource(R.drawable.square_checked_false_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
                        img_home.setImageResource(R.drawable.home_checked_true_icon);
                        able.view_home.恢复界面();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        menu_square.setOnClickListener(menu_square_v-> {
            if(pager.getCurrentItem() == 0){
                able.view_square.scrollView.smoothScrollTo(0,0);
                return;
            }
            pager.setCurrentItem(0);
        });
        menu_hot.setOnClickListener(_ -> pager.setCurrentItem(1));
        menu_home.setOnClickListener(_ -> pager.setCurrentItem(2));
        menu_square.setOnLongClickListener(v -> {
            if(pager.getCurrentItem() == 0){
                able.view_square.scrollView.smoothScrollTo(0, 0);
                return true;
            }
            pager.setCurrentItem(0);
            return true;
        });
        menu_hot.setOnLongClickListener(v -> {
            if(pager.getCurrentItem() == 1) {
                able.view_hot.scrollView.smoothScrollTo(0, 0);
                return true;
            }
            pager.setCurrentItem(1);
            return true;
        });
        menu_home.setOnLongClickListener(V->{
            if(pager.getCurrentItem() == 2){
                able.view_home.linear_main.removeAllViews();
                able.view_home.linear_main.addView(new View_Home_Page(activity_main).getView());
                return true;
            }
            pager.setCurrentItem(2);
            return true;
        });
        button_top.setOnClickListener(V->{
            if(pager.getCurrentItem() == 0){
                able.view_square.scrollView.smoothScrollTo(0,0);
            }else if(pager.getCurrentItem() == 1){
                able.view_hot.scrollView.smoothScrollTo(0,0);
            }
        });
        button_up.setOnClickListener(V->{
            if(pager.getCurrentItem() == 0){
                able.view_square.上一页();
            }else if(pager.getCurrentItem() == 1){
                able.view_hot.上一页();
            }

        });
        button_down.setOnClickListener(V->{
            if(pager.getCurrentItem() == 0){
                able.view_square.下一页();
            }else if(pager.getCurrentItem() == 1){
                able.view_hot.下一页();
            }

        });
        button_update.setOnClickListener(V->{
            if(pager.getCurrentItem() == 0){
                able.view_square.初始化本地数据();
            }else if(pager.getCurrentItem() == 1){
                able.view_hot.初始化数据();
            }
        });
        menu_text.setOnClickListener(V->{
            if(menu_list_view.getVisibility() == View.VISIBLE){
                menu_list_view.setVisibility(View.GONE);
                menu_text.setText(" ▲ ");
                menu_text.setBackgroundColor(Color.TRANSPARENT);
                menu_text.setTextColor(Color.rgb(242,243,247));
                able.view_square.修改底部空间();
                able.view_hot.修改底部空间();
                return;
            }
            menu_list_view.setVisibility(View.VISIBLE);
            menu_text.setText(" ▼ ");
            menu_text.setBackgroundColor(Color.WHITE);
            menu_text.setTextColor(Color.rgb(128,128,128));
            able.view_square.修改底部空间();
            able.view_hot.修改底部空间();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        able.view_square.onStart();
        able.view_hot.onStart();
        able.view_home.onStart();
        Log.w("测试", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        able.view_square.onStop();
        able.view_hot.onStop();
        able.view_home.onStop();

    }

    @Override
    public void 返回键() {
        super.返回键();
        if(pager.getCurrentItem() > 0){
            pager.setCurrentItem(0);
        }else{
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity_main.startActivity(homeIntent);
        }
    }

    @Override
    public void 释放() {
        super.释放();
        able.view_square.释放();
        able.view_hot.释放();
        able.view_home.释放();
    }


}
