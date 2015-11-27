package com.suguiming.myandroid.tool.customDialog;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.suguiming.myandroid.R;

public class PopActivity extends Activity {
    protected int activityCloseEnterAnimation;

    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        //透明状态栏 mine sdk 19
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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
//		doss();
        super.finish();

        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
        return super.onTouchEvent(event);
    }

}
