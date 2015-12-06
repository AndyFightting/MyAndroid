package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.FileUtil;
import com.suguiming.myandroid.tool.ItemTapListener;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.SDCardUtil;
import com.suguiming.myandroid.tool.ScreenUtil;
import com.suguiming.myandroid.tool.customView.PopWindow;
import com.suguiming.myandroid.tool.customView.TapAnimationImageView;

import in.srain.cube.diskcache.FileUtils;

public class FadeInOutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_fade_in_out);
        showTitleView("淡入淡出");
        showLeftImg("back_img");//点击事件默认退出，也可以重写leftImgTap()方法
        showRightTitle("pop window");

        TapAnimationImageView imageView = (TapAnimationImageView)findViewById(R.id.animation_img);
        imageView.setTapListener(new TapAnimationImageView.TapListener() {
            @Override
            public void onImageViewTap(TapAnimationImageView view) {
                showToast("hello");
            }
        });

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

    public void screenShot(View view){
        ImageView img = (ImageView)view;
        switch (view.getId()){
            case R.id.screen_img0:
                img.setImageBitmap(ScreenUtil.getScreenShotWithStatusBar(this));
                break;
            case R.id.screen_img1:
                img.setImageBitmap(ScreenUtil.getScreenShotWithoutStatusBar(this));
                break;
        }
    }
}
