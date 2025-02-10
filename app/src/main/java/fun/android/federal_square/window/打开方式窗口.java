package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Objects;

import fun.android.federal_square.R;

public class 打开方式窗口 {
    public static void 启动(Activity activity, String url){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_select_open, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton button_img_open = view.findViewById(R.id.button_img_open);
        AppCompatButton button_video_open = view.findViewById(R.id.button_video_open);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        button_img_open.setOnClickListener(V->{
            dialog.dismiss();
            查看图片窗口.启动_Dialog(activity, url);
        });

        button_video_open.setOnClickListener(V->{
            dialog.dismiss();
            查看视频窗口.启动_Dialog(activity, url);
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setOnKeyListener((_, keyCode, _) -> {
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
}
