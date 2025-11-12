package fun.android.federal_square.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import fun.android.federal_square.R;
import fun.android.federal_square.child_view.Article_View;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;

public class TimeLine_View extends View_Main {

    public TimeLine_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.timeline_view, null);
        linear = view.findViewById(R.id.linear);
        初始化文章();
    }
    int index = 100;
    public void 初始化文章(){
        linear.addView(new View(context), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Fun.获取状态栏高度(context)));
        for(int i=0;i<20;i++){
            linear.addView(new Article_View(context).getView(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        linear.addView(new View(context), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Fun.获取状态栏高度(context)));
        linear.post(()->{
            for (int i = 0; i < linear.getChildCount(); i++) {
                View child = linear.getChildAt(i);
                if ("article_view".equals(child.getTag())) {
                    if(child.getHeight() + child.getTop() < Static.main.getScrollY() || child.getTop() > Static.main.getScrollY() + Static.main.getHeight()){
                        child.setVisibility(View.INVISIBLE);
                    }else{
                        child.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}