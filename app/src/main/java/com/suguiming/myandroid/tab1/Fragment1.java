package com.suguiming.myandroid.tab1;

import android.os.Bundle;
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
import com.suguiming.myandroid.tool.HanZi2PinYin.PinYinToolkit;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.ORMLiteUse.OrmTestBean;
import com.suguiming.myandroid.tool.ORMLiteUse.OrmTestDao;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment1 extends BaseFragment {

    private ListView userListView;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private List<String> nameList = new ArrayList<>();

    private boolean canLoadMore = true;
    protected PtrFrameLayout refreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment1_layout);
        showTitleView("日历排期");

        initNameList();
        addUserToList(9);

        userListView = (ListView) mainView.findViewById(R.id.user_list1);
        userAdapter = new UserAdapter(mainActivity, R.layout.item_user, userList);
        userListView.setAdapter(userAdapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemTap(position);
            }
        });

        addRefreshHeader();
        addRefreshFooter();

        return view;
    }

    private void addRefreshHeader() {
        refreshLayout = (PtrFrameLayout) mainView.findViewById(R.id.material_style_ptr_frame);

        // header
        final MaterialHeader header = new MaterialHeader(mainActivity);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
        header.setPtrFrameLayout(refreshLayout);

        refreshLayout.setLoadingMinTime(1000);
        refreshLayout.setDurationToCloseHeader(1500);
        refreshLayout.setHeaderView(header);
        refreshLayout.addPtrUIHandler(header);
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh(false);
            }
        }, 100);

        refreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                beginRefresh();

                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                }, 4000);

            }
        });


    }

    public void addRefreshFooter() {
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

    private void beginRefresh() {
        canLoadMore = true;
        userList.clear();
        addUserToList(9);

        userAdapter.notifyDataSetChanged();
    }

    private void beginLoadMore() {
        addUserToList(3);
        userAdapter.notifyDataSetChanged();

        if (userList.size() > 15) { //假设最多15条数据
            canLoadMore = false;
        }
    }

    public void tabTap() {
        refreshLayout.autoRefresh();
    }

    //构造假数据
    private void addUserToList(int addNum) {
        int listNum = userList.size();
        for (int i = 0; i < addNum; i++) {
            String nameStr;
            if (listNum + i < nameList.size()) {
                nameStr = nameList.get(listNum + i);
            } else {
                nameStr = "未完待续";
            }
            userList.add(new User(nameStr, R.mipmap.tab1s));
        }
    }

    //构造假数据
    private void initNameList() {
        nameList.add("汉字转拼音 Pinyin4j");
        nameList.add("ORMLite");
        nameList.add("gson");
    }

    private void itemTap(int position) {
        switch (position) {
            case 0:
                String hanZi = "汉 字 转 拼 音";
                String pinYin = PinYinToolkit.getFullLetter(hanZi);
                showToast(pinYin);
                break;
            case 1:
                OrmTestBean bean = new OrmTestBean("andy", 18);
                OrmTestDao dao = new OrmTestDao(mainActivity);
                dao.add(bean);

                List<OrmTestBean> list = dao.findByName("andy");
                MyTool.log(list.size() + " -------");

                break;
            case 2:



                break;

        }
    }

}
