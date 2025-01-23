package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;

public class 加载等待窗口 {
    private AlertDialog dialog;
    public 加载等待窗口(Activity activity){
        able.handler.post(()->{
            dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_load_toast_view, null);
            TextView text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            return_icon.setVisibility(View.GONE);
            view.setOnClickListener(V->{
                if(dialog!=null){
                    dialog.dismiss();
                    dialog = null;
                }
            });
            if(dialog == null){
                return;
            }
            new Thread(()->{
                int i=0;
                while (dialog != null && text_id!=null){
                    switch(i){
                        case 0:
                            text_id.post(()->{
                                text_id.setText("  ✍  ");
                            });
                            break;
                        case 1:
                            text_id.post(()->{
                                text_id.setText("  .✍  ");
                            });
                            break;
                        case 2:
                            text_id.post(()->{
                                text_id.setText("  ..✍  ");
                            });
                            break;
                        case 3:
                            text_id.post(()->{
                                text_id.setText("  ...✍  ");
                            });
                            break;
                        case 4:
                            text_id.post(()->{
                                text_id.setText("  ....✍  ");
                            });
                            break;
                        case 5:
                            text_id.post(()->{
                                text_id.setText("  .....✍  ");
                            });
                            break;
                        case 6:
                            text_id.post(()->{
                                text_id.setText("  ......✍  ");
                            });
                            break;
                    }
                    try {
                        Thread.sleep(300);
                        i++;
                        if(i > 6){
                            i=0;
                        }
                    }catch (Exception e){

                    }
                }
            }).start();
            dialog.setView(view);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.show();
        });
    }

    public void 关闭(){
        if(dialog==null){
            return;
        }
        dialog.dismiss();
        dialog = null;
    }
}
