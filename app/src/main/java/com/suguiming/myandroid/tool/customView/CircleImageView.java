package com.suguiming.myandroid.tool.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {

    private int borderWidth = 6;
    private int imgWidth;
    private int imgHeight;

    private Paint imgPaint;
    private Paint borderPaint;

    public Bitmap bitmap;
    public BitmapShader bitmapShader;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData();
    }

    @Override
    public void onDraw(Canvas canvas) {

        int halfImgWidth = imgWidth / 2;
        int totalWidth = halfImgWidth + borderWidth;
        canvas.drawCircle(totalWidth,totalWidth,totalWidth, borderPaint);
        canvas.drawCircle(totalWidth,totalWidth, halfImgWidth, imgPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        imgWidth = width - (borderWidth * 2);
        imgHeight = height - (borderWidth * 2);

        setMeasuredDimension(width, height);
    }

    private void initData() {
        imgPaint = new Paint();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
        bitmap = bitmapDrawable.getBitmap();
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        imgPaint.setAntiAlias(true);
        imgPaint.setShader(bitmapShader);

        borderPaint = new Paint();
        setBorderColor(Color.WHITE);
        borderPaint.setAntiAlias(true);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        borderPaint.setColor(borderColor);
        this.invalidate();
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = imgWidth;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = imgHeight;
        }
        return result;
    }
}
