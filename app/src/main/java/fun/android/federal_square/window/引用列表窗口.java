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
import java.util.List;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.URL_PassWord_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;

public class 引用列表窗口 {
    public void 启动(Activity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_yinyong_list_view, null);
        ImageView return_icon = view.findViewById(R.id.return_icon);
        LinearLayout linear = view.findViewById(R.id.linear);
        AppCompatButton button_add = view.findViewById(R.id.button_add);
        EditText edit_text = view.findViewById(R.id.edit_text);
        EditText edit_text_password = view.findViewById(R.id.edit_text_password);
        return_icon.setOnClickListener(V->{
            dialog.dismiss();
        });
        List<URL_PassWord_Data>list = 获取引用列表();
        linear.removeAllViews();
        for(URL_PassWord_Data url_passWord_data : list){
            linear.addView(创建子布局(activity, url_passWord_data, linear, list));
        }
        button_add.setOnClickListener(V->{
            String txt = edit_text.getText().toString();
            String txt_password = edit_text_password.getText().toString();


            txt = txt.replaceAll("\\s+", "");
            if(!txt.isEmpty()){
                URL_PassWord_Data urlPassWordData = new URL_PassWord_Data();
                urlPassWordData.setURL(txt);
                urlPassWordData.setPassWord(txt_password);
                edit_text.setText("");
                edit_text_password.setText("");
                list.add(urlPassWordData);
                Fun_文件.写入文件(able.app_path + "YinYong_Data/List.json", able.gson.toJson(list));
                linear.addView(创建子布局(activity, urlPassWordData, linear, list), 0);
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
    public static List<URL_PassWord_Data> 获取引用列表(){
        String str = Fun_文件.读取文件(able.app_path + "YinYong_Data/List.json");
        if(!Fun.StrBoolJSON(str)){
            return new ArrayList<>();
        }
        List<URL_PassWord_Data> list = able.gson.fromJson(str, new TypeToken<List<URL_PassWord_Data>>() {});
        if(list == null){
            Fun_文件.写入文件(able.app_path + "YinYong_Data/List.json", able.gson.toJson(new ArrayList<>()));
            return new ArrayList<>();
        }
        return list;
    }
    public View 创建子布局(Activity activity, URL_PassWord_Data url_passWord_data, LinearLayout linear, List<URL_PassWord_Data> list){
        View view = View.inflate(activity, R.layout.view_yinyong_list_item_view, null);
        EditText edit_text = view.findViewById(R.id.edit_text);
        edit_text.setText(url_passWord_data.getURL());
        edit_text.setEnabled(false);
        AppCompatButton button_system = view.findViewById(R.id.button_system);
        button_system.setOnClickListener(V->{
            list.remove(url_passWord_data);
            Fun_文件.写入文件(able.app_path + "YinYong_Data/List.json", able.gson.toJson(list));
            linear.removeView(view);
        });
        return view;
    }
}
