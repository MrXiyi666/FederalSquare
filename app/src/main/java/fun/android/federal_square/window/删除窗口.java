package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Collectin;
import fun.android.federal_square.View_Essay;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_删除网盘图片;
import fun.android.federal_square.network.NetWork_我的_收藏_删除;
import fun.android.federal_square.network.NetWork_我的_文章_删除;
import fun.android.federal_square.network.NetWork_我的所有收藏_删除;
import fun.android.federal_square.network.NetWork_我的所有文章_删除;
import fun.android.federal_square.view.View_Home_Collection;
import fun.android.federal_square.view.View_Home_Essay;

public class 删除窗口 {
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
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除所有收藏窗口(View_Collectin activity, String time){
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
            netWork_我的所有收藏_删除.传递参数(Fun_账号.GetID(), time, activity);
            netWork_我的所有收藏_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public static void 删除所有文章窗口(View_Essay activity, String time){
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
            netWork_我的所有文章_删除.传递参数(Fun_账号.GetID(), time, activity);
            netWork_我的所有文章_删除.start();
            dialog.dismiss();
        });

        button_no.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
