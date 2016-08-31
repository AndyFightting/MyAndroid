package com.suguiming.myandroid.tab0;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

public class PureCodeActivity extends BaseActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private MenuItem preItem;//上一次选中的item
    private long TOUCH_TIME = 0;

    private TextView drawer_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_pure_code);
        showTitleView("纯代码控件");
        showLeftImg("back_img");

        drawer_text = (TextView) findViewById(R.id.drawer_text);

        initDrawer();
        initNavigation();
    }

    private void initDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                showTitleView("抽屉关闭了");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                showTitleView("抽屉打开了");
            }
        };
        mDrawer.setDrawerListener(toggle);
    }

    private void initNavigation() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        LinearLayout headerLayout = (LinearLayout) mNavigationView.getHeaderView(0);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);//关闭抽屉

                mDrawer.postDelayed(new Runnable() {//延迟处理
                    @Override
                    public void run() {
                        changeDetail();
                    }
                }, 250);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                mDrawer.closeDrawer(GravityCompat.START);//关闭抽屉

                mDrawer.postDelayed(new Runnable() {//延迟处理
                    @Override
                    public void run() {
                        leftItemTaped(item);
                    }
                }, 250);

                return true;
            }
        });

        //取消蛋疼的默认颜色
        mNavigationView.setItemTextColor(null);
        mNavigationView.setItemIconTintList(null);
    }

    private void changeDetail() {
        showToast("点头像要干嘛呢?");
    }

    private void leftItemTaped(MenuItem item) {
        resetPreItem();

        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, spanString.length(), 0);
        item.setTitle(spanString);

        switch (item.getItemId()) {
            case R.id.item_0:
                item.setIcon(R.mipmap.tab0s);
                drawer_text.setText("0");
                break;
            case R.id.item_1:
                item.setIcon(R.mipmap.tab1s);
                drawer_text.setText("1");
                break;
            case R.id.item_2:
                item.setIcon(R.mipmap.tab2s);
                drawer_text.setText("2");
                break;
            case R.id.item_3:
                item.setIcon(R.mipmap.tab3s);
                drawer_text.setText("3");
                break;
            case R.id.item_4:
                item.setIcon(R.mipmap.tab0s);
                drawer_text.setText("4");
                break;
            default:
                break;
        }
        preItem = item;
    }

    private void resetPreItem() {
        if (preItem != null) {
            SpannableString spanString = new SpannableString(preItem.getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0);
            preItem.setTitle(spanString);

            switch (preItem.getItemId()) {
                case R.id.item_0:
                    preItem.setIcon(R.mipmap.tab0);
                    break;
                case R.id.item_1:
                    preItem.setIcon(R.mipmap.tab1);
                    break;
                case R.id.item_2:
                    preItem.setIcon(R.mipmap.tab2);
                    break;
                case R.id.item_3:
                    preItem.setIcon(R.mipmap.tab3);
                    break;
                case R.id.item_4:
                    preItem.setIcon(R.mipmap.tab0);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < 2000) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                showToast("再按一次退出");
            }
        }
    }

//        纯代码添加控件
//        private void pureCodeLayout(){
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
