package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;

import java.util.Objects;

import fun.android.federal_square.R;
import fun.android.federal_square.data.able;

public class 窗口测试 {
    public 窗口测试(Activity activity){
        var dialog = new AlertDialog.Builder(activity).create();
        var view = View.inflate(activity, R.layout.window_ceshi, null);
        var ding_view = view.findViewById(R.id.ding_view);
        ViewGroup.LayoutParams layoutParams = ding_view.getLayoutParams();
        layoutParams.height = able.状态栏高度;
        ding_view.setLayoutParams(layoutParams);
        dialog.setView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.TOP);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
