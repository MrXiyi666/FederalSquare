package fun.android.federal_square.adatper;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class Main_Pager_Adapter extends PagerAdapter {

    private final List<View> mViews;

    public Main_Pager_Adapter(@NonNull List<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final View view = mViews.get(position);
        // 防止重复添加已有父视图的View
        final ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}