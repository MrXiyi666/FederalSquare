package fun.android.federal_square.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import fun.android.federal_square.MainActivity;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.network.NetWork_登录;

public class View_Login extends View_Main{
    private TextView top_title;
    private EditText enroll_edit_account, enroll_edit_password;
    private AppCompatButton button_login, button_enroll;
    public View_Login(MainActivity activity) {
        super(activity);
    }

    @Override
    public void 初始化() {
        super.初始化();
        view = View.inflate(activity_main, R.layout.view_login, null);
        top_title = view.findViewById(R.id.top_title);
        button_login = view.findViewById(R.id.button_login);
        button_enroll = view.findViewById(R.id.button_enroll);
        enroll_edit_account = view.findViewById(R.id.enroll_edit_account);
        enroll_edit_password = view.findViewById(R.id.enroll_edit_password);
    }

    @Override
    public void 事件() {
        super.事件();
        top_title.setPadding(0, able.状态栏高度 / 2, 0, 0);
        button_enroll.setOnClickListener(V->{
            able.view_home.linear_main.removeAllViews();
            able.view_home.linear_main.addView(new View_Enroll(activity_main).getView());
        });
        button_login.setOnClickListener(V->{
            String account = enroll_edit_account.getText().toString();
            String password = enroll_edit_password.getText().toString();
            if(!account.isEmpty() && !password.isEmpty()){
                NetWork_登录 netWork_登录 = new NetWork_登录(activity_main);
                netWork_登录.传递参数(account, password);
                netWork_登录.start();
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
