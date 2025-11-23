package fun.android.federal_square.window;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import java.util.Objects;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.view.Progress_View;

public class Window_Progress_Bar {
    private Dialog dialog;
    protected Activity activity;

    private Progress_View progress_view;
    public Window_Progress_Bar(Activity activity){
        this.activity = activity;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                progress_view = new Progress_View(activity);
                dialog.setContentView(progress_view.getView());
                dialog.setCancelable(false);
                Fun.setWindowTheme(activity, Objects.requireNonNull(dialog.getWindow()));
                dialog.show();
            }
        });
    }

    public void setProgress(int progress){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress_view.setProgress(progress);
            }
        });

    }

    public void setText(String text){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress_view.setText(text);
            }
        });
    }
    public void close(){
        progress_view.circularProgressView.post(()->{
            progress_view.circularProgressView.setVisibility(View.GONE);
        });
        progress_view.text_view.post(()->{
            progress_view.text_view.setVisibility(View.VISIBLE);
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }
}
