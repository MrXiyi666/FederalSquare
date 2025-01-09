package fun.android.federal_square.fun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
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
import fun.android.federal_square.view.Video_ImageView;
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.打开方式窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看视频窗口;
import fun.android.federal_square.window.查看评论窗口;

public class Fun_文章 {

    public static View Create_Post_View(Activity activity, List<Post_Data> post_data, int index){
        View view = View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_forward = view.findViewById(R.id.button_forward);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);
        View img_view = View.inflate(activity, R.layout.create_post_img_layout, null);
        LinearLayout img_linear1 = img_view.findViewById(R.id.img_linear1);
        TextView name_view = view.findViewById(R.id.name);
        TextView sign_view = view.findViewById(R.id.sign);
        RoundImageView avatar_img = view.findViewById(R.id.avatar_img);
        TextView url_txt_id = view.findViewById(R.id.url_txt_id);
        List<Video_ImageView> img_list = new ArrayList<>();
        img_list.add(img_view.findViewById(R.id.img0));
        img_list.add(img_view.findViewById(R.id.img1));
        img_list.add(img_view.findViewById(R.id.img2));
        LinearLayout linear = view.findViewById(R.id.linear);

        StringBuffer sb = new StringBuffer();
        int img_id=0;
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
                    if(pd.getText().isEmpty()){
                        avatar_img.setImageResource(R.mipmap.ic_launcher_round);
                    }else{
                        Glide.with(activity)
                                .asBitmap()
                                .load(pd.getText())
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
                    if(img_id >= 3){
                        continue;
                    }
                    img_linear1.setVisibility(View.VISIBLE);
                    img_list.get(img_id).setImageBitmap(null);
                    Glide.with(activity)
                            .load(pd.getText())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(img_list.get(img_id));
                    String 后缀 = Fun_文件.获取后缀(pd.getText());
                    img_list.get(img_id).后缀 = 后缀;
                    if(!Fun.图片格式判断(后缀)){
                        img_list.get(img_id).setBackgroundColor(Color.BLACK);
                    }
                    img_list.get(img_id).setOnClickListener(V->{
                        if(Fun.图片格式判断(后缀)){
                            查看图片窗口.启动_Dialog(activity, pd.getText());
                        }else if(Fun.视频格式判断(后缀)){
                            查看视频窗口.启动_Dialog(activity, pd.getText());
                        }else{
                            打开方式窗口.启动(activity, pd.getText());
                        }
                    });
                    img_id++;
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
        if(img_id > 0){
            img_view.setPadding(0,Fun.DPToPX(activity, 2),0, 0);
            linear.addView(img_view);
        }
        img_view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if(img_view.getHeight() > Fun.DPToPX(activity, 150)){
                ViewGroup.LayoutParams params = img_view.getLayoutParams();
                params.height = Fun.DPToPX(activity, 150);
                img_view.setLayoutParams(params);
            }
        });
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
            查看评论窗口.查看评论窗口(activity, finalTime_txt, finalUrl_txt, finalPassWord_txt);
        });
        button_collection.setOnClickListener(V->{
            NetWork_添加收藏 netWork_添加_收藏 = new NetWork_添加收藏(activity);
            netWork_添加_收藏.传递参数(finalTime_txt, post_data);
            netWork_添加_收藏.start();
        });
        view.setOnClickListener(V->{
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
