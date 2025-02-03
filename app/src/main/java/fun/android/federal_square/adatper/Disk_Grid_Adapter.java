package fun.android.federal_square.adatper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
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
    private final int height;
    private static final int[] HEIGHT_PRESETS = {
            350,
            150,
            100,
            75,
            60,
            50,
            45,
            40,
            30
    };
    public Disk_Grid_Adapter(Context context, List<String> url, int Disk_Index){
        this.contextRef = new WeakReference<>(context);
        this.url = url;
        int heightDp = HEIGHT_PRESETS[Disk_Index-1];
        height = Fun.DPToPX(context, heightDp);
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
        MyGui gui;
        Context context = contextRef.get();
        String 后缀 = Fun_文件.获取后缀(url.get(position));
        if(context== null){
            return convertView;
        }
        if(convertView == null){
            gui = new MyGui();
            convertView = LayoutInflater.from(context).inflate(R.layout.disk_item, null);
            gui.img = convertView.findViewById(R.id.img);
            gui.img.getLayoutParams().height = height;
            convertView.setTag(gui);
        }else{
            gui = (MyGui) convertView.getTag();
        }
        gui.img.后缀 = 后缀;
        String url_txt = able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + url.get(position);
        Glide.with(context).load(url_txt)
                .override(height)
                .apply(able.requestOptions)
                .into(gui.img);
        return convertView;
    }
    static class MyGui{
        Video_ImageView img;
    }
    public void clearResources() {
        // 1. 清空数据源
        if (url != null) {
            url.clear();
        }

        // 2. 释放Glide资源
        Context context = contextRef.get();
        if (context != null) {
            Glide.get(context).clearMemory();
        }

        // 3. 解除上下文引用
        contextRef.clear();
    }
}
