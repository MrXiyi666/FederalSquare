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
    private WeakReference<Context> contextRef;
    private final List<String> url;
    private int height;

    public Disk_Grid_Adapter(Context context, List<String> url, int Disk_Index){
        this.contextRef = new WeakReference<>(context);
        this.url = url;
        switch (Disk_Index){
            case 1:
                height = 300;
                break;
            case 2:
                height = 150;
                break;
            case 3:
                height = 100;
                break;
            case 4:
                height = 75;
                break;
            case 5:
                height = 60;
                break;
            case 6:
                height = 50;
                break;
            case 7:
                height = 45;
                break;
            case 8:
                height = 40;
                break;
            case 9:
                height = 30;
                break;
        }
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
        ViewGroup.LayoutParams params = gui.img.getLayoutParams();
        params.height = Fun.DPToPX(context, height);
        gui.img.setLayoutParams(params);
        String 后缀 = Fun_文件.获取后缀(url.get(position));
        gui.img.后缀 = 后缀;
        String url_txt = able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + url.get(position);
        Glide.with(context).load(url_txt)
                .override(Fun.DPToPX(context, height))
                .apply(able.requestOptions)
                .into(gui.img);
        return convertView;
    }
    static class MyGui{
        Video_ImageView img;
    }
}
