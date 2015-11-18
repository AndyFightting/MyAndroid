package com.suguiming.myandroid.tool;

import android.util.Log;
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

}
