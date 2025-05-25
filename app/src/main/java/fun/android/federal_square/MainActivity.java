package fun.android.federal_square;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.view.View_Create;
import fun.android.federal_square.view.View_Main_Pager;

public class MainActivity extends AppCompatActivity {
    public RelativeLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        GradientDrawable gradientDrawable= new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(Fun.DPToPX(this, 20));
        gradientDrawable.setColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        getWindow().getDecorView().setBackground(gradientDrawable);
        初始化();
        事件();
    }
    public void 初始化(){
        main = findViewById(R.id.main);
    }

    public void 事件(){

        if(Fun_文件.是否存在(able.app_path + "System_Data/URL_Name.txt")){
            able.URL = Fun.获取域名();
            able.PassWord = Fun.获取密码();
            able.view_main = new View_Main_Pager(this);
        }else{
            able.view_main = new View_Create(this);
        }
        able.view_main.getView().setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        main.addView(able.view_main.getView());

    }

    @Override
    protected void onStart() {
        super.onStart();
        able.view_main.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        able.view_main.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        able.view_main.释放();
    }

}