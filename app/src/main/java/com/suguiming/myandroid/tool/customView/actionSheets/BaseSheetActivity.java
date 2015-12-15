package com.suguiming.myandroid.tool.customView.actionSheets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.suguiming.myandroid.tool.utils.ItemTapListener;

/**
 * Created by suguiming on 15/11/28.
 */
//用作弹出 action sheet 效果的 base activity
public class BaseSheetActivity extends Activity {
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    public static ItemTapListener itemTapListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //退出动画要用的，不然不行...
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();

        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dismiss();
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        dismiss();
    }

    public void dismiss(){
        super.finish();
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    public static void show(Context context,Class showedActivityClass, ItemTapListener itemTapListener){
        BaseSheetActivity.itemTapListener = itemTapListener;
        Intent intent = new Intent(context,showedActivityClass);
        context.startActivity(intent);
    }

}
