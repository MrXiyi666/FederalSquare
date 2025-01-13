package fun.android.federal_square.fun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import net.csdn.roundview.RoundImageView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.View_Essay;
import fun.android.federal_square.View_Post_Activity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_添加收藏;
import fun.android.federal_square.network.NetWork_转发功能;
import fun.android.federal_square.view.Post_View;
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.查看评论窗口;

public class Fun_文章 {

    public static Post_View Create_Post_View(Activity activity, List<Post_Data> post_data, int index){
        Post_View view =  (Post_View)View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_forward = view.findViewById(R.id.button_forward);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);
        TextView name_view = view.findViewById(R.id.name);
        TextView sign_view = view.findViewById(R.id.sign);
        RoundImageView avatar_img = view.findViewById(R.id.avatar_img);
        TextView url_txt_id = view.findViewById(R.id.url_txt_id);
        LinearLayout linear = view.findViewById(R.id.linear);
        StringBuffer sb = new StringBuffer();
        List<String> img_url = new ArrayList<>();
        String avatar_url = "";
        String url_txt="";
        String PassWord_txt="";
        String time_txt = "";
        String forward = "";
        for(Post_Data pd : post_data){
            switch(pd.getName()){
                case "name":
                    name_view.setText(pd.getText().replace("\n", " ").replace("\r", " "));
                    break;
                case "sign":
                    sign_view.setText(pd.getText().replace("\n", " ").replace("\r", " "));
                    break;
                case "avatar":
                    avatar_url = pd.getText();
                    if(pd.getText().isEmpty()){
                        avatar_img.setImageResource(R.mipmap.ic_launcher_round);
                    }else{
                        Glide.with(activity)
                                .load(pd.getText())
                                .override(Fun.DPToPX(activity, 40), Fun.DPToPX(activity, 40))
                                .into(avatar_img);
                    }
                    break;
                case "text":
                    String [] str = pd.getText().replace("\n", " ").replace("\r", " ").split("");
                    if(sb.length() >=100){
                        break;
                    }
                    if(!sb.toString().isEmpty()){
                        sb.append("\n");
                    }
                    for(String s : str){
                        if(sb.length() >= 100){
                            break;
                        }
                        sb.append(s);
                    }
                    break;
                case "img":
                    if(img_url.size() > 8){
                        continue;
                    }
                    img_url.add(pd.getText());
                    break;
                case "url":
                    url_txt = pd.getText();
                    break;
                case "password":
                    PassWord_txt = pd.getText();
                    break;
                case "time":
                    time_txt = pd.getText();
                    break;
                case "forward":
                    forward = pd.getText();
                    break;
            }
        }
        if(able.URL.equals(url_txt)){
            button_forward.setVisibility(View.GONE);
        }
        if(!able.URL.equals(url_txt)){
            url_txt_id.setText(url_txt);
            url_txt_id.setVisibility(View.VISIBLE);
        }
        if(!forward.isEmpty()){
            button_forward.setVisibility(View.GONE);
        }
        if(index>0){
            button_forward.setVisibility(View.GONE);
        }
        if(sb.length() >=100){
            sb.append("...");
        }
        if(!sb.toString().isEmpty()){
            TextView textView = new TextView(activity);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);
            textView.setText(sb.toString());
            textView.setTextIsSelectable(true);
            linear.addView(textView);
        }
        Fun_文章子布局 fun_文章子布局 = new Fun_文章子布局(activity, img_url);
        view.传递参数(fun_文章子布局);
        view.传递参数(activity, avatar_img, avatar_url);
        if(fun_文章子布局.getView() != null){
            linear.addView(fun_文章子布局.getView());
        }

        String finalTime_txt = time_txt;
        String finalUrl_txt = url_txt;
        String finalPassWord_txt = PassWord_txt;
        String finalForward = forward;
        button_forward.setOnClickListener(V->{
            if(finalForward.isEmpty() && !able.URL.equals(finalUrl_txt)){
                Post_Data postData = new Post_Data();
                postData.setName("forward");
                postData.setText("true");
                post_data.add(postData);
                NetWork_转发功能 netWork_转发功能 = new NetWork_转发功能(activity);
                netWork_转发功能.传递参数(post_data, finalTime_txt);
                netWork_转发功能.start();
            }else{
                Fun.mess(activity, "已存在");

            }

        });

        button_message.setOnClickListener(V->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity, "没有登陆 无法查看");
                return;
            }
            查看评论窗口.查看评论窗口(activity, finalTime_txt, finalUrl_txt, finalPassWord_txt);
        });
        button_collection.setOnClickListener(V->{
            NetWork_添加收藏 netWork_添加_收藏 = new NetWork_添加收藏(activity);
            netWork_添加_收藏.传递参数(finalTime_txt, post_data);
            netWork_添加_收藏.start();
        });
        view.setOnClickListener(V->{
            if(Fun_账号.GetID().isEmpty()){
                Fun.mess(activity, "没有登陆 无法查看");
                return;
            }
            able.传递数据 = post_data;
            Intent intent = new Intent();
            intent.setClass(activity, View_Post_Activity.class);
            activity.startActivity(intent);
        });
        view.setOnLongClickListener(V->{
            if(index == 1){
                删除窗口.删除我的文章窗口(activity, finalTime_txt);
            }
            if(index == 2){
                删除窗口.删除收藏窗口(activity, finalTime_txt);
            }
            if(index == 3){
                删除窗口.删除所有文章窗口((View_Essay) activity, finalTime_txt);
            }
            if(index == 4){
                删除窗口.删除所有收藏窗口((View_Collectin) activity, finalTime_txt);
            }
            return true;
        });
        if(index==2){
            button_collection.setVisibility(View.GONE);
        }

        return view;
    }

    public static List<String> 获取广场集合(){
        try {
            List<String> list = Fun_文件.遍历文件夹(able.app_path + "Square_Data");
            Comparator<String> comparator = Comparator.reverseOrder();
            list.sort(comparator);
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static List<String> 获取我的文章集合(){
        try {
            List<String> list = Fun_文件.遍历文件夹(able.app_path + "Account/Data");
            Comparator<String> comparator = Comparator.reverseOrder();
            list.sort(comparator);
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public static List<String> 获取收藏集合(){
        try {
            List<String> list = Fun_文件.遍历文件夹(able.app_path + "Account/Collection");
            Comparator<String> comparator = Comparator.reverseOrder();
            list.sort(comparator);
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static List<String> 获取热门集合(){
        try {
            String txt = Fun_文件.读取文件(able.app_path + "Hot_Data/list.json");
            if(txt.isEmpty()){
                return new ArrayList<>();
            }
            return able.gson.fromJson(txt, new TypeToken<List<String>>(){}.getType());
        }catch (Exception e){
            return new ArrayList<>();
        }
    }


}
