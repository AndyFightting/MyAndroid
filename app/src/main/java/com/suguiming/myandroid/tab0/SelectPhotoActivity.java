package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.base.volley.RequestListener;
import com.suguiming.myandroid.base.volley.VolleyHelper;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.HUD;
import com.suguiming.myandroid.tool.utils.SelectPhotoUtil;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suguiming on 15/12/12.
 */
public class SelectPhotoActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_select_photo);
        showTitleView("Volley使用");
        showLeftImg("back_img");
        imageView = (ImageView)findViewById(R.id.head_img);
    }

    public void postTap(View view){//测试
        //后台接口要用的参数...
        Map<String ,Object> params = new HashMap<>();
        params.put("phone", "13199999999");
        params.put("password", "q");
        params.put("deviceType", "1");
        params.put("deviceToken", "token");

        HUD.show(this, "请求中...");//因为菊花不想和请求框架耦合，所以单独拿出来
        VolleyHelper.postRequest(Task.HOST + "user/login", params, new RequestListener() {
            @Override
            public void requestSuccess(String response) {
                HUD.setMessage("请求成功!");
                HUD.dismiss();
            }

            @Override
            public void requestFailed(VolleyError error, int code, String message) {
                HUD.setMessage("请求失败!");
                HUD.dismiss();
            }
        });
    }

    public void imageTap(View view){//测试
        //步骤1
        SelectPhotoUtil.beginSelect(this, new SelectPhotoUtil.ResultListener() {
            @Override
            public void complete(Bitmap bitmap) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    uploadImage();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //步骤2 (相机，裁剪，相册)
        if (requestCode == Task.TAKE_PHOTO_CODE || requestCode == Task.CROP_PHOTO_CODE || requestCode == Task.ALBUM_CHOOSE_CODE){
            SelectPhotoUtil.processPhoto(requestCode,resultCode,data);
        }
    }

    private void uploadImage(){
        //后台要的参数....
        Map<String,Object> paramter = new HashMap<>();
        paramter.put("phone","13123486789");
        paramter.put("password","123");
        paramter.put("deviceType","1");
        paramter.put("deviceToken","tmp_token");
        paramter.put("userType","0");
        paramter.put("userName","hello");
        paramter.put("file",new File(SelectPhotoUtil.getPhotoPath()));//上传图片文件

        HUD.show(this, "提交中...");
        String url = Task.HOST+"user/register/1.4";
        VolleyHelper.postRequest(url, paramter, new RequestListener() {
            @Override
            public void requestSuccess(String response) {
                HUD.setMessage("提交成功");
                HUD.dismiss();

            }
            @Override
            public void requestFailed(VolleyError error, int code, String message) {
                HUD.setMessage("提交失败");
                HUD.dismiss();
            }
        });


    }

}
