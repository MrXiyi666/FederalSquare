package fun.android.federal_square.adatper;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class Main_Pager_Adapter extends PagerAdapter {

    private final List<View> pager_view;
    public Main_Pager_Adapter(List<View> p){
        pager_view = p;
    }
    @Override
    public int getCount() {
        return pager_view.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView(pager_view.get(position));
    }
    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pager_view.get(position));
        return pager_view.get(position);
    }
    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0 == arg1;
    }
}
