package fun.android.federal_square.window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
        AlertDialog load_dialog = Fun.初始化等待窗口(activity);
        load_dialog.show();
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_view_img, null);
        com.github.chrisbanes.photoview.PhotoView photoView = view.findViewById(R.id.photoView);
        photoView.getLayoutParams().width = able.宽度;
        photoView.getLayoutParams().height = able.高度;
        photoView.setVisibility(View.VISIBLE);

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
                            load_dialog.dismiss();
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
                            load_dialog.dismiss();
                        }).start();
                        return false;
                    }
                })
                .into(photoView);

        photoView.setOnClickListener(V->{
            dialog.dismiss();
        });
        view.setOnLongClickListener(V->{
            dialog.dismiss();
            load_dialog.dismiss();

            return true;
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
