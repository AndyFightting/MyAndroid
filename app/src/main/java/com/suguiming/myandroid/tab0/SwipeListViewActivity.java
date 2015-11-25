package com.suguiming.myandroid.tab0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.SwipeListAdapter;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.swipListView.BaseSwipeListViewListener;
import com.suguiming.myandroid.tool.swipListView.SwipeListView;

public class SwipeListViewActivity extends BaseActivity {

    private SwipeListView swipeListView;
    private int num = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_swipe_list_view);
        showTitleView("SwipeListView");
        showLeftImg("back_img");

        swipeListView = (SwipeListView)findViewById(R.id.swipe_list);
        swipeListView.setAdapter(new SwipeListAdapter(this,num));

        swipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
        swipeListView.setOffsetLeft(MyTool.getScreenWidthPx(this) - MyTool.pxFromDp(this, 40));

        swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }

            @Override
            public void onListChanged() {
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d("swipe", String.format("onStartClose %d", position));
            }

            @Override
            public void onClickFrontView(int position) {
                Log.d("swipe", String.format("onClickFrontView %d", position));
            }

            @Override
            public void onClickBackView(int position) {
                Log.d("swipe", String.format("onClickBackView %d", position));
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {

            }

        });


    }

}
