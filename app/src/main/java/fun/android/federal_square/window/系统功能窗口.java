package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.Objects;

import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class 系统功能窗口 {
    public void 启动(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_system_features_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch_system_features = view.findViewById(R.id.switch_system_features);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

        }else{
            switch_system_features.setVisibility(View.GONE);
        }

        switch_system_features.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Fun_文件.写入文件(able.app_path + "System_Data/System_Features.txt", "true");
            } else {
                Fun_文件.写入文件(able.app_path + "System_Data/System_Features.txt", "false");
            }
        });
        if(Fun_文件.是否存在(able.app_path + "System_Data/System_Features.txt")){
            String data = Fun_文件.读取文件(able.app_path + "System_Data/System_Features.txt");
            if(data.equals("true")){
                switch_system_features.setChecked(true);
            }else{
                switch_system_features.setChecked(false);
            }
        }

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
