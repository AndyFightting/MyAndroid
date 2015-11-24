package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.UserAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.bean.User;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.swipListView.BaseSwipeListViewListener;
import com.suguiming.myandroid.tool.swipListView.SwipeListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment0 extends BaseFragment {

    private SwipeListView userListView;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private List<String> nameList = new ArrayList<>();

    private PtrFrameLayout refreshLayout;//刷新框架
    private boolean canLoadMore = true;

    public String TAG = "smgLog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        //------这里面初始化fragment view----------
         setMainView(R.layout.fragment0_layout);
         showTitleView("课程统计");

        initNameList();
        addUserToList(9);

        userListView = (SwipeListView)mainView.findViewById(R.id.user_list);
        userAdapter = new UserAdapter(mainActivity,R.layout.item_user,userList);
        userListView.setAdapter(userAdapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemTap(position);
            }
        });

        addRefreshHeader();
        addRefreshFooter();

        //SwipeListView 属性设置----
        userListView.setSwipeMode(3);
        userListView.setOffsetLeft(MyTool.getScreenWidthPx(mainActivity) * 2 / 3);
        userListView.setAnimationTime(300);
        userListView.setSwipeOpenOnLongPress(false);
        userListView.setSwipeCloseAllItemsWhenMoveList(true);


        userListView.setSwipeListViewListener(new BaseSwipeListViewListener()
        {
            @Override
            public void onChoiceChanged(int position, boolean selected)
            {
                Log.d(TAG, "onChoiceChanged:" + position + ", " + selected);
            }

            @Override
            public void onChoiceEnded()
            {
                Log.d(TAG, "onChoiceEnded");
            }

            @Override
            public void onChoiceStarted()
            {
                Log.d(TAG, "onChoiceStarted");
            }

            @Override
            public void onClickBackView(int position)
            {
                Log.d(TAG, "onClickBackView:" + position);
            }

            @Override
            public void onClickFrontView(int position)
            {
                Log.d(TAG, "onClickFrontView:" + position);
            }

            @Override
            public void onClosed(int position, boolean fromRight)
            {
                Log.d(TAG, "onClosed:" + position + "," + fromRight);
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions)
            {
                Log.d(TAG, "onDismiss");

            }

            @Override
            public void onFirstListItem()
            {
                Log.d(TAG, "onFirstListItem");
            }

            @Override
            public void onLastListItem()
            {
                Log.d(TAG, "onLastListItem");
            }

            @Override
            public void onListChanged()
            {
                Log.d(TAG, "onListChanged");


            }

            @Override
            public void onMove(int position, float x)
            {
                Log.d(TAG, "onMove:" + position + "," + x);
            }

            @Override
            public void onOpened(int position, boolean toRight)
            {
                Log.d(TAG, "onOpened:" + position + "," + toRight);
            }

            @Override
            public void onStartClose(int position, boolean right)
            {
                Log.d(TAG, "onStartClose:" + position + "," + right);
            }

            @Override
            public void onStartOpen(int position, int action, boolean right)
            {
                Log.d(TAG, "onStartOpen:" + position + "," + action + ","
                        + right);
            }
        });

        return view;
    }

    private void addRefreshHeader(){
        refreshLayout = (PtrFrameLayout) mainView.findViewById(R.id.fragment_ptr_home_ptr_frame);
        StoreHouseHeader header = new StoreHouseHeader(mainActivity);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("HELLO WORLD"); //只能用字母A到Z,以数字0到9。

        refreshLayout.setDurationToCloseHeader(1500);
        refreshLayout.setHeaderView(header);
        refreshLayout.addPtrUIHandler(header);

        refreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //开始刷新
                beginRefresh();
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    public void addRefreshFooter(){
        userListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 当不滚动时
                        if (userListView.getLastVisiblePosition() == (userListView.getCount() - 1)) {// 判断滚动到底部
                            if (canLoadMore) {    //加载更多
                                beginLoadMore();
                                showLoadMoreFooter("加载中...");
                            } else {               //已经没有更多
                                showLoadMoreFooter("数据全部加载完毕");
                            }
                            break;
                        }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void beginRefresh(){
        userListView.closeOpenedItems();

        canLoadMore = true;
        userList.clear();
        addUserToList(9);

        userAdapter.notifyDataSetChanged();
    }

    private void beginLoadMore() {
        addUserToList(3);
        userAdapter.notifyDataSetChanged();

        if (userList.size() >15){ //假设最多15条数据
            canLoadMore = false;
        }
    }

    public void tabTap(){
        refreshLayout.autoRefresh();
    }

    //构造假数据
    private void addUserToList(int addNum){
        int listNum = userList.size();
        for (int i=0;i<addNum;i++){
            String nameStr;
            if (listNum+i < nameList.size()){
                nameStr = nameList.get(listNum+i);
            }else {
                nameStr = "未完待续";
            }
            userList.add(new User(nameStr,R.mipmap.tab0s));
        }
    }

    //构造假数据
    private void initNameList(){
        nameList.add("模拟下线通知，在任何地方都可以");
    }

    private void itemTap(int position){
        switch (position){
            case 0://模拟强制退出
                Intent intent = new Intent(Task.BROADCAST_LOGIN_OUT);
                mainActivity.sendBroadcast(intent);
               break;

        }
    }

}