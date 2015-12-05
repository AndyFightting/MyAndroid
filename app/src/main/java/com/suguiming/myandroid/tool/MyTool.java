package com.suguiming.myandroid.tool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by suguiming on 15/11/18.
 */
public class MyTool {

    public static void log(String message){
        Log.i("myLog", message);
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

    public static String getTextFile(Context context,String fileName){
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
      return contentBuilder.toString();
    }

    public static void removeFile(Context context, String fileName) {
        String filePath = context.getFilesDir()+"/"+fileName;
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }

    public static File createDir(Context context,String directoryName){
        String dirPath = context.getFilesDir()+"/"+directoryName;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
       return dirFile;
    }

    public static void removeDir(Context context,String directoryName){
        String dirPath = context.getFilesDir()+"/"+directoryName;
        File dirFile = new File(dirPath);
        removeDir(dirFile);
    }

    public static void removeDir(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                removeDir(childFiles[i]);
            }
            file.delete();
        }
    }


    //截屏 包括状态栏
    public static Bitmap getScreenShotWithStatusBar(Activity activity)
        {
           View view = activity.getWindow().getDecorView();
           view.setDrawingCacheEnabled(true);
           view.buildDrawingCache();
           Bitmap bmp = view.getDrawingCache();
           int width = getScreenWidthPx(activity);
           int height = getScreenHeightPx(activity);
           Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
           view.destroyDrawingCache();
           return bp;

       }

    //截屏 不包括状态栏
     public static Bitmap getScreenShotWithoutStatusBar(Activity activity)
     {
             View view = activity.getWindow().getDecorView();
             view.setDrawingCacheEnabled(true);
             view.buildDrawingCache();
             Bitmap bmp = view.getDrawingCache();
             Rect frame = new Rect();
             activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
             int statusBarHeight = frame.top;

             int width = getScreenWidthPx(activity);
             int height = getScreenHeightPx(activity);
             Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
             view.destroyDrawingCache();
             return bp;
         }

}
