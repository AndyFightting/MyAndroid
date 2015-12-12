package com.suguiming.myandroid.tool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.suguiming.myandroid.tool.MyTool;

/**
 * Created by suguiming on 15/12/5.
 */
public class NetworkUtil {

    public static boolean isEnable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()){
           return true;
        }else {
           return false;
        }
    }

    public static NetworkType getType(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()){
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                //WiFi网络
                return NetworkType.WIFI;
            }else if(networkInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
                //有线链接
                return NetworkType.WIFI;
            }else{
                //3g网络
                return NetworkType.MOBILE;
            }
        }else {
            return NetworkType.NONE;
        }
    }

    public static void showType(Context context){
        NetworkType type = getType(context);
        if (type == NetworkType.WIFI){
            MyTool.showToast(context, "已连接wifi");
        }else if(type == NetworkType.MOBILE){
            MyTool.showToast(context,"使用移动网络");
        }else {
            MyTool.showToast(context,"无网络链接");
        }
    }

    //打开网络设置界面
   public static void openNetworkSetting(Activity activity) {
       activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
   }

}
