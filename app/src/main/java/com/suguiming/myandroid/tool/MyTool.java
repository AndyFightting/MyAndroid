package com.suguiming.myandroid.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by suguiming on 15/11/18.
 */
public class MyTool {

    public static void log(String message){
        Log.i("myLog", message);
    }

    public static void shotToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
