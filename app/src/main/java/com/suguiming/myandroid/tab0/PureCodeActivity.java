package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

public class PureCodeActivity extends BaseActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_pure_code);
        showTitleView("纯代码控件");
        showLeftImg("back_img");

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();



    }


//    private void pureCodeLayout(){
//        RelativeLayout  pureLayout = (RelativeLayout)findViewById(R.id.pureLayout);//控件容器
//        pureLayout.setBackgroundColor(Color.parseColor("#F5F5DC"));
//
//        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(200,100);
////        textParams.topMargin = ScreenUtil.pxFromDp(this,30);
////        textParams.leftMargin = ScreenUtil.pxFromDp(this,10);
////        textParams.setMargins(10,10,10,10);
////        textParams.addRule(RelativeLayout.LEFT_OF,viewId);
//        textParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//        TextView textView = new TextView(this);
//        textView.setText("文本");
//        textView.setTextColor(ContextCompat.getColor(this,R.color.white));
//        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.purple));
//        textView.setGravity(Gravity.RIGHT);
//        textView.setPadding(0,10,20,0);
//
//        textView.setLayoutParams(textParams);
//        pureLayout.addView(textView);
//    }
}
