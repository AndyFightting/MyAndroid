package com.suguiming.myandroid.tool;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suguiming on 15/11/20.
 */
public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeAllActivity(){
        activityList.clear();
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static int getActivityNum(){
        return activityList.size();
    }

}
