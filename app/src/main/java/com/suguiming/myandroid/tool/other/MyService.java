package com.suguiming.myandroid.tool.other;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/12/13.
 */
public class MyService extends Service {

    private ServiceBinder binder = new ServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyTool.showToast(this, "service 创建");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //开始处理耗时操作


                stopSelf();//处理完结束自己
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        MyTool.showToast(this, "service 结束");
        super.onDestroy();
    }


    public static void startService(Context context) {
        Intent intent = new Intent(context, MyService.class);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, MyService.class);
        context.stopService(intent);
    }

    //Binder
    public class ServiceBinder extends Binder {
        //都是模拟的方法
        public void start() {
            Log.i("bind", "start");
        }

        public int getProgress() {
            Log.i("bind", "progress");
            return 0;
        }

        public void stop() {
            Log.i("bind", "stop");

        }
    }

}
