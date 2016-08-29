package com.suguiming.myandroid.tool.other.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.suguiming.myandroid.tool.MyTool;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by suguiming on 16/8/24.
 */
public class OkHttpHelper {

    private static final Handler mainHandler = new Handler(Looper.getMainLooper()); //异步的话结果要在主线程回调！！
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static void getRequest(String url, final OkHttpListener listener) {
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        beginRequest(request, listener);
    }

    public static void postStringMap(String url, final Map<String, String> params, final OkHttpListener listener) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                builder.add(key, value);
            }
        }

        RequestBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        beginRequest(request, listener);
    }


    public static void postMultipleMap(String url, final Map<String, Object> params, final OkHttpListener listener) {
        MultipartBody.Builder multipleBuilder = new MultipartBody.Builder();
        multipleBuilder.setType(MultipartBody.FORM);

        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                Object obj = params.get(key);

                if (obj instanceof File) {
                    File tmpFile = (File) obj;
                    multipleBuilder.addFormDataPart(key,tmpFile.getName(),MultipartBody.create(MediaType.parse("image/png"), tmpFile));
                } else {
                    multipleBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(null, (String)obj));
                }
            }
        }

        RequestBody multipleBody = multipleBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipleBody)
                .build();

        beginRequest(request, listener);
    }

    private static void beginRequest(Request request, final OkHttpListener listener) {
        Call call = client.newCall(request);

        call.enqueue(new Callback() {//enqueue是异步请求
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                final int code = response.code();

                if (response.isSuccessful()) {
                    MyTool.logJson(responseString);

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.success(responseString);
                            }
                        }
                    });
                } else {
                              //请求失败：    400  ---------   {"code":100002,"msg":"手机号已注册","url":""}
                    MyTool.log("请求失败：" + code + "---------" + responseString);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.fail();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                MyTool.log("请求错误");
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.fail();
                        }
                    }
                });
            }
        });
    }


}