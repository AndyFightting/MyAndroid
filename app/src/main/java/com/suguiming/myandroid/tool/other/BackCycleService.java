package com.suguiming.myandroid.tool.other;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.suguiming.myandroid.tool.Task;

import de.greenrobot.event.EventBus;

/**
 * Created by suguiming on 15/12/13.
 */
public class BackCycleService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //后台执行任务

                EventBus.getDefault().post(new EventObject());

                Log.i("myLog", "开始执行后台任务");
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long alarmTime = SystemClock.elapsedRealtime() + 5 * 1000; //每5秒执行一次后台任务

        Intent serviceIntent = new Intent(Task.BACKGROUND_CYCLE_ACTION);
        PendingIntent pendIntent = PendingIntent.getBroadcast(this, Task.CYCLE_SERVICE_CODE, serviceIntent, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, alarmTime, pendIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, BackCycleService.class);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        AlarmManager manager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        Intent serviceIntent = new Intent(Task.BACKGROUND_CYCLE_ACTION);
        PendingIntent pendIntent = PendingIntent.getBroadcast(context, Task.CYCLE_SERVICE_CODE, serviceIntent, 0);
        manager.cancel(pendIntent);

        Intent intent = new Intent(context, BackCycleService.class);
        context.stopService(intent);
    }
}
