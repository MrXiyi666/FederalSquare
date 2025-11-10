package fun.android.federal_square.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import fun.android.federal_square.R;
import fun.android.federal_square.system.Fun;

public class Popular_View extends View_Main{
    private LinearLayout linear;
    public Popular_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.popular_view, null);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if(params == null){
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        params.topMargin = Fun.获取状态栏高度(context);
        view.setLayoutParams(params);
        linear = view.findViewById(R.id.linear);
    }
}
