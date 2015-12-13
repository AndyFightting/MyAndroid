package com.suguiming.myandroid.tool.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.other.BackCycleService;

/**
 * Created by suguiming on 15/11/22.
 */
public class MainBroadcastReceiver extends BroadcastReceiver {
    @Override
    //这个接收器只做交接，不做具体处理，具体处理放对应的类里去。
    public void onReceive(final Context context, final Intent intent) {
        switch (intent.getAction()) {
            //网络变化监听
            case ConnectivityManager.CONNECTIVITY_ACTION:
                NetworkUtil.showType(context);
                break;
            //强制退出
            case Task.LOGIN_OUT_ACTION:
                ActivityManager.forceLoginOut();
                break;
            //收到短信
            case Task.RECEIVE_SMS_ACTION:
                SMSUtil.receiveSMS(context, intent);
                break;
            //发短信状态监听
            case Task.SEND_SMS_RESULT_ACTION:
                SMSUtil.sendSMSResult(context, intent, getResultCode());
                break;
            //后台循环任务通知
            case Task.BACKGROUND_CYCLE_ACTION:
                BackCycleService.startService(context);
                break;
        }
    }
}
