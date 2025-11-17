package fun.android.federal_square;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;
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
        setContentView(R.layout.activity_main);
        初始化();
        事件();
    }

    private void 初始化(){
        Static.create(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        button_sheet = findViewById(R.id.button_sheet);
        Static.main = findViewById(R.id.scrollView);
        Static.timeLine_view = new TimeLine_View(this);
        Static.view_main = Static.timeLine_view;
        Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Static.main.setBackgroundColor(Color.parseColor(Static.drawable_color));
        bottomNavigationView.setBackgroundColor(Color.parseColor(Static.menu_color));
    }
    private View 初始化功能菜单(){
        View button_sheet_view = getLayoutInflater().inflate(R.layout.button_sheet_layout, null);
        button_sheet_view.setBackgroundColor(Color.parseColor(Static.menu_color));
        AppCompatButton button_update = button_sheet_view.findViewById(R.id.button_update);
        AppCompatButton button_top = button_sheet_view.findViewById(R.id.button_top);
        AppCompatButton button_up = button_sheet_view.findViewById(R.id.button_up);
        AppCompatButton button_down = button_sheet_view.findViewById(R.id.button_down);

        StateListDrawable button_update_drawable = new StateListDrawable();
        button_update_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_update_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_update.setStateListAnimator(null);
        button_update.setElevation(0f);
        button_update.setBackground(button_update_drawable);

        StateListDrawable button_top_drawable = new StateListDrawable();
        button_top_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_top_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_top.setStateListAnimator(null);
        button_top.setElevation(0f);
        button_top.setBackground(button_top_drawable);

        StateListDrawable button_up_drawable = new StateListDrawable();
        button_up_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_up_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_up.setStateListAnimator(null);
        button_up.setElevation(0f);
        button_up.setBackground(button_up_drawable);

        StateListDrawable button_down_drawable = new StateListDrawable();
        button_down_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_down_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_down.setStateListAnimator(null);
        button_down.setElevation(0f);
        button_down.setBackground(button_down_drawable);

        return button_sheet_view;
    }

    private void 事件(){
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

                if(Static.timeLine_view==null){
                    Static.timeLine_view = new TimeLine_View(this);
                }
                Static.view_main = Static.timeLine_view;
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Static.main.scrollTo(0, Static.timeLine_view_y);
                return true;
            }
            if(item.getItemId() == R.id.popular){
                if(Static.popular_view==null){
                    Static.popular_view = new Popular_View(this);
                }
                Static.view_main = Static.popular_view;
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Static.main.scrollTo(0, Static.popular_view_y);
                return true;
            }
            if(item.getItemId() == R.id.home){
                if(Static.home_view==null){
                    Static.home_view = new Home_View(this);
                }
                Static.view_main = Static.home_view;
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return true;
            }
            return false;
        });

        Static.main.setOnScrollChangeListener((v, c, scrollY, oldScrollX, oldScrollY) -> {
            button_sheet.setAlpha(1.0f);
            滑动监听_handler.removeCallbacks(滑动监听_scrollEndRunnable);
            滑动监听_handler.postDelayed(滑动监听_scrollEndRunnable, 200);
            if(Static.view_main instanceof TimeLine_View){ //用于切换菜单 定位
                Static.timeLine_view_y = scrollY;
            }else {
                Static.popular_view_y = scrollY;
            }
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
        });

    }
    // 方法2：在窗口获取焦点时执行（更可靠）
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) { // 窗口获得焦点时，DecorView 已就绪
            setStatusBarTextColorBlack(getWindow());
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
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 状态栏文字深色（黑色）
                    // 可叠加其他标志，如全屏：| View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
        // 3. Android 6.0（API 23）以下：不支持设置状态栏文字颜色（默认白色，无法修改）
    }

}