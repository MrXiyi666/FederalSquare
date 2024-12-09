package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.io.File;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class 重新设置域名窗口 {
    public void 启动(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_url_setting_view, null);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_exit = view.findViewById(R.id.button_exit);
        button_ok.setOnClickListener(V->{
            Fun_文件.删除文件夹(new File(able.app_path + "Disk_Data"));
            Fun_文件.删除文件夹(new File(able.app_path + "Hot_Data"));
            Fun_文件.删除文件夹(new File(able.app_path + "Square_Data"));
            Fun_文件.删除文件夹(new File(able.app_path + "YinYong_Data"));
            Fun_文件.删除文件夹(new File(able.app_path + "Account"));
            Fun_文件.删除文件(able.app_path + "URL_Name.txt");
            System.exit(0);
        });
        button_exit.setOnClickListener(V->{
            Fun_文件.写入文件(able.app_path + "url_setting_boolean.txt", "");
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
