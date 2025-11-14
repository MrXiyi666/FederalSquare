package fun.android.federal_square.view;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import fun.android.federal_square.R;
import fun.android.federal_square.child_view.Article_View;
import fun.android.federal_square.data.Acticle_Data;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Static;

public class TimeLine_View extends View_Main {
    private Handler handler = new Handler();
    private boolean 是否加载文章 = false;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(min_index>= index){
                linear.addView(new View(context), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Fun.获取状态栏高度(context)));
                return;
            }
            if(是否加载文章){
                handler.postDelayed(this, 1);
            }
            是否加载文章=true;
            Acticle_Data acticle_data = new Acticle_Data();
            acticle_data.Text="第" + min_index +"个文章\nsdgdfsgdfh\ndsgsdgsdg\ndsgsdgsdg\ndsgdsgsg1";
            acticle_data.Name = "第" + min_index + "个用户";
            View view = new Article_View(context, acticle_data).getView();
            if(min_index == index-1){
                View di_xian = view.findViewById(R.id.di_xian);
                di_xian.setVisibility(View.GONE);
            }
            linear.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linear.post(()->{
                View child = linear.getChildAt(linear.getChildCount()-1);
                if ("article_view".equals(child.getTag())) {
                    if(child.getHeight() + child.getTop() < Static.main.getScrollY() || child.getTop() > Static.main.getScrollY() + Static.main.getHeight()){
                        child.setVisibility(View.INVISIBLE);
                    }else{
                        child.setVisibility(View.VISIBLE);
                    }
                }
                min_index++;
                是否加载文章=false;
                //Static.main.fullScroll(ScrollView.FOCUS_DOWN);
                handler.postDelayed(this, 1);
            });

        }
    };
    public TimeLine_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.timeline_view, null);
        linear = view.findViewById(R.id.linear);
        初始化文章();

    }
    int index = 100;
    int min_index=0;
    public void 初始化文章(){
        linear.addView(new View(context), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Fun.获取状态栏高度(context)));
        handler.post(runnable);
    }
}