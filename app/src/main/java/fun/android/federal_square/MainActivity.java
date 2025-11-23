package fun.android.federal_square;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import fun.android.federal_square.NetWork.NetWork_UpLoad;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Fun_文件;
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
        加载图片初始化();

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


    public void 加载图片初始化(){
        Static.上传图片 = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if(uri == null){
                Log.w("upload", "文件为 null ");
                return;
            }
            if(Fun.获取Uri文件大小(MainActivity.this, uri) > 52428800){
                //文件大于 50 MB 请压缩后上传
                Log.w("upload", "文件大于 50 MB 请压缩后上传 ");
                return;
            }
            String 后缀 = Fun.获取文件扩展名(Fun.获取Uri文件名(this, uri));
            if(Fun.图片格式判断(后缀)){
                Log.w("upload", "图片格式 " + 后缀);
                Fun_文件.copy_Uri_File(this, uri, Static.app_path + Fun.获取Uri文件名(this, uri));
                new NetWork_UpLoad(this).Start("http://mrxiyi.top/Record/upload.php", new File(Static.app_path + Fun.获取Uri文件名(this, uri)));
                return;
            }else if(Fun.视频格式判断(后缀)){
                Log.w("upload", "视频格式 " + 后缀);
                return;
            }
            Log.w("upload", "不支持的格式 " + 后缀);
        });
    }

}