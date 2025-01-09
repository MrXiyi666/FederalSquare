package fun.android.federal_square.fun;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.List;
import fun.android.federal_square.R;
import fun.android.federal_square.data.able;
import fun.android.federal_square.view.Video_ImageView;
import fun.android.federal_square.window.打开方式窗口;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.window.查看视频窗口;

public class Fun_文章_子布局 {
    private static String 后缀="";
    public static View getImg_View_1(Activity activity, List<String> img_url){
        View img_view;
        Video_ImageView img_0;
        img_view = View.inflate(activity, R.layout.create_post_img_layout_1, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        return img_view;
    }
    public static View getImg_View_2(Activity activity, List<String> img_url){
        View img_view;
        Video_ImageView img_0, img_1;

        img_view = View.inflate(activity, R.layout.create_post_img_layout_2, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_1 = img_view.findViewById(R.id.img_1);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        img_1.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(1))
                .apply(able.requestOptions_yasuo)
                .into(img_1);
        后缀 = Fun_文件.获取后缀(img_url.get(1));
        img_1.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_1.setBackgroundColor(Color.BLACK);
        }
        img_1.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(1));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(1));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(1));
            }else{
                打开方式窗口.启动(activity, img_url.get(1));
            }
        });
        Video_ImageView finalImg_1 = img_1;
        img_1.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(1))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_1);
            return true;
        });

        return img_view;
    }

    public static View getImg_View_3(Activity activity, List<String> img_url){
        View img_view;
        Video_ImageView img_0, img_1, img_2;

        img_view = View.inflate(activity, R.layout.create_post_img_layout_3, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_1 = img_view.findViewById(R.id.img_1);
        img_2 = img_view.findViewById(R.id.img_2);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        img_1.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(1))
                .apply(able.requestOptions_yasuo)
                .into(img_1);
        后缀 = Fun_文件.获取后缀(img_url.get(1));
        img_1.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_1.setBackgroundColor(Color.BLACK);
        }
        img_1.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(1));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(1));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(1));
            }else{
                打开方式窗口.启动(activity, img_url.get(1));
            }
        });
        Video_ImageView finalImg_1 = img_1;
        img_1.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(1)).apply(able.requestOptions_yasuo)
                    .into(finalImg_1);
            return true;
        });

        img_2.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(2))
                .apply(able.requestOptions_yasuo)
                .into(img_2);
        后缀 = Fun_文件.获取后缀(img_url.get(2));
        img_2.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_2.setBackgroundColor(Color.BLACK);
        }
        img_2.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(2));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(2));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(2));
            }else{
                打开方式窗口.启动(activity, img_url.get(2));
            }
        });
        Video_ImageView finalImg_2 = img_2;
        img_2.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(2))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_2);
            return true;
        });

        return img_view;
    }

    public static View getImg_View_4(Activity activity, List<String> img_url){
        View img_view = null;
        Video_ImageView img_0, img_1, img_2, img_3;

        img_view = View.inflate(activity, R.layout.create_post_img_layout_4, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_1 = img_view.findViewById(R.id.img_1);
        img_2 = img_view.findViewById(R.id.img_2);
        img_3 = img_view.findViewById(R.id.img_3);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        img_1.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(1))
                .apply(able.requestOptions_yasuo)
                .into(img_1);
        后缀 = Fun_文件.获取后缀(img_url.get(1));
        img_1.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_1.setBackgroundColor(Color.BLACK);
        }
        img_1.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(1));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(1));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(1));
            }else{
                打开方式窗口.启动(activity, img_url.get(1));
            }
        });
        Video_ImageView finalImg_1 = img_1;
        img_1.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(1))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_1);
            return true;
        });

        img_2.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(2))
                .apply(able.requestOptions_yasuo)
                .into(img_2);
        后缀 = Fun_文件.获取后缀(img_url.get(2));
        img_2.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_2.setBackgroundColor(Color.BLACK);
        }
        img_2.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(2));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(2));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(2));
            }else{
                打开方式窗口.启动(activity, img_url.get(2));
            }
        });
        Video_ImageView finalImg_2 = img_2;
        img_2.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(2))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_2);
            return true;
        });

        img_3.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(3))
                .apply(able.requestOptions_yasuo)
                .into(img_3);
        后缀 = Fun_文件.获取后缀(img_url.get(3));
        img_3.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_3.setBackgroundColor(Color.BLACK);
        }
        img_3.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(3));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(3));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(3));
            }else{
                打开方式窗口.启动(activity, img_url.get(3));
            }
        });

        Video_ImageView finalImg_3 = img_3;
        img_3.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(3))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_3);
            return true;
        });

        return img_view;
    }

    public static View getImg_View_5(Activity activity, List<String> img_url){
        View img_view;
        Video_ImageView img_0, img_1, img_2, img_3, img_4;

        img_view = View.inflate(activity, R.layout.create_post_img_layout_5, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_1 = img_view.findViewById(R.id.img_1);
        img_2 = img_view.findViewById(R.id.img_2);
        img_3 = img_view.findViewById(R.id.img_3);
        img_4 = img_view.findViewById(R.id.img_4);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        img_1.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(1))
                .apply(able.requestOptions_yasuo)
                .into(img_1);
        后缀 = Fun_文件.获取后缀(img_url.get(1));
        img_1.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_1.setBackgroundColor(Color.BLACK);
        }
        img_1.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(1));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(1));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(1));
            }else{
                打开方式窗口.启动(activity, img_url.get(1));
            }
        });
        Video_ImageView finalImg_1 = img_1;
        img_1.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(1))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_1);
            return true;
        });

        img_2.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(2))
                .apply(able.requestOptions_yasuo)
                .into(img_2);
        后缀 = Fun_文件.获取后缀(img_url.get(2));
        img_2.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_2.setBackgroundColor(Color.BLACK);
        }
        img_2.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(2));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(2));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(2));
            }else{
                打开方式窗口.启动(activity, img_url.get(2));
            }
        });
        Video_ImageView finalImg_2 = img_2;
        img_2.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(2))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_2);
            return true;
        });

        img_3.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(3))
                .apply(able.requestOptions_yasuo)
                .into(img_3);
        后缀 = Fun_文件.获取后缀(img_url.get(3));
        img_3.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_3.setBackgroundColor(Color.BLACK);
        }
        img_3.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(3));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(3));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(3));
            }else{
                打开方式窗口.启动(activity, img_url.get(3));
            }
        });

        Video_ImageView finalImg_3 = img_3;
        img_3.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(3))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_3);
            return true;
        });

        img_4.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(4))
                .apply(able.requestOptions_yasuo)
                .into(img_4);
        后缀 = Fun_文件.获取后缀(img_url.get(4));
        img_4.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_4.setBackgroundColor(Color.BLACK);
        }
        img_4.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(4));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(4));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(4));
            }else{
                打开方式窗口.启动(activity, img_url.get(4));
            }
        });

        Video_ImageView finalImg_4 = img_4;
        img_4.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(4))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_4);
            return true;
        });

        return img_view;
    }

    public static View getImg_View_6(Activity activity, List<String> img_url){
        View img_view;
        Video_ImageView img_0, img_1, img_2, img_3, img_4, img_5;

        img_view = View.inflate(activity, R.layout.create_post_img_layout_6, null);
        img_0 = img_view.findViewById(R.id.img_0);
        img_1 = img_view.findViewById(R.id.img_1);
        img_2 = img_view.findViewById(R.id.img_2);
        img_3 = img_view.findViewById(R.id.img_3);
        img_4 = img_view.findViewById(R.id.img_4);
        img_5 = img_view.findViewById(R.id.img_5);
        img_0.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(0))
                .apply(able.requestOptions_yasuo)
                .into(img_0);
        后缀 = Fun_文件.获取后缀(img_url.get(0));
        img_0.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_0.setBackgroundColor(Color.BLACK);
        }
        img_0.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(0));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(0));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(0));
            }else{
                打开方式窗口.启动(activity, img_url.get(0));
            }
        });
        Video_ImageView finalImg_ = img_0;
        img_0.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(0))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_);
            return true;
        });

        img_1.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(1))
                .apply(able.requestOptions_yasuo)
                .into(img_1);
        后缀 = Fun_文件.获取后缀(img_url.get(1));
        img_1.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_1.setBackgroundColor(Color.BLACK);
        }
        img_1.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(1));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(1));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(1));
            }else{
                打开方式窗口.启动(activity, img_url.get(1));
            }
        });
        Video_ImageView finalImg_1 = img_1;
        img_1.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(1))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_1);
            return true;
        });

        img_2.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(2))
                .apply(able.requestOptions_yasuo)
                .into(img_2);
        后缀 = Fun_文件.获取后缀(img_url.get(2));
        img_2.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_2.setBackgroundColor(Color.BLACK);
        }
        img_2.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(2));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(2));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(2));
            }else{
                打开方式窗口.启动(activity, img_url.get(2));
            }
        });
        Video_ImageView finalImg_2 = img_2;
        img_2.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(2))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_2);
            return true;
        });

        img_3.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(3))
                .apply(able.requestOptions_yasuo)
                .into(img_3);
        后缀 = Fun_文件.获取后缀(img_url.get(3));
        img_3.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_3.setBackgroundColor(Color.BLACK);
        }
        img_3.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(3));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(3));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(3));
            }else{
                打开方式窗口.启动(activity, img_url.get(3));
            }
        });

        Video_ImageView finalImg_3 = img_3;
        img_3.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(3))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_3);
            return true;
        });

        img_4.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(4))
                .apply(able.requestOptions_yasuo)
                .into(img_4);
        后缀 = Fun_文件.获取后缀(img_url.get(4));
        img_4.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_4.setBackgroundColor(Color.BLACK);
        }
        img_4.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(4));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(4));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(4));
            }else{
                打开方式窗口.启动(activity, img_url.get(4));
            }
        });

        Video_ImageView finalImg_4 = img_4;
        img_4.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(4))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_4);
            return true;
        });

        img_5.setImageBitmap(null);
        Glide.with(activity)
                .asBitmap()
                .load(img_url.get(5))
                .apply(able.requestOptions_yasuo)
                .into(img_5);
        后缀 = Fun_文件.获取后缀(img_url.get(5));
        img_5.后缀 = 后缀;
        if(!Fun.图片格式判断(后缀)){
            img_5.setBackgroundColor(Color.BLACK);
        }
        img_5.setOnClickListener(V->{
            后缀 = Fun_文件.获取后缀(img_url.get(5));
            if(Fun.图片格式判断(后缀)){
                查看图片窗口.启动_Dialog(activity, img_url.get(5));
            }else if(Fun.视频格式判断(后缀)){
                查看视频窗口.启动_Dialog(activity, img_url.get(5));
            }else{
                打开方式窗口.启动(activity, img_url.get(5));
            }
        });

        Video_ImageView finalImg_5 = img_5;
        img_5.setOnLongClickListener(V->{
            Fun.mess(activity, "重新加载");
            Glide.with(activity)
                    .asBitmap()
                    .load(img_url.get(5))
                    .apply(able.requestOptions_yasuo)
                    .into(finalImg_5);
            return true;
        });

        return img_view;
    }
}
