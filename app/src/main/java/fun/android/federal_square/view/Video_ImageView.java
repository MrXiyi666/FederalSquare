package fun.android.federal_square.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import fun.android.federal_square.fun.Fun;

public class Video_ImageView extends net.csdn.roundview.RoundImageView {
    public String 后缀;

    public Video_ImageView(Context context) {
        super(context);
    }

    public Video_ImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Video_ImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // 宽高相同
    }
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Fun.视频格式判断(后缀)) {
            画圆形(canvas);
            画三角形(canvas);
        }
    }

    private void 画圆形(Canvas canvas){
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // 取 View 的宽和高中较小的值作为正方形的边长
        int squareSize = Math.min(viewWidth, viewHeight);
        int 半径 = squareSize / 8;
        int xPos = viewWidth / 2 - 半径 / 8;
        int yPos = viewHeight / 2;

        Paint paint = new Paint();
        paint.setColor(Color.rgb(242,243,247));
        paint.setAntiAlias(true); //抗锯齿
        canvas.drawCircle(xPos, yPos, 半径, paint);
    }

    private void 画三角形(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int minSize = Math.min(width, height);
        float sideLength = minSize / 10f; // 十分之一大小
        float heightTriangle = (sideLength * (float) Math.sqrt(3)) / 2f; // 等边三角形的高度

        float cx = width / 2f;
        float cy = height / 2f;

        Path path = new Path();
        // 调整三角形的顶点到右侧
        path.moveTo(cx + heightTriangle / 2, cy); // 顶点在右侧
        path.lineTo(cx - heightTriangle / 2, cy - sideLength / 2); // 左侧上底点
        path.lineTo(cx - heightTriangle / 2, cy + sideLength / 2); // 左侧下底点
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#808080"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
    }

}