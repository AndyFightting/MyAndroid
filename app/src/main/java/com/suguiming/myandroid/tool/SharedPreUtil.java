package com.suguiming.myandroid.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {

    public static final String FILE_NAME = "shared_preference";

    public static void put(Context context,String key,Object value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String)
        {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer)
        {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float)
        {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long)
        {
            editor.putLong(key, (Long) value);
        } else
        {
            editor.putString(key, value.toString());
        }
        editor.apply();
    }

    public static Object get(Context context, String key, Object defaultValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);

        if (defaultValue instanceof String)
        {
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer)
        {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean)
        {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float)
        {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long)
        {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    public static void remove(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeAll(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
