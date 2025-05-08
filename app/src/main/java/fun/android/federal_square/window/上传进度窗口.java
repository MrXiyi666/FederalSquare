package fun.android.federal_square.window;

import android.annotation.SuppressLint;
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
import fun.android.federal_square.fun.Fun;

public class 上传进度窗口 {
    private AlertDialog dialog;
    private TextView text_id;
    private Activity activity;
    public 上传进度窗口(Activity activity,Thread 线程){
        this.activity = activity;
        activity.runOnUiThread(()->{
            dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Loading).create();
            View view = View.inflate(activity, R.layout.window_load_toast_view, null);
            text_id = view.findViewById(R.id.text_id);
            ImageView return_icon = view.findViewById(R.id.return_icon);
            return_icon.setVisibility(View.GONE);
            view.setOnClickListener(V->{
                Fun.mess(activity, "关闭弹窗 上传依然在继续 直到成功或失败", 3000);
                if(dialog!=null){
                    dialog.dismiss();
                    dialog = null;
                }
            });
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.TOP);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setView(view);
            dialog.setCancelable(false);
            dialog.show();
            线程.start();
        });
    }

    @SuppressLint("SetTextI18n")
    public void 提交进度(int index, boolean done){
        activity.runOnUiThread(()->{
            if(dialog!=null && text_id!=null){
                if(!done){
                    text_id.setText("上传进度 " + index + " / 100" );
                }else{
                    String txt = "飞速奔跑中\n\n";
                    new Thread(()->{
                        int i=0;
                        while (dialog != null && text_id!=null){
                            switch(i){
                                case 0:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  ✍  ");
                                    });
                                    break;
                                case 1:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等.✍  ");
                                    });
                                    break;
                                case 2:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等待.✍  ");
                                    });
                                    break;
                                case 3:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等待返.✍  ");
                                    });
                                    break;
                                case 4:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等待返回.✍  ");
                                    });
                                    break;
                                case 5:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等待返回结.✍  ");
                                    });
                                    break;
                                case 6:
                                    text_id.post(()->{
                                        text_id.setText(txt + "  等待返回结果.✍  ");
                                    });
                                    break;
                            }
                            try {
                                Thread.sleep(300);
                            }catch (Exception e){

                            }
                            i++;
                            if(i > 6){
                                i=0;
                            }
                        }
                    }).start();
                }
            }
        });
    }
    public void 关闭(){
        new Thread(()->{
            if(dialog==null){
                return;
            }
            dialog.dismiss();
            dialog = null;
        }).start();
    }
}
