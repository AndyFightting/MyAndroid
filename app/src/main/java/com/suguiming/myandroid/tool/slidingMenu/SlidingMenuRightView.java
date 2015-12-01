package com.suguiming.myandroid.tool.slidingMenu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tab0.SlidingMenuActivity;
import com.suguiming.myandroid.tab0.SwipeListViewActivity;

/**
 * Created by suguiming on 15/12/1.
 */
public class SlidingMenuRightView extends RelativeLayout implements View.OnClickListener{

    private SlidingMenuActivity activity;
    private SlidingMenu slidingMenu;

    private Button bt;

    public SlidingMenuRightView(SlidingMenuActivity activity,SlidingMenu slidingMenu){
        super(activity);
        this.activity = activity;
        this.slidingMenu = slidingMenu;

        LayoutInflater inflater =LayoutInflater.from(activity);
        inflater.inflate(R.layout.sliding_menu_right_frame,this);

        bt = (Button)findViewById(R.id.button);
        bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, SwipeListViewActivity.class);
        activity.startActivity(intent);

        slidingMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                slidingMenu.toggle();
            }
        }, 500);
    }
}
