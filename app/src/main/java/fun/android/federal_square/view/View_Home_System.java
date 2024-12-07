package fun.android.federal_square.view;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_个人信息;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.window.引用列表窗口;

public class View_Home_System extends View_Main{
    private AppCompatButton 网盘按钮, 个人信息, 引用列表;
    public View_Home_System(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_system, null);
        网盘按钮 = view.findViewById(R.id.button_network_disk);
        个人信息 = view.findViewById(R.id.button_personal_information);
        引用列表 = view.findViewById(R.id.button_yinyong_list);
    }

    @Override
    public void 事件() {
        super.事件();
        网盘按钮.setOnClickListener(V->{
            if(Fun_文件.是否存在(able.app_path + "Account/account.json")){
                Intent intent = new Intent();
                intent.setClass(activity_main, DiskActivity.class);
                activity_main.startActivity(intent);
            }
        });
        个人信息.setOnClickListener(V->{
            Fun_个人信息.修改个人信息(activity_main);
        });
        引用列表.setOnClickListener(V->{
            new 引用列表窗口().启动(activity_main);
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
