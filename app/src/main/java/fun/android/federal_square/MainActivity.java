package fun.android.federal_square;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;
import fun.android.federal_square.view.Create_View;
import fun.android.federal_square.view.Home_View;
import fun.android.federal_square.view.Popular_View;
import fun.android.federal_square.view.TimeLine_View;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Handler 滑动监听_handler = new Handler();
    ImageView button_sheet;
    private Runnable 滑动监听_scrollEndRunnable = () -> button_sheet.setAlpha(0.2f);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        初始化();
    }

    private void 初始化(){
        Static.create(this);
        Static.main = findViewById(R.id.scrollView);
        Static.view_main = new Create_View(this);
        Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Static.main.setBackgroundColor(Color.parseColor(Static.drawable_color));
        初始化菜单();
    }
    public void 初始化菜单(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        button_sheet = findViewById(R.id.button_sheet);
        bottomNavigationView.setBackgroundColor(Color.parseColor(Static.menu_color));
        菜单事件();
        bottomNavigationView.setVisibility(View.GONE);
    }
    private View 初始化功能菜单(){
        View button_sheet_view = getLayoutInflater().inflate(R.layout.button_sheet_layout, null);
        button_sheet_view.setBackgroundColor(Color.parseColor(Static.menu_color));
        AppCompatButton button_update = button_sheet_view.findViewById(R.id.button_update);
        AppCompatButton button_top = button_sheet_view.findViewById(R.id.button_top);
        AppCompatButton button_up = button_sheet_view.findViewById(R.id.button_up);
        AppCompatButton button_down = button_sheet_view.findViewById(R.id.button_down);

        Fun.setButtonTheme(this, button_update);
        Fun.setButtonTheme(this, button_top);
        Fun.setButtonTheme(this, button_up);
        Fun.setButtonTheme(this, button_down);
        return button_sheet_view;
    }

    private void 菜单事件(){
        button_sheet.setOnClickListener(V->{
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(初始化功能菜单());
            if (bottomSheetDialog.getWindow() != null) {
                bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                WindowManager.LayoutParams params = bottomSheetDialog.getWindow().getAttributes();
                params.dimAmount = 0f;
                bottomSheetDialog.getWindow().setAttributes(params);
                bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            bottomSheetDialog.show();
        });
        bottomNavigationView.setSelectedItemId(R.id.time_line);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.time_line){
                Static.view_main = new TimeLine_View(this);
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Static.main.scrollTo(0, Static.timeLine_view_y);
                button_sheet.setVisibility(View.VISIBLE);
                return true;
            }
            if(item.getItemId() == R.id.popular){
                Static.view_main = new Popular_View(this);
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Static.main.scrollTo(0, Static.popular_view_y);
                button_sheet.setVisibility(View.VISIBLE);
                return true;
            }
            if(item.getItemId() == R.id.home){
                Static.view_main = new Home_View(this);
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                button_sheet.setVisibility(View.GONE);
                return true;
            }
            return false;
        });

        Static.main.setOnScrollChangeListener((v, c, scrollY, oldScrollX, oldScrollY) -> {
            button_sheet.setAlpha(1.0f);
            滑动监听_handler.removeCallbacks(滑动监听_scrollEndRunnable);
            滑动监听_handler.postDelayed(滑动监听_scrollEndRunnable, 200);
            if(Static.view_main instanceof TimeLine_View ){
                Static.timeLine_view_y = scrollY;
            }
            if(Static.view_main instanceof Popular_View){
                Static.popular_view_y = scrollY;
            }
            if(Static.view_main instanceof TimeLine_View || Static.view_main instanceof Popular_View){
                for (int i = 0; i < Static.view_main.linear.getChildCount(); i++) {
                    View child = Static.view_main.linear.getChildAt(i);
                    if ("article_view".equals(child.getTag())) {
                        if(child.getHeight() + child.getTop() < scrollY || child.getTop() > scrollY + Static.main.getHeight()){
                            child.setVisibility(View.INVISIBLE);
                        }else{
                            child.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

        });

    }
    // 方法2：在窗口获取焦点时执行（更可靠）
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) { // 窗口获得焦点时，DecorView 已就绪
           // setStatusBarTextColorBlack(getWindow());
        }
    }
    /**
     * 将状态栏文字和图标设置为黑色
     * @param window 当前 Activity 的 Window 对象
     */
    private void setStatusBarTextColorBlack(Window window) {
        if (window == null) return;

        // 1. Android 11（API 30）及以上：使用 WindowInsetsController
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = window.getInsetsController();
            if (insetsController != null) {
                // 设置状态栏文字为深色（黑色）
                insetsController.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            }
        }
        // 2. Android 6.0（API 23）至 Android 10（API 29）：使用 SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 状态栏文字深色（黑色）
                    // 可叠加其他标志，如全屏：| View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
        // 3. Android 6.0（API 23）以下：不支持设置状态栏文字颜色（默认白色，无法修改）
    }

}