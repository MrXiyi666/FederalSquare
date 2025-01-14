package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;

public class 广场菜单窗口 {
    public static int page_id = 0;
    public static void 打开窗口(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_square_menu_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        AppCompatButton up_page = view.findViewById(R.id.up_page);
        AppCompatButton down_page = view.findViewById(R.id.down_page);
        AppCompatButton top_page = view.findViewById(R.id.top_page);
        AppCompatButton refresh_page = view.findViewById(R.id.refresh_page);


        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });

        refresh_page.setOnClickListener(V->{
            able.view_square.初始化本地数据();
        });

        top_page.setOnClickListener(V->{
            able.view_square.scrollView.fullScroll(View.FOCUS_UP);
        });

        up_page.setOnClickListener(V->{

        });

        down_page.setOnClickListener(V->{

        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
}
