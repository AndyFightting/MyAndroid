package com.suguiming.myandroid.tab0;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.other.okhttp.OkHttpHelper;
import com.suguiming.myandroid.tool.other.okhttp.OkHttpListener;

public class OkhttpActivity extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_okhttp);
        showTitleView("okhttp");
        showLeftImg("back_img");

        button = (Button)findViewById(R.id.button);

    }

    public void getRequest(View v) {
        String url = Task.HOST + "notification";

        OkHttpHelper.getRequest(url, new OkHttpListener() {
            @Override
            public void success(String response) {
                MyTool.logJson(response);
                showToast("请求成功 看打印");
                button.setText("在主线程中回调");
            }

            @Override
            public void fail() {
                showToast("请求失败");
            }
        });
    }

    public void postRequest(View v) {

    }

}
