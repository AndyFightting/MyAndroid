package com.suguiming.myandroid.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Toast;

import com.suguiming.myandroid.MainActivity;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/11/18.
 */
public class BaseFragment extends Fragment implements View.OnClickListener{

    protected RelativeLayout fatherView;
    protected RelativeLayout titleView;
    protected LinearLayout mainView;
    protected View statusBackView;

    private boolean hasTitle = false;
    private TextView titleTv;
    private ImageView leftImg;
    private TextView leftTitleTv;

    private TextView rightTv;
    private ImageView rightOneImg;
    private ImageView rightTwoImg;

    public MainActivity mainActivity;
    public LayoutInflater mInflater;

    private View loadMoreFooterView;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mainActivity = (MainActivity) activity;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
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

        mInflater = inflater;
        hasTitle = true;
        fatherView = (RelativeLayout)view.findViewById(R.id.father_view);
        mainView = (LinearLayout)view.findViewById(R.id.main_view);
        titleView = (RelativeLayout)view.findViewById(R.id.title_layout);
        titleTv = (TextView)view.findViewById(R.id.title_tv);
        statusBackView = view.findViewById(R.id.status_back_view);

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
        View tmpView = mInflater.inflate(viewId, null);
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

            int imgId = getResources().getIdentifier(imgName, "mipmap", mainActivity.getPackageName());
            leftImg.setImageResource(imgId);
            leftImg.setOnClickListener(this);
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
            rightTv.setVisibility(View.GONE);
        }
    }

    public void showRightTitle(String titleName){
        if (hasTitle){
            rightTv.setOnClickListener(this);
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
            rightOneImg.setOnClickListener(this);
            rightOneImg.setVisibility(View.VISIBLE);
            rightTv.setVisibility(View.GONE);

            int imgId = getResources().getIdentifier(imgName, "mipmap", mainActivity.getPackageName());
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
            rightTv.setVisibility(View.GONE);

            int imgId = getResources().getIdentifier(imgName, "mipmap", mainActivity.getPackageName());
            rightTwoImg.setImageResource(imgId);
        }
    }

    public void leftImgTap(View view){
        MyTool.log("left img tap base fragment");
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

    public void tabTap(){
      MyTool.log("base fragment tab tap");
    }

    public void showToast(String message){
        Toast.makeText(mainActivity,message,Toast.LENGTH_SHORT).show();
    }

    public void showLoadMoreFooter(String message){
        loadMoreFooterView = mInflater.inflate(R.layout.refresh_footer,null);
        TextView titleTv = (TextView)loadMoreFooterView.findViewById(R.id.title);
        titleTv.setText(message);
        fatherView.addView(loadMoreFooterView);

        fatherView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadMoreFooter();
            }
        }, 1000);
    }
    private void hideLoadMoreFooter(){
            fatherView.removeView(loadMoreFooterView);
    }


}
