package com.suguiming.myandroid.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

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

    //通过文件名获取资源id ：getResIdByName("icon", R.drawable.class);
    public static int getResIdByName(String name, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(name);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
