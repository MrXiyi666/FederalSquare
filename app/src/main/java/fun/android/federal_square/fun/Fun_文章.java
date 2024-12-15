package fun.android.federal_square.fun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Post_Activity;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_添加收藏;
import fun.android.federal_square.view.View_Home_Essay;
import fun.android.federal_square.view.View_Home_Collection;
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看评论窗口;

public class Fun_文章 {


    public static View 创建文章(Activity activity, List<Post_Data> post_data){
        String time_name="";

        View view = View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);
        View img_view = View.inflate(activity, R.layout.create_post_img_layout, null);
        LinearLayout img_linear1 = img_view.findViewById(R.id.img_linear1);
        TextView name_view = view.findViewById(R.id.name);
        TextView sign_view = view.findViewById(R.id.sign);
        ImageView avatar_img = view.findViewById(R.id.avatar_img);
        TextView url_txt_id = view.findViewById(R.id.url_txt_id);
        List<ImageView> img_list = new ArrayList<>();
        img_list.add(img_view.findViewById(R.id.img0));
        img_list.add(img_view.findViewById(R.id.img1));
        img_list.add(img_view.findViewById(R.id.img2));
        LinearLayout linear = view.findViewById(R.id.linear);
        int img_id=0;
        StringBuffer sb = new StringBuffer();
        String url_txt="";
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
                        Glide.with(activity)
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
                    String [] str = pd.getText().replace("\n", "").replace("\r", "").split("");
                    if(sb.length() >=50){
                        break;
                    }
                    if(sb.length() > 0){
                        sb.append("\n");
                    }
                    for(String s : str){
                        if(sb.length() >= 50){
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
                            .apply(new RequestOptions()
                                    .error(R.drawable.glide_shibai)
                                    .fallback(R.drawable.glide_duqushibai))
                            .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                            .into(img_list.get(img_id));
                    img_list.get(img_id).setOnClickListener(V->{
                        查看图片窗口.启动(activity, pd.getText());
                    });
                    img_id++;
                    break;
                case "time":
                    time_name = pd.getText();
                    break;
                case "url":
                    url_txt = pd.getText();
                    break;
                case "password":
                    PassWord_txt = pd.getText();
                    break;
            }
        }


