package com.suguiming.myandroid.tool.customView.actionSheets;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/28.
 */
public class PhotoSheet extends BaseSheetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_photo_sheet);

    }

    public void itemTap(View view){
        if (itemTapListener != null){
            itemTapListener.itemTap(view);
        }
        dismiss();
    }

}
