package com.suguiming.myandroid.tool;

/**
 * Created by suguiming on 15/11/18.
 */
public class Task {

    public static String HOST ="http://api1.koudaifit.com/fit/";  //http://test.koudaifit.com/fit/  http://api1.koudaifit.com/fit/
    public static String GOOGLE_GEO_URL = "http://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&sensor=false";

    public static final String BROADCAST_HEAD = "com.suguiming.myandroid.";
    public static final String LOGIN_OUT_ACTION = BROADCAST_HEAD+"login.out";

    public static final String RECEIVE_SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SEND_SMS_RESULT_ACTION = "SENT_SMS_ACTION";

    public static final String CAMERA_ACTION = "android.media.action.IMAGE_CAPTURE";
    public static final String CROP_ACTION = "com.android.camera.action.CROP";
    public static final String ALBUM_ACTION = "android.intent.action.GET_CONTENT";
    public static final String BACKGROUND_CYCLE_ACTION = "sgm.background.cycle.alarm";


    public static final int TAKE_PHOTO_CODE = 1;
    public static final int ALBUM_CHOOSE_CODE = 2;
    public static final int CROP_PHOTO_CODE = 3;

    public static final int CYCLE_SERVICE_CODE = 4;


}
