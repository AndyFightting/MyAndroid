package com.suguiming.myandroid.tool;

import android.content.Context;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by suguiming on 15/11/18.
 */
public class MyTool {

    public static void log(String message){
        Log.d("myLog",message);
    }

    public static boolean isEmptyTv(TextView textView){
        if (textView == null || textView.getText() == null || "".equals(textView.getText().toString().trim())){
            return true;
        }else {
            return false;
        }
    }

    // 50 像素
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
    }

    public static String getSysVersion()
    {
        return  android.os.Build.VERSION.RELEASE;
    }

    public static int getScreenWidthPx(Context context){
        return  context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeightPx(Context context){
        return  context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getXdpi(Context context){
        return  context.getResources().getDisplayMetrics().xdpi;
    }

    public static float getYdpi(Context context){
        return  context.getResources().getDisplayMetrics().ydpi;
    }

    public static float getDensity(Context context){
       return context.getResources().getDisplayMetrics().density;
    }

    public static int pxFromDp(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dpFromPx(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = locationManager.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    public static void shotToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
