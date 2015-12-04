package com.suguiming.myandroid.tool.customView;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public class TapAnimationImageView extends ImageView {

    private static final int SCALE_REDUCE = 0;
    private static final int SCALING = 1;
    private static final int SCALE_ADD = 2;

    protected static float mMinScale = 0.95f; //缩放参数，越小缩放越厉害。1：不缩放
    protected static boolean isFinish = true;
    protected int halfWidth;
    protected int halfHeight;

    private  TapListener tapListener;
    private AnimationHandler handler;

    public TapAnimationImageView(Context context)
    {
        this(context, null);
    }

    public TapAnimationImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TapAnimationImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        handler = new AnimationHandler(this);
    }

    /**
     * 在view给其孩子设置尺寸和位置时被调用
     * 参数changed表示view有新的尺寸或位置；
     * 参数l表示相对于父view的Left位置；参数t表示相对于父view的Top位置；
     * 参数r表示相对于父view的Right位置；参数b表示相对于父view的Bottom位置
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        this.setScaleType(ScaleType.MATRIX);

        if (changed)
        {
            int mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            int mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

            halfWidth = mWidth / 2;
            halfHeight = mHeight / 2;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                handler.sendEmptyMessage(SCALE_REDUCE);
                break;
            case MotionEvent.ACTION_UP:
                handler.sendEmptyMessage(SCALE_ADD);
                break;
        }
        return true;
    }

    private synchronized void beginScale(Matrix matrix, float scale)
    {
        matrix.postScale(scale, scale, halfWidth, halfHeight);
        setImageMatrix(matrix);
    }

//------- 点击事件 接口---------
    public void setTapListener(TapListener tapListener)
    {
        this.tapListener = tapListener;
    }
    public interface TapListener
    {
        void onImageViewTap(TapAnimationImageView view);
    }

//--------handler 内部类----------
    static class AnimationHandler extends Handler{

        private Matrix matrix = new Matrix();
        private int count = 0;
        private float scale;
        private boolean isClicked;

        WeakReference<TapAnimationImageView> weakReference;
        AnimationHandler(TapAnimationImageView imageView) {
            weakReference = new WeakReference<>(imageView);
        }
        public void handleMessage(Message msg)
        {
            TapAnimationImageView imageView = weakReference.get();
            matrix.set(imageView.getImageMatrix());
            switch (msg.what)
            {
                case SCALE_REDUCE:
                    if (!isFinish)
                    {
                        sendEmptyMessage(SCALE_REDUCE);
                    } else
                    {
                        isFinish = false;
                        count = 0;
                        scale = (float) Math.sqrt(Math.sqrt(mMinScale));
                        imageView.beginScale(matrix, scale);
                        sendEmptyMessage(SCALING);
                    }
                    break;
                case SCALING:
                    imageView.beginScale(matrix, scale);
                    if (count < 4)
                    {
                        sendEmptyMessage(SCALING);
                    } else
                    {
                        isFinish = true;
                        if (imageView.tapListener != null && !isClicked)
                        {
                            isClicked = true;
                            imageView.tapListener.onImageViewTap(imageView);
                        } else
                        {
                            isClicked = false;
                        }
                    }
                    count++;

                    break;
                case SCALE_ADD:
                    if (!isFinish)
                    {
                        sendEmptyMessage(SCALE_ADD);
                    } else
                    {
                        isFinish = false;
                        count = 0;
                        scale = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
                        imageView.beginScale(matrix, scale);
                        sendEmptyMessage(SCALING);
                    }
                    break;
            }
        }
    }
}
