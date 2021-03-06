package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.UserAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.base.BaseSwipeActivity;
import com.suguiming.myandroid.bean.User;
import com.suguiming.myandroid.tool.other.EventObject;
import com.suguiming.myandroid.tool.other.OttoEventObject;
import com.suguiming.myandroid.tool.other.OttoHelper;
import com.suguiming.myandroid.tool.utils.ItemTapListener;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.CustomDialog;
import com.suguiming.myandroid.tool.customView.HUD;
import com.suguiming.myandroid.tool.customView.PopMenue;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
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
        View view = super.onCreateView(inflater, container, savedInstanceState);

        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment0_layout);
        showTitleView("课程统计");
        showRightTitle("菜单"); //看BaseFragment, 重写rightTitleTap()得到点击事件

        initNameList();
        addUserToList(9);

        userListView = (ListView) mainView.findViewById(R.id.user_list);
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

        if (userList.size() > 20) { //假设最多条数据
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
            userList.add(new User(nameStr, R.mipmap.tab0s));
        }
    }

    @Override
    public void rightTitleTap(View view) {
        PopMenue.show(mainActivity, PopMenue.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                switch (view.getId()) {
                    case R.id.activity_tv:
                        showToast("点了第一个");
                        break;
                    case R.id.pop_tv:
                        showToast("点了第二个");
                        break;
                }
            }
        });
    }

    //构造假数据
    private void initNameList() {
        nameList.add("模拟下线通知，在任何地方都可以");
        nameList.add("SwipeListView Demo");
        nameList.add("自定义Dialog");
        nameList.add("淡入淡出");
        nameList.add("模仿 iOS present push");
        nameList.add("模仿 iOS action sheet");
        nameList.add("show HUD");
        nameList.add("sliding menu");
        nameList.add("通讯录,短信,通知");
        nameList.add("Volley 使用");
        nameList.add("音频,视频");
        nameList.add("Thread,Service");
        nameList.add("Picasso");
        nameList.add("EventBus");
        nameList.add("Otto");
        nameList.add("okhttp");
        nameList.add("DrawerLayout");
        nameList.add("手势滑动返回");
        nameList.add("AsyncTask使用");
        nameList.add("RxJava使用");

    }

    private void itemTap(int position) {
        Intent intent;
        switch (position) {
            case 0://模拟强制退出
                intent = new Intent(Task.LOGIN_OUT_ACTION);
                mainActivity.sendBroadcast(intent);
                break;
            case 1:
                intent = new Intent(mainActivity, SwipeListViewActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 2:
                CustomDialog customDialog = new CustomDialog(mainActivity, new ItemTapListener() {
                    @Override
                    public void itemTap(View view) {
                        switch (view.getId()) {
                            case R.id.out_tv:
                                showToast("点击退出");
                                break;
                            case R.id.close_tv:
                                showToast("点击关闭");
                                break;
                        }
                    }
                });
                customDialog.show();

                break;
            case 3:
                intent = new Intent(mainActivity, FadeInOutActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 4:
                intent = new Intent(mainActivity, PresentActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 5:
                intent = new Intent(mainActivity, ActionSheetActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 6:
                HUD.show(mainActivity, "登录中...");
                break;
            case 7:
                intent = new Intent(mainActivity, SlidingMenuActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 8:
                intent = new Intent(mainActivity,ReadContactActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 9:
                intent = new Intent(mainActivity,VolleyActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 10:
                intent = new Intent(mainActivity,AudioActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 11:
                intent = new Intent(mainActivity,ThreadActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 12:
                intent = new Intent(mainActivity,PicassoActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 13:
                EventObject eventObject = new EventObject();
                eventObject.eventId = 10086;

                EventBus.getDefault().post(eventObject);
                break;
            case 14:
                OttoEventObject object = new OttoEventObject();
                object.eventId = 110;
                object.msg = "hello otto";

                OttoHelper.getInstance().post(object);
                break;
            case 15:
                intent = new Intent(mainActivity,OkhttpActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 16:
                intent = new Intent(mainActivity,PureCodeActivity.class);
                mainActivity.startActivity(intent);
                break;
            case 17:
                intent = new Intent(mainActivity,ActivitySwipeBack.class);
                mainActivity.startActivity(intent);
                break;
            case 18:
                intent = new Intent(mainActivity,AsyncTaskActivity.class);
                mainActivity.startActivity(intent);
                break;
        }
    }

}