package com.suguiming.myandroid.tool;

import android.app.Activity;
import android.content.Intent;

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

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishAllActivity(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }

    public static int getActivityNum(){

        return activityList.size();
    }

}
