package com.suguiming.myandroid.tab0;

import android.os.Bundle;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

public class PresentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_present);
        showTitleView("iOS present view");
        showLeftImg("back_img");
    }

}
