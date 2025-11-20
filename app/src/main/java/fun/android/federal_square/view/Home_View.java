package fun.android.federal_square.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import net.csdn.roundview.RoundImageView;

import fun.android.federal_square.R;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;

public class Home_View extends View_Main{
    private LinearLayout linear;
    public Home_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.home_view, null);
        linear = view.findViewById(R.id.linear);

        RelativeLayout relati_view = view.findViewById(R.id.relati_view);
        RelativeLayout personal_view = view.findViewById(R.id.personal_view);
        RoundImageView avatar_img = view.findViewById(R.id.avatar_img);
        TextView name_view = view.findViewById(R.id.name_view);
        AppCompatButton button_article = view.findViewById(R.id.button_article);
        AppCompatButton button_favorites = view.findViewById(R.id.button_favorites);
        AppCompatButton button_system = view.findViewById(R.id.button_system);

        avatar_img.setStrokeColor(Color.parseColor(Static.button_stroke_color));
        relati_view.setBackgroundColor(Color.parseColor(Static.menu_color));
        personal_view.setPadding(0, Fun.获取状态栏高度(context), 0, 0);

        Fun.setButtonTheme(context, button_article);
        Fun.setButtonTheme(context, button_favorites);
        Fun.setButtonTheme(context, button_system);

        linear.setBackgroundColor(Color.parseColor(Static.drawable_color));
        linear.addView(Create_Child_View(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void 初始化() {
        super.初始化();

    }

    private View Create_Child_View(){
        View child_view = View.inflate(context, R.layout.home_child_view, null);
        AppCompatButton wangpan_button = child_view.findViewById(R.id.wangpan_button);
        AppCompatButton geren_button = child_view.findViewById(R.id.geren_button);
        AppCompatButton zhuti_button = child_view.findViewById(R.id.zhuti_button);
        AppCompatButton dizhi_button = child_view.findViewById(R.id.dizhi_button);
        AppCompatButton system_button = child_view.findViewById(R.id.system_button);

        Fun.setButtonTheme(context, wangpan_button);
        Fun.setButtonTheme(context, geren_button);
        Fun.setButtonTheme(context, zhuti_button);
        Fun.setButtonTheme(context, dizhi_button);
        Fun.setButtonTheme(context, system_button);

        return child_view;
    }
}
