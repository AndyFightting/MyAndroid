package com.suguiming.myandroid.tab0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.base.volley.RequestListener;
import com.suguiming.myandroid.base.volley.VolleyHelper;
import com.suguiming.myandroid.bean.User;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.HUD;
import com.suguiming.myandroid.tool.utils.SelectPhotoUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suguiming on 15/12/12.
 */
public class VolleyActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_select_photo);
        showTitleView("Volley使用");
        showLeftImg("back_img");
        imageView = (ImageView) findViewById(R.id.head_img);


    }

    public void postTap(View view) {//测试
        //后台接口要用的参数...
        Map<String, Object> params = new HashMap<>();
        params.put("phone", "13888888888");
        params.put("password", "123");
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

    public void imageTap(View view) {//测试
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
        if (requestCode == Task.TAKE_PHOTO_CODE || requestCode == Task.CROP_PHOTO_CODE || requestCode == Task.ALBUM_CHOOSE_CODE) {
            SelectPhotoUtil.processPhoto(requestCode, resultCode, data);
        }
    }

    private void uploadImage() {
        //后台要的参数....
        Map<String, Object> paramter = new HashMap<>();
        paramter.put("phone", "13123286789");
        paramter.put("password", "123");
        paramter.put("deviceType", "1");
        paramter.put("deviceToken", "tmp_token");
        paramter.put("userType", "0");
        paramter.put("userName", "hello");
        paramter.put("file", new File(SelectPhotoUtil.getPhotoPath()));//上传图片文件

        HUD.show(this, "提交中...");
        String url = Task.HOST + "user/register/1.4";
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

    public void gsonBeanTap(View view) {
        Gson gson = new Gson();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User p = new User("andy", i);
            list.add(p);
        }
        String beanListJson = gson.toJson(list);
        String beanJson = gson.toJson(new User("andy", 6));

        //json 转 bean
        User user = MyTool.getBean(beanJson, User.class);
        MyTool.log(user.getName());

        //json 转 list
        List<User> users = MyTool.getBeanList(beanListJson, User[].class);
        MyTool.log(users.get(0).getName());

        //对象 转 json
        MyTool.log(MyTool.getJson(users));
    }

    public void loactionTap(View view) {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null){
                showToast("经度 " + location.getLongitude() + "; 纬度：" + location.getLatitude());
                MyTool.log("经度 " + location.getLongitude() + "; 纬度：" + location.getLatitude());
                loactionToaddress(location);
            }

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null){
                        showToast("经度 " + location.getLongitude() + "; 纬度：" + location.getLatitude());
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
//            locationManager.removeUpdates(listener);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    //反向地理编码，通过经纬度得到地址信息
    private void loactionToaddress(Location location){
      String url = String.format(Task.GOOGLE_GEO_URL, location.getLatitude(), location.getLongitude());

        HUD.show(this,"位置解析中...");
        VolleyHelper.getRequest(url, new RequestListener() {
            @Override
            public void requestSuccess(String response) {
                HUD.setMessage("解析成功");
                HUD.dismiss();
            }

            @Override
            public void requestFailed(VolleyError error, int code, String message) {
                HUD.setMessage("解析失败");
                HUD.dismiss();

            }
        });
    }


}
