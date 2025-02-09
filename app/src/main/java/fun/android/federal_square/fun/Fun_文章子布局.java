package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.ArrayList;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.view.Video_ImageView;
import fun.android.federal_square.window.打开方式窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看视频窗口;

public class Fun_文章子布局 {
    private String 后缀="";
    private View view;
    private final Activity activity;
    private final List<String> img_url;
    private final List<Video_ImageView> video_imageViews = new ArrayList<>();

    public Fun_文章子布局(Activity activity, List<String> img_url){
        this.activity = activity;
        this.img_url = img_url;
        switch (img_url.size()){
            case 0:

                break;
            case 1:
                view = View.inflate(activity, R.layout.create_post_img_layout_1, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                break;
            case 2:
                view = View.inflate(activity, R.layout.create_post_img_layout_2, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                video_imageViews.add(view.findViewById(R.id.img_1));
                break;
            case 3:
                view = View.inflate(activity, R.layout.create_post_img_layout_3, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                video_imageViews.add(view.findViewById(R.id.img_1));
                video_imageViews.add(view.findViewById(R.id.img_2));
                break;
            case 4:
                view = View.inflate(activity, R.layout.create_post_img_layout_4, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                video_imageViews.add(view.findViewById(R.id.img_1));
                video_imageViews.add(view.findViewById(R.id.img_2));
                video_imageViews.add(view.findViewById(R.id.img_3));
                break;
            case 5:
                view = View.inflate(activity, R.layout.create_post_img_layout_5, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                video_imageViews.add(view.findViewById(R.id.img_1));
                video_imageViews.add(view.findViewById(R.id.img_2));
                video_imageViews.add(view.findViewById(R.id.img_3));
                video_imageViews.add(view.findViewById(R.id.img_4));
                break;
            default:
                view = View.inflate(activity, R.layout.create_post_img_layout_6, null);
                video_imageViews.add(view.findViewById(R.id.img_0));
                video_imageViews.add(view.findViewById(R.id.img_1));
                video_imageViews.add(view.findViewById(R.id.img_2));
                video_imageViews.add(view.findViewById(R.id.img_3));
                video_imageViews.add(view.findViewById(R.id.img_4));
                video_imageViews.add(view.findViewById(R.id.img_5));
        }
        for(int i=0;i<video_imageViews.size();i++){
            int finalI = i;
            video_imageViews.get(i).setOnClickListener(V->{
                后缀 = Fun_文件.获取后缀(img_url.get(finalI));
                if(Fun.图片格式判断(后缀)){
                    查看图片窗口.启动_Dialog(activity, img_url.get(finalI));
                }else if(Fun.视频格式判断(后缀)){
                    查看视频窗口.启动_Dialog(activity, img_url.get(finalI));
                }else{
                    打开方式窗口.启动(activity, img_url.get(finalI));
                }
            });
            后缀 = Fun_文件.获取后缀(img_url.get(i));
            video_imageViews.get(i).后缀 = 后缀;
            if(!Fun.图片格式判断(后缀)){
                video_imageViews.get(i).setBackgroundColor(Color.BLACK);
            }
            int finalI1 = i;
            video_imageViews.get(i).setOnLongClickListener(V->{
                if(Fun.图片格式判断(video_imageViews.get(finalI1).后缀)){
                    Glide.with(activity)
                            .load(img_url.get(finalI1))
                            .apply(able.requestOptions)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(video_imageViews.get(finalI1));
                }else{
                    Glide.with(activity)
                            .asBitmap()
                            .load(img_url.get(finalI1))
                            .apply(able.requestOptions)
                            .into(video_imageViews.get(finalI1));
                }
                return true;
            });
        }

    }

    public View getView() {
        return view;
    }

    public void 加载图片(){
        for(int i=0;i<video_imageViews.size();i++){
            video_imageViews.get(i).clearAnimation();
            Glide.with(activity)
                    .load(img_url.get(i))
                    .apply(able.requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(video_imageViews.get(i));
        }
    }

    public void 清除图片(){
        for(int i=0;i<video_imageViews.size();i++){
            Glide.with(activity).clear(video_imageViews.get(i));
        }
    }
}
