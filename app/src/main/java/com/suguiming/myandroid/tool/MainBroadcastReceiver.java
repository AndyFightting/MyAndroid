package com.suguiming.myandroid.tool;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.suguiming.myandroid.login.LoginActivity;

/**
 * Created by suguiming on 15/11/22.
 */
public class MainBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent){
        switch (intent.getAction()){
            //网络变化监听
            case ConnectivityManager.CONNECTIVITY_ACTION:
                ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isAvailable()){
                    if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                        //WiFi网络

                    }else if(networkInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
                        //有线网络

                    }else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                        //3g网络

                    }
                }else {
                    MyTool.shotToast(context,"网络不可用");
                }
                break;
            //强制退出
            case Task.BROADCAST_LOGIN_OUT:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setTitle("提示:");
                builder.setMessage("该账号在其他地方登陆，您已被强制下线！");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityManager.finishAllActivity();
                        //进入登陆页 --
                        Intent loginIntent = new Intent(context, LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(loginIntent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //----下面做本地数据清理工作----
                MyTool.shotToast(context,"本地数据已清理完毕");

          break;
        }
    }
}
