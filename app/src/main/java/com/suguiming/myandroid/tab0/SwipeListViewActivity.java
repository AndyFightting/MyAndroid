package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.SwipeListAdapter;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.swipListView.SwipeListView;

public class SwipeListViewActivity extends BaseActivity {

    private SwipeListView swipeListView;
    private int num = 10;
    private SwipeListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_swipe_list_view);
        showTitleView("SwipeListView");
        showLeftImg("back_img");

        swipeListView = (SwipeListView)findViewById(R.id.swipe_list);
        adapter = new SwipeListAdapter(this,num);
        swipeListView.setAdapter(adapter);
        swipeListView.setOffsetLeft(80);//设置滑动距离 单位dip

        adapter.setInnerItemTapListener(new SwipeListAdapter.InnerItemTapListener() {
            @Override
            public void innerItemTap(View view, int position) {
                swipeListView.closeOpenedItems();
                swipeListView.dismiss(position);

                //动画结束后再刷新listView
                swipeListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num = num -1;
                        adapter.setNum(num);
                        adapter.notifyDataSetChanged();
                    }
                },200); //动画时间
            }
        });


    }

}
