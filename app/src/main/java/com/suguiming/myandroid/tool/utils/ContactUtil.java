package com.suguiming.myandroid.tool.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by suguiming on 15/12/12.
 */
public class ContactUtil {

    public static void readContact(Context context){
        Cursor cursor = null;
        try{
            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor == null){
                cursor.close();
            }
        }
    }
}
