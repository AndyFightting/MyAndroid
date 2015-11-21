package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.UserAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.bean.Student;
import com.suguiming.myandroid.bean.User;
import com.suguiming.myandroid.tool.MyTool;

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

    private ListView userListView;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private List<String> nameList = new ArrayList<>();

    private PtrFrameLayout refreshLayout;//刷新框架
    private boolean canLoadMore = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        //------这里面初始化fragment view----------
         setMainView(R.layout.fragment0_layout);
         showTitleView("课程统计");

        initNameList();
        addUserToList(9);

        userListView = (ListView)mainView.findViewById(R.id.user_list);
        userAdapter = new UserAdapter(mainActivity,R.layout.item_user,userList);
        userListView.setAdapter(userAdapter);

        addRefreshHeader();
        addRefreshFooter();

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
            userList.add(new User(nameStr+(i+listNum),R.mipmap.tab0s));
        }
    }

    //构造假数据
    private void initNameList(){
        nameList.add("ormList使用");
        nameList.add("volley使用");
    }

}