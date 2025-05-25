package fun.android.federal_square.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class View_Pager extends ViewPager {
    public boolean 判断滑动 = true;
    public View_Pager(@NonNull Context context) {
        super(context);
    }

    public View_Pager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 如果判断滑动为 false，直接返回 false，不处理触摸事件
        return 判断滑动 && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 如果判断滑动为 false，直接返回 false，不拦截触摸事件
        return 判断滑动 && super.onInterceptTouchEvent(ev);
    }
    public void 是否滑动(boolean b){
        判断滑动 = b;
    }
}