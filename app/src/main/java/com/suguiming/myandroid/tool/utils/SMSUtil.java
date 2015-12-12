package com.suguiming.myandroid.tool.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;


/**
 * Created by suguiming on 15/12/12.
 */
public class SMSUtil {

    public static void receiveSMS(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] objects = (Object[]) bundle.get("pdus");

        SmsMessage[] messages = new SmsMessage[objects.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
        }

        String phoneNum = messages[0].getOriginatingAddress(); //号码
        String content = "";                                   //内容
        for (SmsMessage message : messages) {
            content += (message.getMessageBody());
        }

        MyTool.showToast(context, phoneNum + " - " + content);
    }

    public static void sendSMS(Context context) {
        Intent sendIntent = new Intent(Task.SEND_SMS_RESULT_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, sendIntent, 0);

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("13120846265", null, "hello world", pendingIntent, null);
    }

    public static void sendSMSResult(Context context, Intent intent, int resultCode) {
        if (resultCode == MyTool.RESULT_OK) {
            MyTool.showToast(context, "发送成功");
        } else {
            MyTool.showToast(context, "发送失败");
        }

    }

}

