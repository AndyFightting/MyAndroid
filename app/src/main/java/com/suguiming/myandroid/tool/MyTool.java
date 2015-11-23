package com.suguiming.myandroid.tool;

import android.content.Context;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    public static void saveTextFile(Context context,String fileName,String text){
        FileOutputStream outputStream ;
        BufferedWriter bufferedWriter = null;
        try {
           outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(text);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void getTextFile(Context context,String fileName){
        FileInputStream inputStream;
        BufferedReader bufferReader = null;
        StringBuilder contentBuilder = new StringBuilder();
        try {
            inputStream = context.openFileInput(fileName);
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineText = "";
            while ((lineText = bufferReader.readLine()) != null){
                contentBuilder.append(lineText);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
           if (bufferReader != null){
               try {
                   bufferReader.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
        }
    }


}
