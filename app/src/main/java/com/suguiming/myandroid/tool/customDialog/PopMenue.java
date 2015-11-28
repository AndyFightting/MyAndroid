package com.suguiming.myandroid.tool.customDialog;

import android.os.Bundle;
import android.view.View;
import com.suguiming.myandroid.R;

//使用的时候就是继承 BaseAlphaView，然后在item点击事件中...像下面那样调用即可
public class PopMenue extends BaseAlphaView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_menu);

    }

    public void itemTap(View view){
        if (itemTapListener != null){
            itemTapListener.itemTap(view);
            dismiss();
        }
    }
}