        if(!able.URL_Name.equals(url_txt)){
            url_txt_id.setText(url_txt);
        }
        if(sb.length() >=50){
            sb.append("...");
        }
        if(sb.length() > 0){
            TextView textView = new TextView(activity);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);
            textView.setText(sb.toString());
            textView.setTextIsSelectable(true);
            linear.addView(textView);
        }
        linear.addView(img_view);
        String finalTime_name = time_name;

        String finalUrl_txt = url_txt;
        String finalPassWord_txt = PassWord_txt;
        button_message.setOnClickListener(V->{
            查看评论窗口.查看评论窗口(activity, finalTime_name, finalUrl_txt, finalPassWord_txt);
        });
        button_collection.setOnClickListener(V->{
            NetWork_添加收藏 netWork_添加_收藏 = new NetWork_添加收藏(activity);
            netWork_添加_收藏.传递参数(finalTime_name, post_data);
            netWork_添加_收藏.start();
        });

        view.setOnClickListener(V->{
            able.传递数据 = post_data;
            Intent intent = new Intent();
            intent.setClass(activity, View_Post_Activity.class);
            activity.startActivity(intent);
        });
        return view;
    }


    public static View 创建我的文章(Activity activity, List<Post_Data> post_data, View_Home_Essay view_home_essay){
        String time_name="";

        View view = View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);

        View img_view = View.inflate(activity, R.layout.create_post_img_layout, null);
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
        StringBuffer sb = new StringBuffer();
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
                        Glide.with(activity)
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
                    String [] str = pd.getText().replace("\n", "").replace("\r", "").split("");
                    if(sb.length() >=50){
                        break;
                    }
                    if(sb.length() > 0){
                        sb.append("\n");
                    }
                    for(String s : str){
                        if(sb.length() >= 50){
                            break;
                        }
                        sb.append(s);
                    }
                    break;
                case "img":
                    if(img_id < 3){
                        img_linear1.setVisibility(View.VISIBLE);
                        img_list.get(img_id).setImageBitmap(null);
                        Glide.with(activity)
                                .load(pd.getText())
                                .apply(new RequestOptions()
                                        .error(R.drawable.glide_shibai)
                                        .fallback(R.drawable.glide_duqushibai))
                                .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                                .into(img_list.get(img_id));
                        img_list.get(img_id).setOnClickListener(V->{
                            查看图片窗口.启动(activity, pd.getText());
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
        if(sb.length() >=50){
            sb.append("...");
        }
        if(sb.length() > 0){
            TextView textView = new TextView(activity);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);
            textView.setText(sb.toString());
            textView.setTextIsSelectable(true);
            linear.addView(textView);
        }
        linear.addView(img_view);
        String finalTime_name = time_name;
        String final网址 = 网址;
        String finalPassWord_txt = PassWord_txt;
        button_message.setOnClickListener(V->{
            查看评论窗口.查看评论窗口(activity, finalTime_name, final网址, finalPassWord_txt);
        });

        String finalTime_name1 = time_name;
        button_collection.setOnClickListener(V->{
            NetWork_添加收藏 netWork_添加_收藏 = new NetWork_添加收藏(activity);
            netWork_添加_收藏.传递参数(finalTime_name1, post_data);
            netWork_添加_收藏.start();
        });
        String finalTime_name2 = time_name;
        view.setOnLongClickListener(V->{
            删除窗口.删除我的文章窗口(activity, finalTime_name2, view_home_essay);
            return true;
        });
        view.setOnClickListener(V->{
            able.传递数据 = post_data;
            Intent intent = new Intent();
            intent.setClass(activity, View_Post_Activity.class);
            activity.startActivity(intent);
        });
        return view;
    }

    public static View 创建收藏文章(Activity activity, List<Post_Data> post_data, View_Home_Collection view_HomeCollection){
        String time_name="";

        View view = View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);

        View img_view = View.inflate(activity, R.layout.create_post_img_layout, null);
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
        StringBuffer sb = new StringBuffer();
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
                        Glide.with(activity)
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
                    String [] str = pd.getText().replace("\n", "").replace("\r", "").split("");
                    if(sb.length() >=50){
                        break;
                    }
                    if(sb.length() > 0){
                        sb.append("\n");
                    }
                    for(String s : str){
                        if(sb.length() >= 50){
                            sb.append("...");
                            break;
                        }
                        sb.append(s);
                    }
                    break;
                case "img":
                    if(img_id < 3){
                        img_linear1.setVisibility(View.VISIBLE);
                        img_list.get(img_id).setImageBitmap(null);
                        Glide.with(activity)
                                .load(pd.getText())
                                .apply(new RequestOptions()
                                        .error(R.drawable.glide_shibai)
                                        .fallback(R.drawable.glide_duqushibai))
                                .transition(DrawableTransitionOptions.with(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                                .into(img_list.get(img_id));
                        img_list.get(img_id).setOnClickListener(V->{
                            查看图片窗口.启动(activity, pd.getText());
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
        if(sb.length() >=50){
            sb.append("...");
        }
        if(sb.length() > 0){
            TextView textView = new TextView(activity);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);
            textView.setText(sb.toString());
            textView.setTextIsSelectable(true);
            linear.addView(textView);
        }
        linear.addView(img_view);
        String finalTime_name = time_name;
        String final网址 = 网址;
        String finalPassWord_txt = PassWord_txt;
        button_message.setOnClickListener(V->{
            查看评论窗口.查看评论窗口(activity, finalTime_name, final网址, finalPassWord_txt);
        });

        button_collection.setVisibility(View.GONE);
        String finalTime_name2 = time_name;
        view.setOnLongClickListener(V->{
            删除窗口.删除收藏窗口(activity, finalTime_name2, view_HomeCollection);
            return true;
        });
        view.setOnClickListener(V->{
            able.传递数据 = post_data;
            Intent intent = new Intent();
            intent.setClass(activity, View_Post_Activity.class);
            activity.startActivity(intent);
        });
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
