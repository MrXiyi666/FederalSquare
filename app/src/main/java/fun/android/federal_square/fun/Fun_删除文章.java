package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.View_Article;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.network.NetWork_我的_文章_删除;
import fun.android.federal_square.network.NetWork_我的所有文章_删除;
import fun.android.federal_square.view.View_Home_Article;

public class Fun_删除文章 {

    public static void 启动(Activity activity, String time, View_Home_Article view_HomeArticle){
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
            netWork_我的_文章_删除.传递参数(Fun_账号.GetID(), time, view_HomeArticle);
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

    public static void 所有启动(View_Article activity, String time){
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
