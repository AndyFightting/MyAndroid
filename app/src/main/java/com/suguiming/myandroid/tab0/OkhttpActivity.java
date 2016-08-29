package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.other.okhttp.OkHttpHelper;
import com.suguiming.myandroid.tool.other.okhttp.OkHttpListener;
import com.suguiming.myandroid.tool.utils.SelectPhotoUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OkhttpActivity extends BaseActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_okhttp);
        showTitleView("okhttp");
        showLeftImg("back_img");

        button = (Button) findViewById(R.id.button_okhttp);
        imageView = (ImageView) findViewById(R.id.image_okhttp);
    }

    public void getRequest(View v) {
        OkHttpHelper.getRequest(Task.HOST + "notification", new OkHttpListener() {
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
        Map<String, String> params = new HashMap<>();
        params.put("phone", "18301943273");
        params.put("password", "123");
        params.put("deviceType", "1");
        params.put("deviceToken", "token");

        OkHttpHelper.postStringMap(Task.HOST + "user/login", params, new OkHttpListener() {
            @Override
            public void success(String response) {
                showToast("成功 -- 看打印");
            }

            @Override
            public void fail() {
                showToast("失败");
            }
        });
    }

    public void upload(View v) {
        //步骤1
        SelectPhotoUtil.beginSelect(this, new SelectPhotoUtil.ResultListener() {
            @Override
            public void complete(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                uploadImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //步骤2 (相机，裁剪，相册)
        if (requestCode == Task.TAKE_PHOTO_CODE || requestCode == Task.CROP_PHOTO_CODE || requestCode == Task.ALBUM_CHOOSE_CODE) {
            SelectPhotoUtil.processPhoto(requestCode, resultCode, data);
        }
    }

    private void uploadImage() {
        //后台要的参数....
        Map<String, Object> paramter = new HashMap<>();
        paramter.put("phone", "12123286780");//注册后就不能再注册了
        paramter.put("password", "123");
        paramter.put("deviceType", "1");
        paramter.put("deviceToken", "tmp_token");
        paramter.put("userType", "0");
        paramter.put("userName", "hello");
        paramter.put("file", new File(SelectPhotoUtil.getPhotoPath()));//上传图片文件

        String url = Task.HOST + "user/register/1.4";

        OkHttpHelper.postMultipleMap(url, paramter, new OkHttpListener() {
            @Override
            public void success(String response) {
                showToast("成功啦");
            }

            @Override
            public void fail() {
                showToast("失败了");
            }
        });
    }



}
