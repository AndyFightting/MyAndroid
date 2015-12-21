package com.suguiming.myandroid.tab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.tool.other.EventObject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment3 extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment3_layout);
        showTitleView("我");

        //注册EventBus
        EventBus.getDefault().register(this);

        return view;
    }

    public void tabTap(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }

    //EventBus 事件接收方法

    @Subscribe
    public void onEventMainThread(EventObject object){

        showToast("hahah");

    }
}
