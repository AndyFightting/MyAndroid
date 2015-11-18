package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment0 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        //------这里面初始化fragment view----------
         setMainView(R.layout.fragment0_layout);
         showTitleView("fragment 0");

        return view;
    }


    public void tabTap(){
        MyTool.log("0 fragment tab tap");

    }


}