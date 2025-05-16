package fun.android.federal_square.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.network.NetWork_注册;

public class View_Enroll extends View_Main{
    private TextView top_title;
    private EditText enroll_edit_account, enroll_edit_password, enroll_edit_password2;
    private AppCompatButton button_login, button_enroll;
    public View_Enroll(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_enroll, null);
        top_title = view.findViewById(R.id.top_title);
        button_login = view.findViewById(R.id.button_login);
        button_enroll = view.findViewById(R.id.button_enroll);
        enroll_edit_account = view.findViewById(R.id.enroll_edit_account);
        enroll_edit_password = view.findViewById(R.id.enroll_edit_password);
        enroll_edit_password2 = view.findViewById(R.id.enroll_edit_password2);
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, Fun.获取状态栏高度(activity_main) / 2, 0, 0);
        button_login.setOnClickListener(V->{
            able.view_home.linear_main.removeAllViews();
            able.view_home.linear_main.addView(new View_Login(activity_main).getView());
        });

        button_enroll.setOnClickListener(V->{
            var account_text = enroll_edit_account.getText().toString();
            var password = enroll_edit_password.getText().toString();
            var password2 = enroll_edit_password2.getText().toString();

            if(!account_text.isEmpty() && !password.isEmpty() && !password2.isEmpty() && password.equals(password2)){
                var post_id = new Post_Data();
                post_id.setName("ID");
                post_id.setText(enroll_edit_account.getText().toString());
                Post_Data post_password = new Post_Data();
                post_password.setName("PassWord");
                post_password.setText(password);
                Post_Data post_avatar_url = new Post_Data();
                post_avatar_url.setName("Avatar_Url");
                post_avatar_url.setText("");
                Post_Data post_back_url = new Post_Data();
                post_back_url.setName("Back_Url");
                post_back_url.setText("");
                Post_Data post_name = new Post_Data();
                post_name.setName("Name");
                post_name.setText("新手玩家");
                Post_Data post_sign = new Post_Data();
                post_sign.setName("Sign");
                post_sign.setText("言论自由");
                Post_Data 发贴开关 = new Post_Data();
                发贴开关.setName("发贴开关");
                发贴开关.setText("true");
                Post_Data 评论开关 = new Post_Data();
                评论开关.setName("评论开关");
                评论开关.setText("true");
                List<Post_Data> post_dataList = new ArrayList<>();
                post_dataList.add(post_id);
                post_dataList.add(post_password);
                post_dataList.add(post_avatar_url);
                post_dataList.add(post_back_url);
                post_dataList.add(post_name);
                post_dataList.add(post_sign);
                post_dataList.add(发贴开关);
                post_dataList.add(评论开关);
                var netWork_注册 = new NetWork_注册(activity_main);
                netWork_注册.传递参数(post_dataList);
                netWork_注册.start();
            }else{
                Fun.mess(activity_main, "错误");
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void 释放() {
        super.释放();
    }
}
