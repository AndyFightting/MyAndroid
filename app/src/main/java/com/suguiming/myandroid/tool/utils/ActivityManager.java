package com.suguiming.myandroid.tool.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.suguiming.myandroid.login.LoginActivity;
import com.suguiming.myandroid.tool.MyTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suguiming on 15/11/20.
 */
public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    //强制下线
    public static void forceLoginOut() {
        final Activity topActivity = activityList.get(activityList.size() - 1);

        AlertDialog.Builder builder = new AlertDialog.Builder(topActivity);
        builder.setCancelable(false);
        builder.setTitle("提示:");
        builder.setMessage("该账号在其他地方登陆，您已被强制下线！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityManager.finishAllActivity();
                //进入登陆页 --
                Intent loginIntent = new Intent(topActivity, LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                topActivity.startActivity(loginIntent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        //----下面做本地数据清理工作----

        MyTool.showToast(topActivity, "本地数据已清理完毕");

    }

}
