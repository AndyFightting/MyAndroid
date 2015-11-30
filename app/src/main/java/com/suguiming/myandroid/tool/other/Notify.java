package com.suguiming.myandroid.tool.other;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/29.
 */
public class Notify {

    public void send(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher,"您有新的消息",System.currentTimeMillis());
        notification.notify();
    }

}
