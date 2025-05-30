package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;

public class 查看图片窗口 {
    @SuppressLint("ResourceType")
    public static void 启动_Dialog(Activity activity, String url){
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity, "没有登陆 无法查看");
            return;
        }

        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_view_img, null);
        com.github.chrisbanes.photoview.PhotoView photoView = view.findViewById(R.id.photoView);
        photoView.getLayoutParams().width = Fun.屏幕宽度(activity);
        photoView.getLayoutParams().height = Fun.屏幕高度(activity);
        photoView.setVisibility(View.VISIBLE);
        photoView.setOnClickListener(V->{
            dialog.dismiss();
        });
        Glide.with(activity)
                .load(url)
                .apply(able.requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        new Thread(() -> {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e1) {
                                throw new RuntimeException(e1);
                            }
                            dialog.dismiss();
                        }).start();
                        Fun.mess(activity, "加载失败");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        new Thread(() -> {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }).start();
                        return false;
                    }
                })
                .into(photoView);
        view.setOnLongClickListener(V->{
            dialog.dismiss();
            return true;
        });
        dialog.setView(view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }
}
