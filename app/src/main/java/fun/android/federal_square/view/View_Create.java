package fun.android.federal_square.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class View_Create extends View_Main{
    private AppCompatButton button_ok;
    private MainActivity activity;
    private EditText edit_url, edit_password;
    public View_Create(MainActivity activity) {
        super(activity);
        this.activity = activity;
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
            activity.main.removeView(able.view_main.getView());
            able.view_main = new View_Main_Pager(activity);
            able.view_main.getView().setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            activity.main.addView(able.view_main.getView());
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
