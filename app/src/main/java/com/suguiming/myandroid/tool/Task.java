package com.suguiming.myandroid.tool;

/**
 * Created by suguiming on 15/11/18.
 */
public class Task {



    public static final String BROADCAST_HEAD = "com.suguiming.myandroid.";
    public static final String LOGIN_OUT_ACTION = BROADCAST_HEAD+"login.out";

    public static final String RECEIVE_SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SEND_SMS_RESULT_ACTION = "SENT_SMS_ACTION";

    public static final String CAMERA_ACTION = "android.media.action.IMAGE_CAPTURE";
    public static final String CROP_ACTION = "com.android.camera.action.CROP";
    public static final String ALBUM_ACTION = "android.intent.action.GET_CONTENT";


    public static final int TAKE_PHOTO_CODE = 1;
    public static final int ALBUM_CHOOSE_CODE = 2;
    public static final int CROP_PHOTO_CODE = 3;


}
