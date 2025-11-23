package fun.android.federal_square.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import fun.android.federal_square.R;
import fun.android.federal_square.child_view.CircularProgressView;

public class Progress_View {

    public TextView text_view;
    private View view;
    public CircularProgressView circularProgressView;
    public Progress_View(Context context){
        view = View.inflate(context, R.layout.progress_view, null);
        circularProgressView = view.findViewById(R.id.circularProgress);
        text_view = view.findViewById(R.id.text_view);
        circularProgressView = view.findViewById(R.id.circularProgress);
    }

    public void setProgress(int progress){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                circularProgressView.setProgress(progress);
            }
        });
    }

    public void setText(String text){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                text_view.setText(text);
            }
        });
    }

    public View getView() {
        return view;
    }
}
