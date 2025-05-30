package fun.android.federal_square.window;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;
import java.util.Objects;
import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.View_Essay;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_删除网盘图片;
import fun.android.federal_square.network.NetWork_我的_收藏_删除;
import fun.android.federal_square.network.NetWork_我的_文章_删除;
import fun.android.federal_square.network.NetWork_我的所有收藏_删除;
import fun.android.federal_square.network.NetWork_我的所有文章_删除;
import fun.android.federal_square.view.Post_View;
import fun.android.federal_square.view.Video_ImageView;

public class 删除窗口 {

    public static void 删除本地文章窗户(Activity activity, String name, Post_View postView){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);
        TextView text = view.findViewById(R.id.text);
        text.setText("这只会删除设备上的本地数据");
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            dialog.dismiss();
            Fun_文件.删除文件(able.app_path + "Square_Data/" + name + ".json");
            able.view_square.linear.removeView(postView);
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除网盘图片窗口(Activity activity, String name){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            var netWork_删除网盘图片 = new NetWork_删除网盘图片(activity);
            netWork_删除网盘图片.传递参数(name, (DiskActivity) activity);
            netWork_删除网盘图片.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((V1, keyCode, V3) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除收藏窗口(Activity activity, String time){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);

        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            NetWork_我的_收藏_删除 netWork_我的_收藏_删除 = new NetWork_我的_收藏_删除(activity);
            netWork_我的_收藏_删除.传递参数(Fun_账号.GetID(), time);
            netWork_我的_收藏_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((V1, keyCode, V3) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除所有收藏窗口(Activity activity, String time){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);

        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            NetWork_我的所有收藏_删除 netWork_我的所有收藏_删除 = new NetWork_我的所有收藏_删除(activity);
            netWork_我的所有收藏_删除.传递参数(Fun_账号.GetID(), time, (View_Collectin) activity);
            netWork_我的所有收藏_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((V1, keyCode, V3) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除我的文章窗口(Activity activity, String time){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);

        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            NetWork_我的_文章_删除 netWork_我的_文章_删除 = new NetWork_我的_文章_删除(activity);
            netWork_我的_文章_删除.传递参数(Fun_账号.GetID(), time);
            netWork_我的_文章_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((V1, keyCode, V3) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除所有文章窗口(Activity activity, String time){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);

        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            NetWork_我的所有文章_删除 netWork_我的所有文章_删除 = new NetWork_我的所有文章_删除(activity);
            netWork_我的所有文章_删除.传递参数(Fun_账号.GetID(), time, (View_Essay) activity);
            netWork_我的所有文章_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((V1, keyCode, V3) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss();
                return true;
            }
            return false;
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 发表文章删除元素窗口(Activity activity, LinearLayout linear, TextView textView,  List<Post_Data> post_dataList, Post_Data post_data){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);
        textView.setBackgroundColor(Color.rgb(242,243,247));
        button_ok.setOnClickListener(V->{
            var vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(10);
            linear.removeView(textView);
            post_dataList.remove(post_data);
            dialog.dismiss();
        });
        button_no.setOnClickListener(V->{
            textView.setBackgroundColor(Color.TRANSPARENT);
            dialog.dismiss();
        });
        return_icon.setOnClickListener(V->{
            textView.setBackgroundColor(Color.TRANSPARENT);
            dialog.dismiss();
        });
        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 发表文章删除元素窗口(Activity activity, LinearLayout linear, Video_ImageView imageView, List<Post_Data> post_dataList, Post_Data post_data){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_delete_file, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_no = view.findViewById(R.id.button_no);
        imageView.setBackgroundColor(Color.rgb(242,243,247));
        button_ok.setOnClickListener(V->{
            var vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(10);
            imageView.setImageBitmap(null);
            linear.removeView(imageView);
            post_dataList.remove(post_data);
            dialog.dismiss();
        });
        button_no.setOnClickListener(V->{
            imageView.setBackgroundColor(Color.TRANSPARENT);
            dialog.dismiss();
        });
        return_icon.setOnClickListener(V->{
            imageView.setBackgroundColor(Color.TRANSPARENT);
            dialog.dismiss();
        });
        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
