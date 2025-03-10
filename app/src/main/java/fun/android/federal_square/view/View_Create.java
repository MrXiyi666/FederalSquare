package fun.android.federal_square.view;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Main_Pager_Adapter;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;

public class View_Create extends View_Main{
    private AppCompatButton button_ok;
    private MainActivity activity;
    private EditText edit_url, edit_password;
    public View_Create(MainActivity activity) {
        super(activity);
        this.activity = activity;
        activity.linear_menu.setVisibility(View.GONE);
        activity.linear_menu_list_view.setVisibility(View.GONE);
    }
    private ViewPager pager;
    private List<View> pager_view;
    public void 传递参数(ViewPager pager, List<View> pager_view){
        this.pager = pager;
        this.pager_view = pager_view;
        if(Fun_文件.是否存在(able.app_path + "System_Data/URL_Name.txt")){
            able.URL = Fun.获取域名();
            able.PassWord = Fun.获取密码();
            跳转到广场();
        }
    }

    private void 跳转到广场(){
        able.view_square = new View_Square(activity_main);
        able.view_hot = new View_Hot(activity_main);
        able.view_home = new View_Home(activity_main);
        pager_view.add(able.view_square.getView());
        pager_view.add(able.view_hot.getView());
        pager_view.add(able.view_home.getView());
        var adapter = new Main_Pager_Adapter(pager_view);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        pager.setVisibility(View.VISIBLE);
        activity.linear_create.setVisibility(View.GONE);
        activity.linear_menu.setVisibility(View.VISIBLE);
        activity.linear_menu_list_view.setVisibility(View.VISIBLE);
        able.view_square.启动刷新();
    }
    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_create, null);
        button_ok = view.findViewById(R.id.button_ok);
        edit_url = view.findViewById(R.id.edit_url);
        edit_password = view.findViewById(R.id.edit_password);
    }

    @Override
    public void 事件() {
        super.事件();
        button_ok.setOnClickListener(V->{
            var txt_data = edit_url.getText().toString().replaceAll("\\s+", "");
            var txt_password = edit_password.getText().toString();
            if(txt_data.isEmpty()){
                return;
            }
            able.URL = txt_data;
            if(!txt_password.isEmpty()){
                txt_data = txt_data + "," + txt_password;
                able.PassWord = txt_password;
            }
            Fun_文件.写入文件(able.app_path + "System_Data/URL_Name.txt", txt_data);
            跳转到广场();
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
}
