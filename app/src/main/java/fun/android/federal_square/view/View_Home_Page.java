package fun.android.federal_square.view;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.window.选择头像窗口;
import fun.android.federal_square.window.选择背景窗口;

public class View_Home_Page extends View_Main{
    public ImageView avatar_img;
    public ImageView back_img;
    private TextView top_title;
    public TextView name_view, sign_view, account_id;
    public LinearLayout linear;
    private RelativeLayout top_relati;
    private AppCompatButton button_essay, button_collection, button_system;

    public View_Home_Essay view_home_essay;
    public View_Home_Collection view_home_collection;
    public View_Home_Page(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_page, null);
        top_title = view.findViewById(R.id.top_title);
        top_relati = view.findViewById(R.id.top_relati);
        linear = view.findViewById(R.id.linear);
        avatar_img = view.findViewById(R.id.avatar_img);
        back_img = view.findViewById(R.id.back_img);
        name_view = view.findViewById(R.id.name);
        sign_view = view.findViewById(R.id.sign);
        account_id = view.findViewById(R.id.account_id);
        button_essay = view.findViewById(R.id.button_essay);
        button_collection = view.findViewById(R.id.button_collection);
        button_system = view.findViewById(R.id.button_system);
        view_home_essay = new View_Home_Essay(activity_main);
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.post(()->{
            top_title.setPadding(0, Fun.获取状态栏高度(activity_main) / 2, 0, 0);
        });
        name_view.setText(Fun_账号.GetName() + "");
        sign_view.setText(Fun_账号.GetSign() + "");
        account_id.setText("ID: " + Fun_账号.GetID() + "");
        if(Fun_账号.GetAvatar_Url().isEmpty()){
            avatar_img.setImageResource(R.mipmap.ic_launcher_round);
        }else{
            Glide.with(activity_main)
                    .load(Fun_账号.GetAvatar_Url())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(able.原图_request)
                    .override(Fun.DPToPX(activity_main, 80))
                    .into(avatar_img);
        }
        if(!Fun_账号.GetBack_Url().isEmpty()){
            Glide.with(activity_main)
                    .load(Fun_账号.GetBack_Url())
                    .apply(able.原图_request)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(back_img);
        }
        back_img.setBackgroundResource(R.drawable.gradient_white_to_transparent);
        top_relati.post(()->{
            top_relati.getLayoutParams().height = top_relati.getHeight() + Fun.获取状态栏高度(activity_main);
            top_relati.requestLayout();
        });

        avatar_img.setOnClickListener(V->{
            选择头像窗口.选择头像(activity_main, avatar_img);
        });
        back_img.setOnClickListener(V->{
            选择背景窗口.启动(activity_main, this);
        });

        linear.addView(view_home_essay.getView());
        button_essay.setTextColor(Color.rgb(0,0,0));
        button_collection.setTextColor(Color.rgb(128,128,128));
        button_system.setTextColor(Color.rgb(128,128,128));
        button_essay.setOnClickListener(V->{
            view_home_collection = null;
            linear.removeAllViews();
            view_home_essay = new View_Home_Essay(activity_main);
            linear.addView(view_home_essay.getView());
            button_essay.setTextColor(Color.rgb(0,0,0));
            button_collection.setTextColor(Color.rgb(128,128,128));
            button_system.setTextColor(Color.rgb(128,128,128));
        });
        button_collection.setOnClickListener(V->{
            view_home_essay = null;
            linear.removeAllViews();
            view_home_collection = new View_Home_Collection(activity_main);
            linear.addView(view_home_collection.getView());
            button_essay.setTextColor(Color.rgb(128,128,128));
            button_collection.setTextColor(Color.rgb(0,0,0));
            button_system.setTextColor(Color.rgb(128,128,128));
        });
        button_system.setOnClickListener(V->{
            view_home_essay = null;
            view_home_collection = null;
            linear.removeAllViews();
            linear.addView(new View_Home_System(activity_main).getView());
            button_essay.setTextColor(Color.rgb(128,128,128));
            button_collection.setTextColor(Color.rgb(128,128,128));
            button_system.setTextColor(Color.rgb(0,0,0));
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
        if(view_home_essay!=null){
            view_home_essay.释放();
        }
        if(view_home_collection!=null){
            view_home_collection.释放();
        }
    }

    public void 恢复界面(){
        if(view_home_essay!=null){
            view_home_essay.恢复界面();
        }
        if(view_home_collection!=null){
            view_home_collection.恢复界面();
        }
    }

}
