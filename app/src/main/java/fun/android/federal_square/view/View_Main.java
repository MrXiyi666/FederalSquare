package fun.android.federal_square.view;

import android.view.View;
import fun.android.federal_square.MainActivity;

public class View_Main {
    public MainActivity activity_main;
    public String class_name;
    public View view;
    public View_Main(MainActivity activity){
        activity_main = activity;
        this.class_name = this.getClass().getSimpleName();
        初始化();
        事件();
    }

    public View getView(){
        return view;
    }

    public void 初始化(){

    }

    public void 事件(){

    }

    public void onStart(){

    }

    public void onStop(){

    }

    public void 返回键(){

    }

    public void 释放(){

    }

}