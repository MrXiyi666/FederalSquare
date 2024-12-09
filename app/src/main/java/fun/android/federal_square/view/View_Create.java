package fun.android.federal_square.view;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class View_Create extends View_Main{
    private AppCompatButton button_ok;
    private EditText edit_url, edit_password;
    public View_Create(MainActivity activity) {
        super(activity);
    }
    private ViewPager pager;
    private LinearLayout linear_create, linear_di;
    public void 传递参数(ViewPager pager, LinearLayout linear_create, LinearLayout linear_di){
        this.pager = pager;
        this.linear_create = linear_create;
        this.linear_di = linear_di;
        if(Fun_文件.是否存在(able.app_path + "URL_Name.txt")){
            pager.setVisibility(View.VISIBLE);
            linear_create.setVisibility(View.GONE);
            linear_di.setVisibility(View.VISIBLE);
            able.URL_Name = Fun_文件.读取文件(able.app_path + "URL_Name.txt").split(",")[0]+"";
            able.Read_PassWord = Fun_文件.读取文件(able.app_path + "URL_Name.txt").split(",")[1] + "";

        }
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
            String txt_url = edit_url.getText().toString();
            String txt_password = edit_password.getText().toString();
            if(txt_url.isEmpty()){
                return;
            }
            String txt_data = txt_url;
            able.URL_Name = txt_url;

            if(!txt_password.isEmpty()){
                txt_data = txt_data + "," + txt_password;
                able.Read_PassWord = txt_password;
            }
            Fun_文件.写入文件(able.app_path + "URL_Name.txt", txt_data);
            pager.setVisibility(View.VISIBLE);
            linear_create.setVisibility(View.GONE);
            linear_di.setVisibility(View.VISIBLE);

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
