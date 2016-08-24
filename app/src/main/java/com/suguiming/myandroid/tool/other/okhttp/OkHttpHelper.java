package com.suguiming.myandroid.tool.other.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suguiming on 16/8/24.
 */
public class OkHttpHelper {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper()); //异步的话结果要在主线程回调！！

    public static void getRequest(String url,final OkHttpListener listener) {
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String responseString = response.body().string();

                    if (response.isSuccessful()){
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (listener != null){
                                    listener.success(responseString);
                                }
                            }
                        });
                    }else {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (listener != null){
                                    listener.fail();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null){
                                listener.fail();
                            }
                        }
                    });
                }
            });
    }

}