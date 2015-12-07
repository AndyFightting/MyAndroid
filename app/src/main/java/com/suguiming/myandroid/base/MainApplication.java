package com.suguiming.myandroid.base;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by suguiming on 15/11/18.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("myLog");

    }

}