package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
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
    private ImageView avatar_img;
    private String avatar_url="";
    private Activity activity;
    public void 传递参数(Fun_文章子布局 fun_文章子布局){
        this.fun_文章子布局 = fun_文章子布局;
    }

    public void 传递参数(Activity activity, ImageView avatar_img, String avatar_url){
        this.activity = activity;
        this.avatar_img = avatar_img;
        this.avatar_url = avatar_url;
        this.avatar_img.setOnLongClickListener(V->{
            Glide.with(activity)
                    .load(avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(able.requestOptions)
                    .override(Fun.DPToPX(activity, 40), Fun.DPToPX(activity, 40))
                    .into(avatar_img);
            return true;
        });
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility == View.VISIBLE){
            加载图片();
        }else{
            清除图片();
        }
    }
    private void 加载头像(){
        if(avatar_url.isEmpty()){
            avatar_img.setImageResource(R.mipmap.ic_launcher_round);
        }else{
            Glide.with(activity)
                    .load(avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(able.requestOptions)
                    .override(Fun.DPToPX(activity, 40), Fun.DPToPX(activity, 40))
                    .into(avatar_img);

        }
    }

    public void 清除图片(){
        fun_文章子布局.清除图片();
        Glide.with(activity).clear(avatar_img);
    }

    public void 加载图片(){
        fun_文章子布局.加载图片();
        加载头像();
    }
    private View di_xian;
    public void 传递底线(View di_xian){
        this.di_xian = di_xian;
    }

    public void 底线消失(){
        if(di_xian!=null){
            di_xian.setVisibility(View.INVISIBLE);
        }
    }
}