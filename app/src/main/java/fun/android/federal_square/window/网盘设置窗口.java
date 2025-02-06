package fun.android.federal_square.window;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class 网盘设置窗口 {
    public static void 启动(DiskActivity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_disk_menu, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        EditText edit_text_1 = view.findViewById(R.id.edit_text_1);
        AppCompatButton button_edit_1 = view.findViewById(R.id.button_edit_1);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        button_edit_1.setOnClickListener(V->{
            String str = edit_text_1.getText().toString();
            if(str.isEmpty()){
                return;
            }
            int index = Integer.parseInt(str);
            Fun_文件.写入文件(able.app_path + "System_Data/Disk_index.txt", str);
            activity.gridView.setNumColumns(index);
            activity.初始化数据();
            dialog.dismiss();
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
