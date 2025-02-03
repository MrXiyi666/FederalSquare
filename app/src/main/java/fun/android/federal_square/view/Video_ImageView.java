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
    public String 后缀 = "";

    private void init(Context context) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Fun.视频格式判断(后缀)) {
            // 计算文本的宽度和高度
            float textWidth = paint_stroke.measureText(后缀);
            float textHeight = paint_stroke.getTextSize();

            // 计算文本的起始位置（居中底部对齐）
            float x = (getWidth() - textWidth) / 2;
            float y = getHeight() - textHeight / 2; // 底部对齐

            // 绘制文本
            canvas.drawText(后缀, x, y, paint_stroke);
            canvas.drawText(后缀, x, y, paint_fill);
        }
    }
}