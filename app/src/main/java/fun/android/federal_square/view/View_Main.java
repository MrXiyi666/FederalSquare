package fun.android.federal_square.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import fun.android.federal_square.R;
import fun.android.federal_square.system.Fun;

public class View_Main {
    private Context context;
    public String class_name;
    public View view;
    public View_Main(Context context){
        this.context = context;
        this.class_name = this.getClass().getSimpleName();
    }

    public View getView(){
        return view;
    }

    public void 初始化(){

    }

    public void 事件(){

    }

    public void 释放(){

    }

}
