package fun.android.federal_square.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

        StateListDrawable button_article_drawable = new StateListDrawable();
        button_article_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_article_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_article.setStateListAnimator(null);
        button_article.setElevation(0f);
        button_article.setBackground(button_article_drawable);

        StateListDrawable button_favorites_drawable = new StateListDrawable();
        button_favorites_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_favorites_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_favorites.setStateListAnimator(null);
        button_favorites.setElevation(0f);
        button_favorites.setBackground(button_favorites_drawable);

        StateListDrawable button_system_drawable = new StateListDrawable();
        button_system_drawable.addState(new int[]{android.R.attr.state_pressed}, Static.down_pressedDrawable);
        button_system_drawable.addState(new int[]{}, Static.up_normalDrawable);
        button_system.setStateListAnimator(null);
        button_system.setElevation(0f);
        button_system.setBackground(button_system_drawable);
    }
}
