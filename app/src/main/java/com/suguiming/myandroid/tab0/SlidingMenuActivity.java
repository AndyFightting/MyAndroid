package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.suguiming.myandroid.R;

/**
 * slidingMenu 框架使用方法，
 *
 * 1.从官网(https://github.com/jfeinstein10/SlidingMenu)下载源码
 * 2.在项目中建一个与app文件夹同级的libraries文件夹，在libraries文件夹里再建一个 SlidingMenu 文件夹
 * 3.把下载下来的框架里的library文件夹复制黏贴到 SlidingMenu 文件夹里
 * 4.在setting.gradle文件里添加 include ':libraries:SlidingMenu/library'
 * 5.在项目的build.gradle 文件里 添加 compile project(':libraries:SlidingMenu/library') 就可以用啦
 *
 * 注意：编译会报错，要把源码里的FloatMath.sin()  替换成 Math.sin()。
 * (像这样把SlidingMenu当做一个View来使用更灵活)
 *
 */

public class SlidingMenuActivity extends AppCompatActivity {

    private SlidingMenu slidingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏 mine sdk 19
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_sliding_menu);

        slidingLayout = (SlidingMenu) findViewById(R.id.sliding_layout);
        slidingLayout.setMode(SlidingMenu.LEFT_RIGHT);//设置左右菜单，有右菜单一定就要有右菜单布局 secondaryMenu

        slidingLayout.setContent(R.layout.sliding_menu_main_frame);//设置主页面
        slidingLayout.setMenu(R.layout.sliding_menu_left_frame);//设置菜单 必须
        slidingLayout.setSecondaryMenu(R.layout.sliding_menu_right_frame);//设置菜单 必须

        slidingLayout.setBehindOffsetRes(R.dimen.sliding_left_offset);//越大滑动距离越小。必须
        slidingLayout.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);////设置要使菜单滑动，触碰屏幕的范围

        //设置边界阴影，和暗淡渐变效果，可以不设置
//        slidingLayout.setShadowWidthRes(R.dimen.sliding_shadow_width);
//        slidingLayout.setShadowDrawable(R.drawable.sliding_frame_shadow);
//        slidingLayout.setSecondaryShadowDrawable(R.drawable.sliding_frame_shadow);
          slidingLayout.setFadeDegree(0.7f);

        //监听开关（开始开关，完成开关）
        slidingLayout.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {

            }
        });
        slidingLayout.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {

            }
        });
        slidingLayout.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {

            }
        });
        slidingLayout.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {

            }
        });

    }

}
