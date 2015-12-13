package com.suguiming.myandroid.tool.other;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/12/13.
 */
public class MyIntentService extends IntentService {//就是Service,但自动在子线程执行，且自动结束
    public MyIntentService(){
        super("MyIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {//处理，这个方法已经在子线程里了
        Log.i("线程log:",Thread.currentThread().getId()+"");

    }

    @Override
    public void onDestroy() {
        Log.i("线程log","结束");
        MyTool.showToast(this, "结束");
        super.onDestroy();
    }

    public static void startService(Context context){
        Intent intent = new Intent(context,MyIntentService.class);
        context.startService(intent);
    }

    public static void stopService(Context context){
        Intent intent = new Intent(context,MyIntentService.class);
        context.stopService(intent);
    }
}

