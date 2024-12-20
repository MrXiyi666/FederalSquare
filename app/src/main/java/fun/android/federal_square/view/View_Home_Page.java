package fun.android.federal_square.view;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.window.选择头像窗口;
import fun.android.federal_square.window.选择背景窗口;

public class View_Home_Page extends View_Main{
    public ImageView avatar_img, back_img;
    private TextView top_title;
    public TextView name_view, sign_view, account_id;
    public LinearLayout linear;
    private RelativeLayout relati_page;
    private AppCompatButton button_article, button_collection, button_system;
    public View_Home_Page(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_home_page, null);
        top_title = view.findViewById(R.id.top_title);
        relati_page = view.findViewById(R.id.relati_page);
        linear = view.findViewById(R.id.linear);
        avatar_img = view.findViewById(R.id.avatar_img);
        back_img = view.findViewById(R.id.back_img);
        name_view = view.findViewById(R.id.name);
        sign_view = view.findViewById(R.id.sign);
        account_id = view.findViewById(R.id.account_id);
        button_article = view.findViewById(R.id.button_article);
        button_collection = view.findViewById(R.id.button_collection);
        button_system = view.findViewById(R.id.button_system);
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.post(()->{
            top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
            back_img.getLayoutParams().height = relati_page.getHeight();
            back_img.requestLayout();
            relati_page.setPadding(20, 0, 20, 0);
        });
        name_view.setText(Fun_账号.GetName() + "");
        sign_view.setText(Fun_账号.GetSign() + "");
        account_id.setText("ID: " + Fun_账号.GetID() + "");
        if(Fun_账号.GetAvatar_Url().isEmpty()){
            avatar_img.setImageResource(R.mipmap.ic_launcher_round);
        }else{
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.glide_zhanwei)
                    .circleCropTransform()
                    .error(R.drawable.glide_shibai)
                    .fallback(R.drawable.glide_duqushibai);
            Glide.with(activity_main)
                    .load(Fun_账号.GetAvatar_Url())
                    .apply(requestOptions)
                    .into(avatar_img);
        }
        if(!Fun_账号.GetBack_Url().isEmpty()){
            Glide.with(activity_main).load(Fun_账号.GetBack_Url()).centerCrop().into(back_img);
        }
        avatar_img.setOnClickListener(V->{
            选择头像窗口.选择头像(activity_main, avatar_img);
        });
        back_img.setOnClickListener(V->{
            选择背景窗口.启动(activity_main, this);
        });
        linear.addView(new View_Home_Essay(activity_main).getView());
        button_article.setTextColor(Color.rgb(0,0,0));
        button_collection.setTextColor(Color.rgb(128,128,128));
        button_system.setTextColor(Color.rgb(128,128,128));
        button_article.setOnClickListener(V->{
            linear.removeAllViews();
            linear.addView(new View_Home_Essay(activity_main).getView());
            button_article.setTextColor(Color.rgb(0,0,0));
            button_collection.setTextColor(Color.rgb(128,128,128));
            button_system.setTextColor(Color.rgb(128,128,128));
        });

        button_collection.setOnClickListener(V->{
            linear.removeAllViews();
            linear.addView(new View_Home_Collection(activity_main).getView());
            button_article.setTextColor(Color.rgb(128,128,128));
            button_collection.setTextColor(Color.rgb(0,0,0));
            button_system.setTextColor(Color.rgb(128,128,128));
        });

        button_system.setOnClickListener(V->{
            linear.removeAllViews();
            linear.addView(new View_Home_System(activity_main).getView());
            button_article.setTextColor(Color.rgb(128,128,128));
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
    }


}
