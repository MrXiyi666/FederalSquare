package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;

public class 文章设置窗口 {
    public static void 显示(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_essay_system, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText square_edit = view.findViewById(R.id.square_edit);
        AppCompatButton button_square = view.findViewById(R.id.button_square);
        EditText hot_edit = view.findViewById(R.id.hot_edit);
        AppCompatButton button_hot = view.findViewById(R.id.button_hot);
        EditText home_essay_edit = view.findViewById(R.id.home_essay_edit);
        AppCompatButton button_home_essay = view.findViewById(R.id.button_home_essay);
        EditText home_collection_edit = view.findViewById(R.id.home_collection_edit);
        AppCompatButton button_home_collection = view.findViewById(R.id.button_home_collection);
        EditText time_edit = view.findViewById(R.id.time_edit);
        AppCompatButton button_time = view.findViewById(R.id.button_time);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        button_square.setOnClickListener(V->{
            String txt = square_edit.getText().toString();
            if(!txt.isEmpty()){
                Fun_文件.写入文件(able.app_path + "System_Data/Essay_index.txt", txt);
                Fun.mess(activity, "修改为" + txt);
            }
        });
        button_hot.setOnClickListener(V->{
            String txt = hot_edit.getText().toString();
            if(!txt.isEmpty()){
                Fun_文件.写入文件(able.app_path + "System_Data/Hot_Essay_index.txt", txt);
                Fun.mess(activity, "修改为" + txt);
            }
        });
        button_home_essay.setOnClickListener(V->{
            String txt = home_essay_edit.getText().toString();
            if(!txt.isEmpty()){
                Fun_文件.写入文件(able.app_path + "System_Data/Home_Essay_index.txt", txt);
                Fun.mess(activity, "修改为" + txt);
            }
        });
        button_home_collection.setOnClickListener(V->{
            String txt = home_collection_edit.getText().toString();
            if(!txt.isEmpty()){
                Fun_文件.写入文件(able.app_path + "System_Data/Home_Collection_Essay_index.txt", txt);
                Fun.mess(activity, "修改为" + txt);
            }
        });
        button_time.setOnClickListener(V->{
            String txt = time_edit.getText().toString();
            if(!txt.isEmpty()){
                Fun_文件.写入文件(able.app_path + "System_Data/Time_index.txt", txt);
                Fun.mess(activity, "修改为" + txt);
            }
        });
        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
