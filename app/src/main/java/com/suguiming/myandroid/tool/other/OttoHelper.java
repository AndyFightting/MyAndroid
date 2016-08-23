package com.suguiming.myandroid.tool.other;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by suguiming on 16/8/23.
 */
public class OttoHelper {

    //默认是在MainThread中执行,与下面等价。如果不关心在哪个线程执行，可以使用ThreadEnforcer.ANY，
    //甚至可以使用自己实现的ThreadEnforcer接口。
    private static final Bus bus = new Bus();
    //private static final Bus bus = new Bus(ThreadEnforcer.MAIN);

    public static Bus getInstance() {
        return bus;
    }
}
