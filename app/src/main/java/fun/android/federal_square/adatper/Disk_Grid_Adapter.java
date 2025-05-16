package fun.android.federal_square.adatper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.Video_ImageView;

public class Disk_Grid_Adapter extends BaseAdapter {

    private final WeakReference<Context> contextRef;
    private final List<String> url;

    private RequestOptions requestOptions = new RequestOptions()
            .frame(0)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.glide_shibai)
            .fallback(R.drawable.glide_duqushibai);

    public Disk_Grid_Adapter(Context context, List<String> url, int Disk_Index){
        this.contextRef = new WeakReference<>(context);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGui gui;
        Context context = contextRef.get();
        if(context== null){
            return convertView;
        }
        if(convertView == null){
            gui = new MyGui();
            convertView = LayoutInflater.from(context).inflate(R.layout.disk_item, null);
            gui.img = convertView.findViewById(R.id.img);
            convertView.setTag(gui);
        }else{
            gui = (MyGui) convertView.getTag();
        }
        gui.img.后缀 = Fun_文件.获取后缀(url.get(position));
        String url_txt = able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + url.get(position);
        Glide.with(context).clear(gui.img);
        Glide.with(context)
                .asBitmap()
                .load(url_txt)
                .apply(requestOptions)
                .into(gui.img);
        return convertView;
    }
    static class MyGui{
        Video_ImageView img;
    }
    public void clearResources() {
        if (url != null) {
            url.clear();
        }
        Context context = contextRef.get();
        if (context != null) {
            Glide.get(context).clearMemory();
        }
        contextRef.clear();
    }
}