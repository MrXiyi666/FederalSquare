package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.io.File;
import java.util.Objects;

import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_我的_个人信息;
import fun.android.federal_square.view.View_Login;

public class 个人信息窗口 {
    public static void 修改个人信息(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_personal_information_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText edit_name = view.findViewById(R.id.edit_name);
        EditText edit_sign = view.findViewById(R.id.edit_sign);
        EditText edit_password = view.findViewById(R.id.edit_password);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_delete = view.findViewById(R.id.button_delete);
        TextView text_url = view.findViewById(R.id.text_url);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        text_url.setText("我的网址: "+able.URL_Name + "\n我的密码: " + able.Read_PassWord);
        button_ok.setOnClickListener(V->{
            String name_txt="";
            String sign_txt="";
            String password_txt="";
            if(edit_name.getText().toString().isEmpty()){
                name_txt = Fun_账号.GetName();
            }else{
                name_txt = edit_name.getText().toString();
            }
            if(edit_sign.getText().toString().isEmpty()){
                sign_txt = Fun_账号.GetSign();
            }else{
                sign_txt = edit_sign.getText().toString();
            }
           if(edit_password.getText().toString().isEmpty()){
               password_txt = Fun_账号.GetPassWord();
           }else{
               password_txt = edit_password.getText().toString();
           }
            NetWork_我的_个人信息 netWork_我的_个人信息 = new NetWork_我的_个人信息(activity);
            netWork_我的_个人信息.传递参数(name_txt, password_txt, sign_txt, dialog);
            netWork_我的_个人信息.start();

        });

        button_delete.setOnClickListener(V->{
            Fun_文件.删除文件夹(new File(able.app_path + "Account"));
            Fun_文件.删除文件夹(new File(able.app_path + "Disk_Data"));
            Fun_文件.创建文件夹(able.app_path + "Account");
            Fun_文件.创建文件夹(able.app_path + "Account/Data");
            Fun_文件.创建文件夹(able.app_path + "Disk_Data");
            Fun_文件.创建文件夹(able.app_path + "Account/Collection");
            able.view_home.linear_main.removeAllViews();
            able.view_home.linear_main.addView(new View_Login((MainActivity) activity).getView());
            Fun.mess(activity, "成功登出");
            dialog.dismiss();
        });


        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }
}
