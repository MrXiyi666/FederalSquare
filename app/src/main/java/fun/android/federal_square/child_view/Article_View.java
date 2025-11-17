package fun.android.federal_square.child_view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.csdn.roundview.RoundImageView;

import fun.android.federal_square.R;
import fun.android.federal_square.data.Acticle_Data;
import fun.android.federal_square.system.Static;

public class Article_View {
    private View view;
    public Article_View(Context context, Acticle_Data acticle_data){
        view = View.inflate(context, R.layout.article_view, null);
        view.setTag("article_view");
        RoundImageView avatar_img = view.findViewById(R.id.avatar_img);
        LinearLayout linear_info = view.findViewById(R.id.linear_info);
        TextView name = view.findViewById(R.id.name);
        TextView text = view.findViewById(R.id.text);
        TextView url = view.findViewById(R.id.url);
        TextView sign = view.findViewById(R.id.sign);
        View di_xian = view.findViewById(R.id.di_xian);
        name.setText(acticle_data.Name);
        text.setText(acticle_data.Text);
        url.setText(acticle_data.Url);
        sign.setText(acticle_data.Sign);

        // 使用 ViewTreeObserver 确保布局完成
        linear_info.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // 移除监听器，避免重复调用
                        linear_info.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // 执行绑定
                        int lineHeight = linear_info.getHeight();
                        ViewGroup.LayoutParams params = avatar_img.getLayoutParams();
                        params.width = lineHeight;
                        params.height = lineHeight;
                        avatar_img.setLayoutParams(params);
                    }
                }
        );
        avatar_img.setStrokeColor(Color.parseColor(Static.button_stroke_color));
        di_xian.setBackgroundColor((Color.parseColor(Static.button_stroke_color) & 0x00FFFFFF) | (51 << 24));
        view.setBackgroundColor(Color.parseColor(Static.article_color));

    }

    public View getView(){
        return view;
    }

}