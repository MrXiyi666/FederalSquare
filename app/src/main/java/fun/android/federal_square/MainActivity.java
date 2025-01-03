package fun.android.federal_square;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.adatper.Main_Pager_Adapter;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.view.View_Create;
import fun.android.federal_square.view.View_Home;
import fun.android.federal_square.view.View_Home_Page;
import fun.android.federal_square.view.View_Hot;
import fun.android.federal_square.view.View_Square;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private final List<View> pager_view = new ArrayList<>();
    private LinearLayout menu_square, menu_hot, menu_home;
    private ImageView img_square, img_hot, img_home;
    private LinearLayout linear_create, button_menu;
    private View_Create view_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        初始化();
        事件();
    }

    public void 初始化(){
        able.状态栏高度 = Fun.获取状态栏高度(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        able.宽度 = displayMetrics.widthPixels;
        able.高度 = displayMetrics.heightPixels;

        linear_create = findViewById(R.id.linear_create);
        button_menu = findViewById(R.id.button_menu);
        pager = findViewById(R.id.pager);
        menu_square = findViewById(R.id.menu_square);
        menu_hot = findViewById(R.id.menu_hot);
        menu_home = findViewById(R.id.menu_home);
        img_square = findViewById(R.id.img_square);
        img_hot = findViewById(R.id.img_hot);
        img_home = findViewById(R.id.img_home);
        view_create = new View_Create(this);
        view_create.传递参数(pager, pager_view, linear_create, button_menu);
    }

    public void 事件(){
        linear_create.addView(view_create.getView());
        able.pager_id = 0;
        img_square.setImageResource(R.drawable.square_checked_true_icon);
        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
        img_home.setImageResource(R.drawable.hot_checked_false_icon);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                able.pager_id = position;
                switch (position){
                    case 0:
                        img_square.setImageResource(R.drawable.square_checked_true_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
                        img_home.setImageResource(R.drawable.hot_checked_false_icon);
                        break;
                    case 1:
                        img_square.setImageResource(R.drawable.square_checked_false_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_true_icon);
                        img_home.setImageResource(R.drawable.hot_checked_false_icon);
                        break;
                    case 2:
                        img_square.setImageResource(R.drawable.square_checked_false_icon);
                        img_hot.setImageResource(R.drawable.hot_checked_false_icon);
                        img_home.setImageResource(R.drawable.home_checked_true_icon);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        menu_square.setOnClickListener(menu_square_v-> {
            if(pager.getCurrentItem() == 0){
                able.view_square.scrollView.fullScroll(View.FOCUS_UP);
                return;
            }
            pager.setCurrentItem(0);
        });
        menu_hot.setOnClickListener(menu_square_v-> pager.setCurrentItem(1));
        menu_home.setOnClickListener(menu_square_v-> pager.setCurrentItem(2));
        menu_square.setOnLongClickListener(v -> {
            if(pager.getCurrentItem() == 0){
                able.view_square.初始化本地数据();
                able.view_square.scrollView.fullScroll(View.FOCUS_UP);
                return true;
            }
            pager.setCurrentItem(0);
            return true;
        });
        menu_hot.setOnLongClickListener(v -> {
            if(pager.getCurrentItem() == 1) {
                able.view_hot.初始化数据();
                return true;
            }
            pager.setCurrentItem(1);
            return true;
        });
        menu_home.setOnLongClickListener(V->{
            if(pager.getCurrentItem() == 2){
                able.view_home.linear_main.removeAllViews();
                able.view_home.linear_main.addView(new View_Home_Page(this).getView());
                return true;
            }
            pager.setCurrentItem(2);
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(able.view_square!=null){
            able.view_square.onStart();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(able.view_square != null){
            able.view_square.onStop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(able.view_square!=null){
            able.view_square.释放();
        }
        if(able.view_hot!=null){
            able.view_hot.释放();
        }
        if( able.view_home != null){
            able.view_home.释放();
        }

        Glide.get(this).clearMemory();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(pager.getCurrentItem() > 0){
                pager.setCurrentItem(0);
                return false;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}