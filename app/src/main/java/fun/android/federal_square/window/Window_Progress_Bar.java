package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.child_view.CircularProgressView;

public class Window_Progress_Bar {
    private Activity activity;
    private AlertDialog dialog;
    private CircularProgressView progress_view;
    public Window_Progress_Bar(Activity activity){
        this.activity = activity;
        dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_progress_bar_view, null);
        progress_view = view.findViewById(R.id.circularProgress);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void setProgress(int progress){
        progress_view.setProgress(progress);
    }

    public void close(){
        dialog.dismiss();
    }
}
