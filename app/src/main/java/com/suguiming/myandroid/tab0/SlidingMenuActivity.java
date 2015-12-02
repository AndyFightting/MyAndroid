package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.slidingMenu.SlidingMenuLeftView;
import com.suguiming.myandroid.tool.slidingMenu.SlidingMenuMainView;
import com.suguiming.myandroid.tool.slidingMenu.SlidingMenuRightView;

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

public class SlidingMenuActivity extends BaseActivity {

    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏， 不是继承 BaseActivity 就加这行
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_sliding_menu);

        slidingMenu = (SlidingMenu) findViewById(R.id.sliding_layout);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//设置左右菜单，有右菜单一定就要有右菜单布局 secondaryMenu

        slidingMenu.setContent(new SlidingMenuMainView(this,slidingMenu));//设置主页面
        slidingMenu.setMenu(new SlidingMenuLeftView(this,slidingMenu));//设置菜单 必须
        slidingMenu.setSecondaryMenu(new SlidingMenuRightView(this,slidingMenu));//设置菜单 必须

        slidingMenu.setBehindOffsetRes(R.dimen.sliding_left_offset);//越大滑动距离越小。必须
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);////设置要使菜单滑动，触碰屏幕的范围
        slidingMenu.setBehindScrollScale(0.5f);//差异滑动 0 -- 1

        //设置边界阴影，和暗淡渐变效果，可以不设置
//        slidingMenu.setShadowWidthRes(R.dimen.sliding_shadow_width);
//        slidingMenu.setShadowDrawable(R.drawable.sliding_frame_shadow);
//        slidingMenu.setSecondaryShadowDrawable(R.drawable.sliding_frame_shadow);
          slidingMenu.setFadeDegree(0.7f);

        //监听开关（开始开关，完成开关）
        slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {

            }
        });
        slidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {

            }
        });
        slidingMenu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {

            }
        });
        slidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {

            }
        });
    }

}
