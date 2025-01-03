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
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看视频窗口;
import fun.android.federal_square.window.查看评论窗口;

public class Fun_文章 {

    public static View Create_Post_View(Activity activity, List<Post_Data> post_data, int index){
        View view = View.inflate(activity, R.layout.create_post_layout, null);
        LinearLayout button_message = view.findViewById(R.id.button_message);
        LinearLayout button_collection = view.findViewById(R.id.button_collection);
        View img_view = View.inflate(activity, R.layout.create_post_img_layout, null);
        LinearLayout img_linear1 = img_view.findViewById(R.id.img_linear1);
        TextView name_view = view.findViewById(R.id.name);
        TextView sign_view = view.findViewById(R.id.sign);
        RoundImageView avatar_img = view.findViewById(R.id.avatar_img);
        TextView url_txt_id = view.findViewById(R.id.url_txt_id);
        List<ImageView> img_list = new ArrayList<>();
        img_list.add(img_view.findViewById(R.id.img0));
        img_list.add(img_view.findViewById(R.id.img1));
        img_list.add(img_view.findViewById(R.id.img2));
        LinearLayout linear = view.findViewById(R.id.linear);

        StringBuffer sb = new StringBuffer();
        int img_id=0;
        String url_txt="";
        String PassWord_txt="";
        String time_txt = "";
        for(Post_Data pd : post_data){
            switch(pd.getName()){
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
                                .asBitmap()
                                .load(pd.getText())
                                .into(avatar_img);
                    }
                    break;
                case "text":
                    String [] str = pd.getText().replace("\n", " ").replace("\r", " ").split("");
                    if(sb.length() >=50){
                        break;
                    }
                    if(!sb.toString().isEmpty()){
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
                            .asBitmap()
                            .load(pd.getText())
                            .apply(new RequestOptions()
                                    .error(R.drawable.glide_shibai)
                                    .fallback(R.drawable.glide_duqushibai))
                            .into(img_list.get(img_id));
                    img_list.get(img_id).setOnClickListener(V->{
                        String 后缀 = Fun_文件.获取后缀(pd.getText());
                        if(后缀.equals("jpg") | 后缀.equals("jpeg") | 后缀.equals("png") | 后缀.equals("webp")){
                            查看图片窗口.启动_Dialog(activity, pd.getText());
                        }
                        if(后缀.equals("mp4") | 后缀.equals("3gp") | 后缀.equals("mov") | 后缀.equals("avi") | 后缀.equals("mkv")){
                            查看视频窗口.启动_Dialog(activity, pd.getText());
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
            }
        }
        if(!able.URL.equals(url_txt)){
            url_txt_id.setText(url_txt);
            url_txt_id.setVisibility(View.VISIBLE);
        }

        if(sb.length() >=50){
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
