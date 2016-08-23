package com.suguiming.myandroid.tab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.tool.other.EventObject;
import com.suguiming.myandroid.tool.other.OttoEventObject;
import com.suguiming.myandroid.tool.other.OttoHelper;

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

        //注册otto bus
        OttoHelper.getInstance().register(this);

        return view;
    }

    public void tabTap(){}

    @Override
    public void onDestroy() {
        //注销EventBus
        EventBus.getDefault().unregister(this);

        //注销otto bus
        OttoHelper.getInstance().unregister(this);

        super.onDestroy();
    }

//    EventBus 事件接收4种方法
//
//    onEvent: 如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，
//    也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
//
//    onEventMainThread: 如果使用onEventMainThread作为订阅函数，
//    那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，
//    这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
//
//    onEventBackground: 如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，
//    那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。
//
//    onEventAsync：使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
    @Subscribe
    public void onEventMainThread(EventObject object){
        showToast("收到EventBus啦"+object.getEventId());
    }

    //otto 事件接收
    //注解一定要有,并且方法的用 public 修饰的.方法名可以随意取,重点是参数,它是根据参数进行判断!
    //@Produce也是用于方法，并且这个方法的参数必须为空，返回值是你要订阅的事件的类型。
    @com.squareup.otto.Subscribe
    public void receiveOttoEventObject(OttoEventObject object){
        showToast("收到otto啦"+object.getMsg());
    }
}
