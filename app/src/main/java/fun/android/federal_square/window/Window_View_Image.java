package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import java.io.File;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.system.Fun;

public class Window_View_Image {
    public Window_View_Image(Activity activity, String img_path){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_view_img, null);
        com.github.chrisbanes.photoview.PhotoView photoView = view.findViewById(R.id.photoView);
        photoView.getLayoutParams().width = Fun.屏幕宽度(activity);
        photoView.getLayoutParams().height = Fun.屏幕高度(activity);
        photoView.setVisibility(View.VISIBLE);
        photoView.setOnClickListener(V->{
            dialog.dismiss();
            // 清除 ImageView 显示的图片
            photoView.setImageDrawable(null);
            // 或清除资源引用（效果相同）
            photoView.setImageResource(0);
        });

        view.setOnLongClickListener(V->{
            dialog.dismiss();
            // 清除 ImageView 显示的图片
            photoView.setImageDrawable(null);
            // 或清除资源引用（效果相同）
            photoView.setImageResource(0);
            return true;
        });

        File imageFile = new File(img_path);
        if (imageFile.exists()) {
            Uri imageUri = Uri.fromFile(imageFile);
            photoView.setImageURI(imageUri);
        } else {
            dialog.dismiss();
            // 清除 ImageView 显示的图片
            photoView.setImageDrawable(null);
            // 或清除资源引用（效果相同）
            photoView.setImageResource(0);
        }
        dialog.setView(view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
