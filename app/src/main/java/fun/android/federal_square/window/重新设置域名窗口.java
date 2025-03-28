package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class 重新设置域名窗口 {
    public void 启动(Activity activity, ImageView button_url_setting){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_url_setting_view, null);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        AppCompatButton button_exit = view.findViewById(R.id.button_exit);
        ImageView return_icon = view.findViewById(R.id.return_icon);

        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_ok.setOnClickListener(V->{
            Glide.get(activity).clearMemory();
            new Thread(()->{
                Glide.get(activity).clearDiskCache();
                Fun_文件.删除文件夹(new File(able.app_path + "System_Data"));
                Fun_文件.删除文件夹(new File(able.app_path + "Disk_Data"));
                Fun_文件.删除文件夹(new File(able.app_path + "Hot_Data"));
                Fun_文件.删除文件夹(new File(able.app_path + "Square_Data"));
                Fun_文件.删除文件夹(new File(able.app_path + "YinYong_Data"));
                Fun_文件.删除文件夹(new File(able.app_path + "Account"));
                Fun_文件.删除文件夹(new File(able.app_path + "video_cache"));
                Fun_文件.删除文件夹(new File(able.app_path + "cache"));
                able.handler.post(()->{
                    System.exit(0);
                });
            }).start();
        });
        button_exit.setOnClickListener(V->{
            Fun_文件.写入文件(able.app_path + "System_Data/url_setting_boolean.txt", "");
            button_url_setting.setVisibility(View.GONE);
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
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
