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

        switch (Static.当前主题){
            case "青春薄荷绿":
                view.setBackgroundColor(Color.parseColor(Static.青春薄荷绿[2]));
                avatar_img.setStrokeColor(Color.parseColor(Static.青春薄荷绿[3]));
                di_xian.setBackgroundColor((Color.parseColor(Static.青春薄荷绿[3]) & 0x00FFFFFF) | (51 << 24));
                break;
            case "活力天空蓝":
                view.setBackgroundColor(Color.parseColor(Static.活力天空蓝[2]));
                avatar_img.setStrokeColor(Color.parseColor(Static.活力天空蓝[3]));
                di_xian.setBackgroundColor((Color.parseColor(Static.活力天空蓝[3]) & 0x00FFFFFF) | (51 << 24));
                break;
            case "甜美樱花粉":
                view.setBackgroundColor(Color.parseColor(Static.甜美樱花粉[2]));
                avatar_img.setStrokeColor(Color.parseColor(Static.甜美樱花粉[3]));
                di_xian.setBackgroundColor((Color.parseColor(Static.甜美樱花粉[3]) & 0x00FFFFFF) | (51 << 24));
                break;
            case "阳光柑橘橙":
                view.setBackgroundColor(Color.parseColor(Static.阳光柑橘橙[2]));
                avatar_img.setStrokeColor(Color.parseColor(Static.阳光柑橘橙[3]));
                di_xian.setBackgroundColor((Color.parseColor(Static.阳光柑橘橙[3]) & 0x00FFFFFF) | (51 << 24));
                break;
            case "梦幻薰衣草紫":
                view.setBackgroundColor(Color.parseColor(Static.梦幻薰衣草紫[2]));
                avatar_img.setStrokeColor(Color.parseColor(Static.梦幻薰衣草紫[3]));
                di_xian.setBackgroundColor((Color.parseColor(Static.梦幻薰衣草紫[3]) & 0x00FFFFFF) | (51 << 24));
                break;
            default:

        }

    }

    public View getView(){
        return view;
    }

}