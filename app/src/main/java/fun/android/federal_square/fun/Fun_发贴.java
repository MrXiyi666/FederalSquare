package fun.android.federal_square.fun;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import fun.android.federal_square.R;
import fun.android.federal_square.adatper.Disk_Grid_Adapter;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_广场上传;

public class Fun_发贴 {
    private LinearLayout linear;
    public AlertDialog dialog;
    private List<Post_Data> post_dataList;
    public void 创建发贴窗口(Activity activity ){

        dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_post_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText edit_text = view.findViewById(R.id.edit_text);
        AppCompatButton add_img = view.findViewById(R.id.add_img);
        AppCompatButton add_text = view.findViewById(R.id.add_text);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        linear = view.findViewById(R.id.linear);
        post_dataList = new ArrayList<>();
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        add_img.setOnClickListener(V->{
            选择图片(activity);
        });
        add_text.setOnClickListener(V->{
            String text_data = edit_text.getText().toString();
            if(!text_data.isEmpty()){
                Post_Data post_data = new Post_Data();
                post_data.setName("text");
                post_data.setText(text_data);
                TextView textView = new TextView(activity);
                textView.setTextSize(18);
                textView.setText(text_data);
                textView.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 10);
                textView.setLayoutParams(params);
                textView.setOnLongClickListener(tV->{
                    Vibrator vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(10);
                    linear.removeView(textView);
                    post_dataList.remove(post_data);
                    return true;
                });
                post_dataList.add(post_data);
                linear.addView(textView);
                edit_text.setText("");
            }else{
                Fun.mess(activity, "数据为空");
            }

        });

        button_ok.setOnClickListener(V->{
            if(!post_dataList.isEmpty()){
                NetWork_广场上传 netWork_广场上传 = new NetWork_广场上传(activity);
                Post_Data name = new Post_Data();
                name.setName("name");
                name.setText(Fun_账号.GetName());
                Post_Data sign = new Post_Data();
                sign.setName("sign");
                sign.setText(Fun_账号.GetSign());
                Post_Data avatar = new Post_Data();
                avatar.setName("avatar");
                avatar.setText(Fun_账号.GetAvatar_Url());
                Post_Data time = new Post_Data();
                time.setName("time");
                time.setText(Fun.获取时间());
                Post_Data url = new Post_Data();
                url.setName("url");
                url.setText(able.URL_Name);
                Post_Data password = new Post_Data();
                password.setName("password");
                password.setText(able.Read_PassWord);
                post_dataList.add(name);
                post_dataList.add(sign);
                post_dataList.add(avatar);
                post_dataList.add(time);
                post_dataList.add(url);
                post_dataList.add(password);
                netWork_广场上传.传递数据(post_dataList, time.getText());
                netWork_广场上传.start();
            }else{
                Fun.mess(activity, "内容为空");
            }
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public void 选择图片(Activity activity){
        AlertDialog 选择图片窗口句柄 = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_select_image_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        GridView gridview = view.findViewById(R.id.gridview);
        return_icon.setOnClickListener(V->{
            选择图片窗口句柄.dismiss();
        });
        List<String> list = 遍历所有图片();
        if(list.isEmpty()){
            Fun.mess(activity, "网盘数据为空");
            return;
        }
        gridview.setAdapter(new Disk_Grid_Adapter(activity, list));
        gridview.setOnItemClickListener((adapterView, view1, position, l) -> {
            Post_Data post_data = new Post_Data();
            post_data.setName("img");
            post_data.setText(list.get(position));
            ImageView imageView = new ImageView(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(able.宽度 / 2, able.宽度 / 2);
            imageView.setLayoutParams(layoutParams);
            Glide.with(activity)
                    .load(list.get(position))
                    .into(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 10);
            imageView.setLayoutParams(params);
            imageView.setOnLongClickListener(V->{
                Vibrator vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(10);
                linear.removeView(imageView);
                post_dataList.remove(post_data);
                return true;
            });
            imageView.setOnClickListener(V->{
                Fun_查看图片.启动_Dialog(activity, list.get(position));
            });
            post_dataList.add(post_data);
            linear.addView(imageView);
            选择图片窗口句柄.dismiss();
        });

        选择图片窗口句柄.setView(view);
        选择图片窗口句柄.setCancelable(true);
        Objects.requireNonNull(选择图片窗口句柄.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        选择图片窗口句柄.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        选择图片窗口句柄.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        选择图片窗口句柄.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        选择图片窗口句柄.getWindow().setGravity(Gravity.CENTER);
        选择图片窗口句柄.show();

    }

    public List<String> 遍历所有图片(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        Comparator<String> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        List<String> return_list = new ArrayList<>();
        for(String name : list){
            return_list.add(able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + name);
        }
        return return_list;
    }
}