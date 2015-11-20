package com.suguiming.myandroid.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.ActivityManager;
import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/11/18.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActivityManager.addActivity(this);
        //透明状态栏 mine sdk 19
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        MyTool.log("onCreate");


    }

    @Override
    protected void onStart(){
        super.onStart();
        MyTool.log("onStart");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        MyTool.log("onRestart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        MyTool.log("onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        MyTool.log("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyTool.log("onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityManager.removeActivity(this);
        MyTool.log("onDestroy activityList count "+ActivityManager.getActivityNum());
    }

    //Activity被系统杀死时被调用，在onPause之前被调用
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MyTool.log("onSaveInstanceState");
    }

    //Activity被系统杀死后再重建时被调用,在onStart之后被调用
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MyTool.log("onRestoreInstanceState");
    }

    //Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        MyTool.log("onWindowFocusChanged");
    }

    //方向改变时被调用
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                MyTool.log("ORIENTATION_PORTRAIT");
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                MyTool.log("ORIENTATION_LANDSCAPE");
                break;
        }
    }
//------------------------------------------------title view 相关的方法----------------------------------------------------------------

    //若要用公共 title View 就用这方法
    public void setMainView(int viewId){
        setContentView(R.layout.layout_base);
        hasTitle = true;

        fatherView = (RelativeLayout)findViewById(R.id.father_view);
        mainView = (LinearLayout)findViewById(R.id.main_view);
        titleView = (RelativeLayout)findViewById(R.id.title_layout);
        titleTv = (TextView)findViewById(R.id.title_tv);
        statusBackView = findViewById(R.id.status_back_view);

        leftImg = (ImageView)findViewById(R.id.left_img);
        leftTitleTv = (TextView)findViewById(R.id.left_title_tv);

        rightTitleTv = (TextView)findViewById(R.id.right_title_tv);
        rightOneImg = (ImageView)findViewById(R.id.right_one_img);
        rightTwoImg = (ImageView)findViewById(R.id.right_two_img);

        LayoutInflater inflater = LayoutInflater.from(this);
        View tmpView = inflater.inflate(viewId, null);
        mainView.addView(tmpView);
        
    }

    public void hideTitleView(){
        if (hasTitle){
            titleView.setVisibility(View.GONE);
        }
    }

    public void showTitleView(String titleName){
        if (hasTitle){
            titleView.setVisibility(View.VISIBLE);
            titleTv.setText(titleName);
        }
    }

    public void hideLeftImg(){
        if (hasTitle){
            leftImg.setVisibility(View.GONE);
        }
    }

    public void showLeftImg(String imgName){
        if (hasTitle){
            leftImg.setOnClickListener(this);
            leftImg.setVisibility(View.VISIBLE);
            leftTitleTv.setVisibility(View.GONE);

            Context ctx=getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            leftImg.setImageResource(imgId);
        }
    }

    private void hideLeftTitle(){
        if (hasTitle){
            leftTitleTv.setVisibility(View.GONE);
        }
    }

    public void showLeftTitle(String titleName){
        if (hasTitle){
            leftTitleTv.setOnClickListener(this);
            leftTitleTv.setVisibility(View.VISIBLE);
            leftImg.setVisibility(View.GONE);

            leftTitleTv.setText(titleName);
        }
    }

    private void hideRightTitle(){
        if (hasTitle){
            rightTitleTv.setVisibility(View.GONE);
        }
    }

    public void showRightTitle(String titleName){
        if (hasTitle){
            rightTitleTv.setOnClickListener(this);
            rightTitleTv.setVisibility(View.VISIBLE);
            rightOneImg.setVisibility(View.GONE);
            rightTwoImg.setVisibility(View.GONE);
            rightTitleTv.setText(titleName);
        }
    }

    public void hideRightOneImg(){
        if (hasTitle){
            rightOneImg.setVisibility(View.GONE);
        }
    }

    public void showRightOneImg(String imgName){
        if (hasTitle){
            rightOneImg.setOnClickListener(this);
            rightOneImg.setVisibility(View.VISIBLE);
            rightTitleTv.setVisibility(View.GONE);

            Context ctx=getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            rightOneImg.setImageResource(imgId);
        }
    }

    public void hideRightTwoImg(){
        if (hasTitle){
            rightTwoImg.setVisibility(View.GONE);
        }
    }

    public void showRightTwoImg(String imgName){
        if (hasTitle){
            rightTwoImg.setOnClickListener(this);
            rightTwoImg.setVisibility(View.VISIBLE);
            rightTitleTv.setVisibility(View.GONE);

            Context ctx=getBaseContext();
            int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
            rightTwoImg.setImageResource(imgId);
        }
    }

    //若不是返回就重写该方法
    public void leftImgTap(View view){
        MyTool.log("left img tap base activity");
        finish();
    }

    public void leftTitleTap(View view){
        MyTool.log("left title tap");
    }

    public void mainViewTap(View view){
        MyTool.log("back view tap");

    }

    public void rightTitleTap(View view){
        MyTool.log("right title tap");

    }

    public void rightOneImgTap(View view){
        MyTool.log("right one img tap");

    }

    public void rightTwoImgTap(View view){
        MyTool.log("right two img tap");

    }
//------------------------------------------------其他方法----------------------------------------------------------------

    @Override
    public void onClick(View view){
        switch (view.getId()){
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

    public void setImageWithName(ImageView image,String imgName){
        Context ctx=getBaseContext();
        int imgId = getResources().getIdentifier(imgName, "mipmap", ctx.getPackageName());
        image.setImageResource(imgId);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
