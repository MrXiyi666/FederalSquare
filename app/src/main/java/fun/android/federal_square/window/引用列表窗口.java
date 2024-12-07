package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_文件;

public class 引用列表窗口 {
    public void 启动(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_yinyong_list_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        LinearLayout linear = view.findViewById(R.id.linear);
        AppCompatButton button_add = view.findViewById(R.id.button_add);
        EditText edit_text = view.findViewById(R.id.edit_text);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        List<String>list = 获取引用列表();
        linear.removeAllViews();
        for(String name : list){
            linear.addView(创建子布局(activity, name, linear));
        }
        button_add.setOnClickListener(V->{
            String txt = edit_text.getText().toString();
            txt = txt.replaceAll("\r\n|\r|\n", "");
            if(!txt.isEmpty()){
                edit_text.setText("");
                list.add(txt);
                Fun_文件.写入文件(able.app_path + "YinYong_Data/List.json", able.gson.toJson(list));
                linear.addView(创建子布局(activity, txt, linear), 0);
            }
        });

        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }
    public static List<String> 获取引用列表(){
        List<String> list = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "YinYong_Data/List.json"), new TypeToken<List<String>>(){});
        if(list == null){
            return new ArrayList<>();
        }
        return list;
    }
    public View 创建子布局(Activity activity, String txt, LinearLayout linear){
        View view = View.inflate(activity, R.layout.view_yinyong_list_item_view, null);
        EditText edit_text = view.findViewById(R.id.edit_text);
        edit_text.setText(txt);
        edit_text.setEnabled(false);
        AppCompatButton button_system = view.findViewById(R.id.button_system);
        button_system.setOnClickListener(V->{
           if(button_system.getText().equals("修改")){
               edit_text.setEnabled(true);
               button_system.setText("确认");
           }else{
               edit_text.setEnabled(false);
               button_system.setText("修改");
               if(edit_text.getText().toString().isEmpty()){
                   Fun_文件.删除文件(able.app_path + "YinYong_Data/" + txt);
                   linear.removeAllViews();
                   for(String name : 获取引用列表()){
                       linear.addView(创建子布局(activity, name, linear));
                   }
               }

           }
        });
        return view;
    }
}
