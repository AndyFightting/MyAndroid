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
public class SlidingMenuMainView extends RelativeLayout implements View.OnClickListener{

    private SlidingMenuActivity activity;
    private SlidingMenu slidingMenu;

    private Button leftBt;
    private Button rightBt;
    private Button centerBt;

    public SlidingMenuMainView(SlidingMenuActivity activity,SlidingMenu slidingMenu){
        super(activity);
        this.activity = activity;
        this.slidingMenu = slidingMenu;

        LayoutInflater inflater =LayoutInflater.from(activity);
        inflater.inflate(R.layout.sliding_menu_main_frame,this);

        leftBt = (Button)findViewById(R.id.left_bt);
        leftBt.setOnClickListener(this);

        rightBt = (Button)findViewById(R.id.right_bt);
        rightBt.setOnClickListener(this);

        centerBt = (Button)findViewById(R.id.center_bt);
        centerBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.left_bt:
              slidingMenu.showMenu();
              break;
          case R.id.right_bt:
              slidingMenu.showSecondaryMenu();
              break;
          case R.id.center_bt:
              Intent intent = new Intent(activity, SwipeListViewActivity.class);
              activity.startActivity(intent);
              break;
      }
    }
}
