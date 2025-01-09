package fun.android.federal_square.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import fun.android.federal_square.fun.Fun;

public class Video_ImageView extends net.csdn.roundview.RoundImageView {
    private Paint paint_fill, paint_stroke;
    private void init(Context context){
        paint_fill = new Paint();
        paint_fill.setColor(Color.WHITE);
        paint_fill.setAntiAlias(true);
        paint_fill.setStyle(Paint.Style.FILL);
        paint_fill.setStrokeWidth(Fun.DPToPX((Activity) context, 4));
        paint_fill.setTextSize(Fun.DPToPX((Activity) context, 15));
        paint_stroke = new Paint();
        paint_stroke.setColor(Color.BLACK);
        paint_stroke.setAntiAlias(true);
        paint_stroke.setStyle(Paint.Style.STROKE);
        paint_stroke.setStrokeWidth(Fun.DPToPX((Activity) context, 4));
        paint_stroke.setTextSize(Fun.DPToPX((Activity) context, 15));
    }

    public Video_ImageView(Context context) {
        super(context);
        init(context);
    }

    public Video_ImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Video_ImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public String 后缀="";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(Fun.视频格式判断(后缀)){
            int x_width = this.getWidth() / 2;
            int p_width = (int) ((paint_stroke.getTextSize() / 2) * 后缀.length());
            int x = x_width - p_width;
            int y=0;
            if(this.getHeight() > paint_stroke.getTextSize()){
                y= (int) (this.getHeight() - paint_stroke.getTextSize());
            }else{
                y = (int) paint_stroke.getTextSize();
            }
            canvas.drawText(后缀, x, y, paint_stroke);
            canvas.drawText(后缀, x, y, paint_fill);
        }
    }
}
