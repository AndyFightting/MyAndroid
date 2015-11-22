package com.suguiming.myandroid.login;

import android.os.Bundle;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_login);
        showTitleView("登录");
    }

}
