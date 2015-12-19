package com.suguiming.myandroid.tool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suguiming on 15/11/18.
 */
public class MyTool {

    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_OK = -1;
    public static final int RESULT_FIRST_USER = 1;

    public static void log(String message) {
        Log.i("myLog", message);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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

    public static Map getMapFromObj(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Map) {
            return (Map) object;
        } else {
            return null;
        }
    }

    public static <T> T getBean(String json, Class<T> clazz) {
        T bean = null;
        try {
            Gson gson = new Gson();
            bean = gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

   //使用：getBeanList(json, User[].class);
    public static <T> List<T> getBeanList(String json, Class<T[]> type) {
        try{
            T[] list = new Gson().fromJson(json, type);
            return Arrays.asList(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
