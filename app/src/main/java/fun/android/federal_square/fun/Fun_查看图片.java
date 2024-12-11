package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.Objects;

import fun.android.federal_square.R;
import fun.android.federal_square.data.able;

public class Fun_查看图片 {
    public static PhotoView photoView;
    public static void 启动_Dialog(Activity activity, String url){
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_view_img, null);
        LinearLayout linear = view.findViewById(R.id.linear);
        com.github.chrisbanes.photoview.PhotoView photoView = view.findViewById(R.id.photoView);
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                        photoView.getLayoutParams().width = able.宽度;
                        photoView.getLayoutParams().height = able.高度;
                        photoView.setImageBitmap(resource);
                        photoView.requestLayout();
                    }
                });
        photoView.setOnClickListener(V->{
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


    public static void 启动(Activity activity, String url){
        photoView = null;
        photoView = activity.findViewById(R.id.photoView);
        photoView.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                        photoView.getLayoutParams().width = able.宽度;
                        photoView.getLayoutParams().height = able.高度;
                        photoView.setImageBitmap(resource);
                        photoView.requestLayout();
                    }
                });
        photoView.setOnClickListener(V->{
            photoView.setImageBitmap(null);
            photoView.setVisibility(View.GONE);
        });
    }
}
