package fun.android.federal_square;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
        Static.view_main = new TimeLine_View(this);
        Static.main.addView(Static.view_main.getView());//new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
    }

    private void 事件(){
        bottomNavigationView.setSelectedItemId(R.id.time_line);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.time_line){
                Static.view_main = new TimeLine_View(MainActivity.this);
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView());
                return true;
            }
            if(item.getItemId() == R.id.popular){
                Static.view_main = new Popular_View(MainActivity.this);
                Static.main.removeAllViews();
                Static.main.addView(Static.view_main.getView());
                return true;
            }
            if(item.getItemId() == R.id.home){
                Static.main.removeAllViews();
                return true;
            }
            return false;
        });
    }

}