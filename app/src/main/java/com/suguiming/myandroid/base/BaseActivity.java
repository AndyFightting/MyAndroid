package com.suguiming.myandroid.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.utils.ActivityManager;

/**
 * Created by suguiming on 15/11/18.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected RelativeLayout fatherView;
    protected RelativeLayout titleView;
    protected LinearLayout mainView;
    protected View statusBackView;

    private boolean hasTitle = false;
    private TextView titleTv;
    private ImageView leftImg;
    private TextView leftTitleTv;

    private TextView rightTitleTv;
    private ImageView rightOneImg;
    private ImageView rightTwoImg;

    public LayoutInflater mInflater;

    private RelativeLayout footerView;
    private TextView footerTv;

    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActivityManager.addActivity(this);
        //透明状态栏 mine sdk 19
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //自定义退出动画要用的，不然退出的效果不行...
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();

        MyTool.log("onCreate----------------- 当前位置：" + getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    //Activity被系统杀死时被调用，在onPause之前被调用,用于保存状态数据等
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //Activity被系统杀死后再重建时被调用,在onStart之后被调用
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    //Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    //方向改变时被调用
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                break;
        }
    }
//------------------------------------------------title view 相关的方法----------------------------------------------------------------

    //若要用公共 title View 就用这方法
    public void setMainView(int viewId) {
        setContentView(R.layout.layout_base);
        hasTitle = true;

        fatherView = (RelativeLayout) findViewById(R.id.father_view);
        mainView = (LinearLayout) findViewById(R.id.main_view);
        titleView = (RelativeLayout) findViewById(R.id.title_layout);
        titleTv = (TextView) findViewById(R.id.title_tv);
        statusBackView = findViewById(R.id.status_back_view);

        leftImg = (ImageView) findViewById(R.id.left_img);
        leftTitleTv = (TextView) findViewById(R.id.left_title_tv);

        rightTitleTv = (TextView) findViewById(R.id.right_title_tv);
        rightOneImg = (ImageView) findViewById(R.id.right_one_img);
        rightTwoImg = (ImageView) findViewById(R.id.right_two_img);

        LayoutInflater inflater = LayoutInflater.from(this);
        View tmpView = inflater.inflate(viewId, null);

        if (tmpView instanceof LinearLayout) {
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            tmpView.setLayoutParams(linearLayoutParams);
        } else if (tmpView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            tmpView.setLayoutParams(relativeLayoutParams);
        }

        mainView.removeAllViews();
        mainView.addView(tmpView);

        footerView = (RelativeLayout) mInflater.inflate(R.layout.refresh_footer, null);
        footerTv = (TextView) footerView.findViewById(R.id.title);
        fatherView.addView(footerView);

    }

    public void hideTitleView() {
        if (hasTitle) {
            titleView.setVisibility(View.GONE);
        }
    }

    public void showTitleView(String titleName) {
        if (hasTitle) {
            titleView.setVisibility(View.VISIBLE);
            titleTv.setText(titleName);
        }
    }

    public void hideLeftImg() {
        if (hasTitle) {
            leftImg.setVisibility(View.GONE);
        }
    }

    public void showLeftImg(String imgName) {
        if (hasTitle) {
            leftImg.setOnClickListener(this);
            leftImg.setVisibility(View.VISIBLE);
            leftTitleTv.setVisibility(View.GONE);

            Context ctx = getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            leftImg.setImageResource(imgId);
        }
    }

    private void hideLeftTitle() {
        if (hasTitle) {
            leftTitleTv.setVisibility(View.GONE);
        }
    }

    public void showLeftTitle(String titleName) {
        if (hasTitle) {
            leftTitleTv.setOnClickListener(this);
            leftTitleTv.setVisibility(View.VISIBLE);
            leftImg.setVisibility(View.GONE);

            leftTitleTv.setText(titleName);
        }
    }

    private void hideRightTitle() {
        if (hasTitle) {
            rightTitleTv.setVisibility(View.GONE);
        }
    }

    public void showRightTitle(String titleName) {
        if (hasTitle) {
            rightTitleTv.setOnClickListener(this);
            rightTitleTv.setVisibility(View.VISIBLE);
            rightOneImg.setVisibility(View.GONE);
            rightTwoImg.setVisibility(View.GONE);
            rightTitleTv.setText(titleName);
        }
    }

    public void hideRightOneImg() {
        if (hasTitle) {
            rightOneImg.setVisibility(View.GONE);
        }
    }

    public void showRightOneImg(String imgName) {
        if (hasTitle) {
            rightOneImg.setOnClickListener(this);
            rightOneImg.setVisibility(View.VISIBLE);
            rightTitleTv.setVisibility(View.GONE);

            Context ctx = getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            rightOneImg.setImageResource(imgId);
        }
    }

    public void hideRightTwoImg() {
        if (hasTitle) {
            rightTwoImg.setVisibility(View.GONE);
        }
    }

    public void showRightTwoImg(String imgName) {
        if (hasTitle) {
            rightTwoImg.setOnClickListener(this);
            rightTwoImg.setVisibility(View.VISIBLE);
            rightTitleTv.setVisibility(View.GONE);

            Context ctx = getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            rightTwoImg.setImageResource(imgId);
        }
    }

    //若不是返回就重写该方法
    public void leftImgTap(View view) {
        finish();
    }

    public void leftTitleTap(View view) {
    }

    public void mainViewTap(View view) {

    }

    public void rightTitleTap(View view) {

    }

    public void rightOneImgTap(View view) {

    }

    public void rightTwoImgTap(View view) {

    }
//------------------------------------------------其他方法----------------------------------------------------------------

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_img:
                leftImgTap(view);
                break;
            case R.id.left_title_tv:
                leftTitleTap(view);
                break;
            case R.id.right_title_tv:
                rightTitleTap(view);
                break;
            case R.id.right_one_img:
                rightOneImgTap(view);
                break;
            case R.id.right_two_img:
                rightTwoImgTap(view);
                break;
            case R.id.main_view:
                mainViewTap(view);
                break;
        }
    }

    public void setImageWithName(ImageView image, String imgName) {
        Context ctx = getBaseContext();
        int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
        image.setImageResource(imgId);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLoadMoreFooter(String message) {
        footerView.setVisibility(View.VISIBLE);
        footerTv.setText(message);

        fatherView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadMoreFooter();
            }
        }, 1000);//最迟1秒后也会自带消失
    }

    public void hideLoadMoreFooter() {
        footerView.setVisibility(View.GONE);
    }


}
