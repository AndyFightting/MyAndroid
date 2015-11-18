package com.suguiming.myandroid.base;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/11/18.
 */
public class BaseFragment extends Fragment {

    protected RelativeLayout fatherView;
    protected RelativeLayout titleView;
    protected LinearLayout mainView;

    private boolean hasTitle = false;
    private TextView titleTv;
    private ImageView leftImg;
    private TextView leftTitleTv;

    private TextView rightTv;
    private ImageView rightOneImg;
    private ImageView rightTwoImg;

    public Context mContext;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyTool.log("fragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MyTool.log("fragment onCreateView");
        View view = inflater.inflate(R.layout.layout_base, container, false);

        hasTitle = true;
        fatherView = (RelativeLayout)view.findViewById(R.id.father_view);
        mainView = (LinearLayout)view.findViewById(R.id.main_view);
        titleView = (RelativeLayout)view.findViewById(R.id.title_layout);
        titleTv = (TextView)view.findViewById(R.id.title_tv);

        leftImg = (ImageView)view.findViewById(R.id.left_img);
        leftTitleTv = (TextView)view.findViewById(R.id.left_title_tv);

        rightTv = (TextView)view.findViewById(R.id.right_title_tv);
        rightOneImg = (ImageView)view.findViewById(R.id.right_one_img);
        rightTwoImg = (ImageView)view.findViewById(R.id.right_two_img);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyTool.log("fragment onActivityCreated");

    }

        @Override
    public void onStart(){
        super.onStart();
        MyTool.log("fragment onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        MyTool.log("fragment onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        MyTool.log("fragment onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        MyTool.log("fragment onStop");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        MyTool.log("fragment onDestroyView");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        MyTool.log("fragment onDestroy");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        MyTool.log("fragment onDetach");
    }

    //Activity被系统杀死时被调用，在onPause之前被调用
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MyTool.log("fragment onSaveInstanceState");
    }

    //方向改变时被调用
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                MyTool.log("fragment ORIENTATION_PORTRAIT");
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                MyTool.log("fragment ORIENTATION_LANDSCAPE");
                break;
        }
    }
//------------------------------------------------title view 相关的方法----------------------------------------------------------------

    public void setMainView(int viewId){
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            leftImg.setVisibility(View.VISIBLE);
            leftTitleTv.setVisibility(View.GONE);

            int imgId = getResources().getIdentifier(imgName, "mipmap", mContext.getPackageName());
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
            leftTitleTv.setVisibility(View.VISIBLE);
            leftImg.setVisibility(View.GONE);

            leftTitleTv.setText(titleName);
        }
    }

    private void hideRightTitle(){
        if (hasTitle){
            rightTv.setVisibility(View.GONE);
        }
    }

    public void showRightTitle(String titleName){
        if (hasTitle){
            rightTv.setVisibility(View.VISIBLE);
            rightOneImg.setVisibility(View.GONE);
            rightTwoImg.setVisibility(View.GONE);
            rightTv.setText(titleName);
        }
    }

    public void hideRightOneImg(){
        if (hasTitle){
            rightOneImg.setVisibility(View.GONE);
        }
    }

    public void showRightOneImg(String imgName){
        if (hasTitle){
            rightOneImg.setVisibility(View.VISIBLE);
            rightTv.setVisibility(View.GONE);

            int imgId = getResources().getIdentifier(imgName, "mipmap", mContext.getPackageName());
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
            rightTwoImg.setVisibility(View.VISIBLE);
            rightTv.setVisibility(View.GONE);

            int imgId = getResources().getIdentifier(imgName, "mipmap", mContext.getPackageName());
            rightTwoImg.setImageResource(imgId);
        }
    }

    public void leftImgTap(View view){
        MyTool.log("left img tap");
    }

    public void leftTitleTap(View view){
        MyTool.log("left title tap");
    }

    public void backViewTap(View view){
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
//------------------------------------------------其他基础方法----------------------------------------------------------------



}
