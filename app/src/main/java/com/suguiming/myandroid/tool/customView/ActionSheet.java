package com.suguiming.myandroid.tool.customView;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/28.
 */
public class ActionSheet extends BaseAlphaView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_sheet);

    }

    public void itemTap(View view){
        if (itemTapListener != null){
            itemTapListener.itemTap(view);
            dismiss();
        }
    }

}
