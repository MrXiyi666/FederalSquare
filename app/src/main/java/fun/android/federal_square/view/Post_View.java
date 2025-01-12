package fun.android.federal_square.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import fun.android.federal_square.fun.Fun_文章子布局;

public class Post_View extends LinearLayout {
    public Post_View(Context context) {
        super(context);
    }

    public Post_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Post_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Fun_文章子布局 fun_文章子布局;
    public void 传递参数(Fun_文章子布局 fun_文章子布局){
        this.fun_文章子布局 = fun_文章子布局;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility == View.VISIBLE){
            fun_文章子布局.加载图片();
        }
        if(visibility == View.INVISIBLE){
            fun_文章子布局.清除图片();
        }
    }
}
