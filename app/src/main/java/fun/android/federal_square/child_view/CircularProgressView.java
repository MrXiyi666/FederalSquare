package fun.android.federal_square.child_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressView extends View {
    private Paint backgroundPaint; // 背景圆环画笔
    private Paint progressPaint;   // 进度圆环画笔
    private Paint textPaint;       // 文字画笔
    private int progress = 0;      // 当前进度（0-100）
    private int max = 100;         // 最大进度

    public CircularProgressView(Context context) {
        super(context);
        init();
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 初始化背景圆环画笔
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.rgb(242, 243, 247)); // 背景色
        backgroundPaint.setStyle(Paint.Style.STROKE); // 描边
        backgroundPaint.setStrokeWidth(10); // 圆环宽度
        backgroundPaint.setAntiAlias(true); // 抗锯齿

        // 初始化进度圆环画笔
        progressPaint = new Paint();
        progressPaint.setColor(Color.rgb(80, 80, 80)); // 进度色
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(10);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND); // 圆角笔触

        // 初始化文字画笔（显示进度值）
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10; // 圆环半径（留出边距）

        // 绘制背景圆环
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);

        // 绘制进度圆环（从顶部开始，顺时针绘制）
        float sweepAngle = (float) progress / max * 360; // 进度对应的角度
        canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                -90, // 起始角度（-90 度对应顶部）
                sweepAngle, // 扫过的角度
                false, // 不填充
                progressPaint
        );
        // 绘制进度文字（如“60%”）
        String text = progress + "%";
        // 计算文字 baseline 位置（垂直居中）
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(text, centerX, textY, textPaint);
    }

    // 设置进度（外部调用更新进度）
    public void setProgress(int progress) {
        if (progress < 0) progress = 0;
        if (progress > max) progress = max;
        this.progress = progress;
        invalidate(); // 刷新绘制
    }

    public void setMax(int max) {
        this.max = max;
    }
}