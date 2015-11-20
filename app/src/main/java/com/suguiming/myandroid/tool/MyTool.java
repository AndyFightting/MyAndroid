package com.suguiming.myandroid.tool;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by suguiming on 15/11/18.
 */
public class MyTool {

    public static void log(String message){
        Log.i("myLog",message);
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
        //-------方式一 -------------------
        int statusHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height); //像素高度
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
        //-------方式二 -------------------
        /*
        int statusHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
        */
    }

    public static String getSysVersion()
    {
        return  android.os.Build.VERSION.RELEASE;
    }

    public static int getScreenWidth(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return  dm.widthPixels;
    }

    public static int getScreenHeight(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return  dm.heightPixels;
    }

    public static int pixFromDip(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dipFromPix(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
