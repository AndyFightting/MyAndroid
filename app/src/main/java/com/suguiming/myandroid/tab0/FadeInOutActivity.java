package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.ItemTapListener;
import com.suguiming.myandroid.tool.customView.PopWindow;

public class FadeInOutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_fade_in_out);
        showTitleView("淡入淡出");
        showLeftImg("back_img");//点击事件默认退出，也可以重写leftImgTap()方法
        showRightTitle("pop window");

    }

    @Override
    public void rightTitleTap(View view) {
        PopWindow.show(this, view, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                showToast("hello");
            }
        });
    }
}
