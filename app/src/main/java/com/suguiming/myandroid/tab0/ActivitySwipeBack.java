package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseSwipeActivity;

public class ActivitySwipeBack extends BaseSwipeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_activity_swipe_back);
    }

    public void textTaped(View v){
        Intent intent = new Intent(this,ActivitySwipeBack.class);
        startActivity(intent);
    }
}
