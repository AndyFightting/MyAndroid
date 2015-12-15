package com.suguiming.myandroid.tool.customView.actionSheets;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/12/14.
 */
public class ShareSheet extends BaseSheetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_share_sheet);

    }

    public void itemTap(View view) {
        if (itemTapListener != null) {
            itemTapListener.itemTap(view);
        }
        dismiss();

//分享内容不变的话 在这统一处理就可以了，否则就用接口传递下去区分处理
        switch (view.getId()) {
            case R.id.peng_you:
                MyTool.showToast(this, "分享朋友圈");
                break;
            case R.id.wei_xin:
                MyTool.showToast(this, "分享微信");
                break;
        }
    }
}
