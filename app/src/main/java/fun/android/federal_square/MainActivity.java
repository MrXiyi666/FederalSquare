package fun.android.federal_square;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;
import fun.android.federal_square.view.Popular_View;
import fun.android.federal_square.view.TimeLine_View;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        初始化();
        事件();
    }

    private void 初始化(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Static.main = findViewById(R.id.scrollView);
        Static.timeLine_view = new TimeLine_View(this);
        Static.view_main = Static.timeLine_view;
        Static.main.addView(Static.view_main.getView(), new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    private void 事件(){
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
                Static.main.removeAllViews();
                return true;
            }
            return false;
        });

        Static.main.setOnScrollChangeListener((v, c, scrollY, oldScrollX, oldScrollY) -> {
            if(Static.view_main instanceof TimeLine_View){
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

}