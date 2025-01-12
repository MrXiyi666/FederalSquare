package fun.android.federal_square.adatper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.view.Video_ImageView;

public class Disk_Grid_Adapter extends BaseAdapter {
    private final Activity activity;
    private final List<String> url;
    private int height;
    public List<ImageView> imageViewList = new ArrayList<>();
    public Disk_Grid_Adapter(Activity activity, List<String> url, int Disk_Index){
        this.activity = activity;
        this.url = url;
        switch (Disk_Index){
            case 1:
                height = ViewGroup.LayoutParams.MATCH_PARENT;
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
        if(convertView == null){
            gui = new MyGui();
            convertView = LayoutInflater.from(activity).inflate(R.layout.disk_item, null);
            gui.img = convertView.findViewById(R.id.img);
            gui.linear = convertView.findViewById(R.id.linear);
            ViewGroup.LayoutParams params = gui.img.getLayoutParams();
            params.height = Fun.DPToPX(activity, height);
            gui.img.setLayoutParams(params);
            String 后缀 = Fun_文件.获取后缀(url.get(position));
            gui.img.后缀 = 后缀;
            convertView.setTag(gui);
        }else{
            gui = (MyGui) convertView.getTag();
        }

        String url_txt = able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + url.get(position);
        Glide.with(activity).load(url_txt)
                .override(Fun.DPToPX(activity, height))
                .apply(able.requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        imageViewList.add(gui.img);
                        return false;
                    }
                })
                .into(gui.img);
        return convertView;
    }
    class MyGui{
        LinearLayout linear;
        Video_ImageView img;
    }
}
