package com.suguiming.myandroid.base.volley;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;
import com.suguiming.myandroid.base.MainApplication;
import com.suguiming.myandroid.tool.MyTool;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.Set;

//再把volley包装一下使用更简单
public class VolleyHelper {

    public static void getRequest(String url, final RequestListener requestListener) {
        doRequest(Request.Method.GET, url, null, requestListener);
    }
    public static void postRequest(String url, final Map<String, Object> params, final RequestListener requestListener) {
        doRequest(Request.Method.POST, url, params, requestListener);
    }

    private static void doRequest(int method, String url, final Map<String, Object> params, final RequestListener requestListener) {
        final MainApplication application = MainApplication.getApplication();
        MultiPartStringRequest multiPartStringRequest = new MultiPartStringRequest(
                method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.json(response);  //成功打印
                        if (requestListener != null) {
                            requestListener.requestSuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int code = 0;
                        String msg = null;
                        if (error != null && error.networkResponse != null) {
                            code = error.networkResponse.statusCode;
                            msg = new String(error.networkResponse.data);
                        }

                        Log.i("myLog", "-----请求失败 code:" + code + "  信息:" + msg, error); //失败打印
                        if (requestListener != null) {
                            requestListener.requestFailed(error, code, msg);
                            if (code == 400) {//说明有错误的 message
                                try {
                                    JSONObject jsonObject = new JSONObject(msg);
                                    MyTool.showToast(application, jsonObject.getString("msg"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                MyTool.showToast(application, "请检查网络");
                            }
                        }
                    }
                });

        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                Object obj = params.get(key);
                if (obj instanceof File) {
                    multiPartStringRequest.addFileUpload(key, (File) obj);
                } else {
                    multiPartStringRequest.addStringUpload(key, obj.toString());
                }
            }
        }
        application.addToQueue(multiPartStringRequest);
    }

    //--------------

    private void test(){

        RequestQueue newRequestQueue = Volley.newRequestQueue(null);
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
                Log.e("TAG", response);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("TAG", error.getMessage(), error);
            }
        });


    }



}
