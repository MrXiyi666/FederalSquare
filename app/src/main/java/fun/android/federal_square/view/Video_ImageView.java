package fun.android.federal_square.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import fun.android.federal_square.R;
import fun.android.federal_square.fun.Fun;

public class Video_ImageView extends net.csdn.roundview.RoundImageView {
    private Bitmap originalBitmap=null;
    private Bitmap scaledBitmap=null;
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

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Fun.视频格式判断(后缀)) {
            if(originalBitmap==null && originalBitmap.isRecycled()){
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.video_icon);
            }
            int squareSize = Math.min(getWidth(), getHeight()) / 4;
            if(scaledBitmap==null && scaledBitmap.isRecycled()){
                scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, squareSize, squareSize, true);
            }
            squareSize = Math.min(getWidth(), getHeight()) / 4;
            float left = (getWidth() - squareSize) / 2f;
            float top = (getHeight() - squareSize) / 2f;
            canvas.drawBitmap(scaledBitmap, left, top, new Paint());
        }
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (originalBitmap != null) {
            originalBitmap.recycle();
            originalBitmap = null;
        }
        if (scaledBitmap != null) {
            scaledBitmap.recycle();
            scaledBitmap = null;
        }
    }
}