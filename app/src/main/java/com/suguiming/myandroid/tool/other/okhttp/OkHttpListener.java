package com.suguiming.myandroid.tool.other.okhttp;

/**
 * Created by suguiming on 16/8/24.
 */
public interface OkHttpListener {
    void success(String response);
    void fail();
}
