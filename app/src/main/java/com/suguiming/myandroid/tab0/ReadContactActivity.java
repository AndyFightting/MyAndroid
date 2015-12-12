package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.utils.SMSUtil;

public class ReadContactActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_read_contact);
        showTitleView("读通讯录");
        showLeftImg("back_img");

    }


    public void noticeTap(View view){
        SMSUtil.sendSMS(this);

    }

}
