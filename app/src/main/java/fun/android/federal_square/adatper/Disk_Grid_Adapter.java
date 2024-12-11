package fun.android.federal_square.adatper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import java.util.List;

import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun_账号;

public class Disk_Grid_Adapter extends BaseAdapter {
    private final Activity activity;
    private final List<String> url;
    public Disk_Grid_Adapter(Activity activity, List<String> url){
        this.activity = activity;
        this.url = url;
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public Object getItem(int position) {
        return url.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGui gui = new MyGui();

        if(convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.disk_item, null);
            gui.img = convertView.findViewById(R.id.img);
            convertView.setTag(gui);
        }else{
            gui = (MyGui) convertView.getTag();
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.glide_zhanwei)
                .error(R.drawable.glide_shibai)
                .fallback(R.drawable.glide_duqushibai);
        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        Glide.with(activity)
                .load(able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + url.get(position))
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.with(factory))
                .into(gui.img);
            return convertView;
    }
    class MyGui{
        ImageView img;
    }
}
