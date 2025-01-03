package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;

public class 查看图片窗口 {
    @SuppressLint("ResourceType")
    public static void 启动_Dialog(Activity activity, String url){
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_view_img, null);
        com.github.chrisbanes.photoview.PhotoView photoView = view.findViewById(R.id.photoView);
        photoView.getLayoutParams().width = able.宽度;
        photoView.getLayoutParams().height = able.高度;
        photoView.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .error(R.drawable.glide_shibai)
                        .fallback(R.drawable.glide_duqushibai))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                        photoView.setImageBitmap(resource);

                    }
                });
        photoView.setOnClickListener(V->{
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
