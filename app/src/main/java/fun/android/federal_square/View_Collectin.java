package fun.android.federal_square;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_删除收藏;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_查看图片;
import fun.android.federal_square.fun.Fun_评论;
import fun.android.federal_square.fun.Fun_贴子;
import fun.android.federal_square.view.View_Home_Collection;

public class View_Collectin extends AppCompatActivity {
    private ImageView return_icon;
    private TextView top_title;
    private LinearLayout linear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.WHITE);
        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_collection);
        初始化();
        事件();
    }

    public void 初始化(){
        top_title = findViewById(R.id.top_title);
        return_icon = findViewById(R.id.return_icon);
        linear = findViewById(R.id.linear);
        初始化数据();
    }

    public void 事件(){
        //top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        return_icon.setOnClickListener(V->{
            finish();
        });
    }

    public void 初始化数据(){
        List<String> list = Fun_贴子.获取收藏集合();
        linear.post(()->{
            linear.removeAllViews();
        });
        for(int i=0;i<list.size();i++){
            try {
                String str = Fun_文件.读取文件(able.app_path + "Account/Collection/" + list.get(i));
                if(str.isEmpty()){
                    Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                    continue;
                }
                List<Post_Data> post_data = able.gson.fromJson(str, new TypeToken<List<Post_Data>>(){}.getType());
                if(post_data == null){
                    Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
                    continue;
                }
                linear.post(()->{
                   linear.addView(创建收藏贴子(post_data));
                });
            }catch (Exception e){
                Fun_文件.删除文件(able.app_path + "Account/Collection/" + list.get(i));
            }
        }

    }

    public View 创建收藏贴子(List<Post_Data> post_data){
        String time_name="";

        View view = View.inflate(this, R.layout.create_post_layout, null);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);

        View img_view = View.inflate(this, R.layout.create_post_img_layout, null);
        LinearLayout img_linear1 = img_view.findViewById(R.id.img_linear1);
        TextView name_view = view.findViewById(R.id.name);
        TextView sign_view = view.findViewById(R.id.sign);
        ImageView avatar_img = view.findViewById(R.id.avatar_img);
        List<ImageView> img_list = new ArrayList<>();

        img_list.add(img_view.findViewById(R.id.img0));
        img_list.add(img_view.findViewById(R.id.img1));
        img_list.add(img_view.findViewById(R.id.img2));
        LinearLayout linear = view.findViewById(R.id.linear);
        int img_id=0;
        String 网址="";
        String PassWord_txt="";
        for(Post_Data pd : post_data){
            switch (pd.getName()){
                case "name":
                    name_view.setText(pd.getText());
                    break;
                case "sign":
                    sign_view.setText(pd.getText());
                    break;
                case "avatar":
                    if(pd.getText().isEmpty()){
                        avatar_img.setImageResource(R.mipmap.ic_launcher_round);
                    }else{
                        Glide.with(this)
                                .load(pd.getText())
                                .apply(new RequestOptions()
                                        .circleCropTransform()
                                        .error(R.drawable.glide_shibai)
                                        .fallback(R.drawable.glide_duqushibai))
                                .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                                .into(avatar_img);
                    }
                    break;
                case "text":
                    TextView text1 = new TextView(this);
                    text1.setTextColor(Color.BLACK);
                    text1.setTextSize(15);
                    text1.setText(pd.getText());
                    text1.setTextIsSelectable(true);
                    linear.addView(text1);
                    break;
                case "img":
                    if(img_id < 3){
                        img_linear1.setVisibility(View.VISIBLE);
                        img_list.get(img_id).setImageBitmap(null);
                        Glide.with(this)
                                .load(pd.getText())
                                .apply(new RequestOptions()
                                        .error(R.drawable.glide_shibai)
                                        .fallback(R.drawable.glide_duqushibai))
                                .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                                .into(img_list.get(img_id));
                        img_list.get(img_id).setOnClickListener(V->{
                            Fun_查看图片.启动(this, pd.getText());
                        });
                        img_id++;
                    }
                    break;
                case "time":
                    time_name = pd.getText();
                    break;
                case "url":
                    网址 = pd.getText();
                    break;
                case "password":
                    PassWord_txt = pd.getText();
                    break;
            }
        }
        linear.addView(img_view);
        String finalTime_name = time_name;
        String final网址 = 网址;
        String finalPassWord_txt = PassWord_txt;
        button_message.setOnClickListener(V->{
            Fun_评论.查看评论窗口(this, finalTime_name, final网址, finalPassWord_txt);
        });

        button_collection.setVisibility(View.GONE);
        String finalTime_name2 = time_name;
        view.setOnLongClickListener(V->{
            Fun_删除收藏.所有启动(this, finalTime_name2);
            return true;
        });
        view.setOnClickListener(V->{
            able.传递数据 = post_data;
            Intent intent = new Intent();
            intent.setClass(this, View_Post_Activity.class);
            this.startActivity(intent);
        });
        return view;
    }


}
