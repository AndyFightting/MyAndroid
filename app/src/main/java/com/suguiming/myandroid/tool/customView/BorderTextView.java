package com.suguiming.myandroid.tool.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.suguiming.myandroid.R;


/**
 * Created by suguiming on 15/12/11.
 */
public class BorderTextView extends TextView {

    public Paint borderPaint;
    public Paint innerPaint;
    public Paint textPaint;

    @ColorInt
    public int borderColor;
    @ColorInt
    public int innerColor;

    public int borderWidth;
    public int cornerRadius;

    public RectF borderRect;
    public RectF innerRect;

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        innerColor = array.getColor(R.styleable.BorderTextView_innerColor, Color.YELLOW);
        borderColor = array.getColor(R.styleable.BorderTextView_borderColor, 0);
        borderWidth = array.getDimensionPixelSize(R.styleable.BorderTextView_borderWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        0,
                        getResources().getDisplayMetrics()));
        cornerRadius = array.getDimensionPixelSize(R.styleable.BorderTextView_cornerRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        0,
                        getResources().getDisplayMetrics()));
        array.recycle();

        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setAntiAlias(true);

        innerPaint = new Paint();
        innerPaint.setColor(innerColor);
        innerPaint.setAntiAlias(true);

        textPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        borderRect = new RectF(0, 0, w, h);
        innerRect = new RectF(borderWidth, borderWidth, w-borderWidth, h-borderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //弄个边框
        canvas.drawRoundRect(borderRect, cornerRadius, cornerRadius, borderPaint);
        canvas.drawRoundRect(innerRect, cornerRadius, cornerRadius, innerPaint);

        //文字居中
        textPaint.setColor(getTextColors().getDefaultColor());
        textPaint.setTextSize(getTextSize());
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        float textBaseY = getHeight() - (getHeight() - fontHeight)/2 - fontMetrics.bottom;
        canvas.drawText(getText().toString(), getWidth()/2, textBaseY,textPaint);
    }


}
