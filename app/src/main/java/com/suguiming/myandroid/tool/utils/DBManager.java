package com.suguiming.myandroid.tool.utils;

import android.content.Context;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

//db保存路径  /data/data/packageName/databases/ 文件夹下
public class DBManager {

    public static final String ADDRESS_NAME = "address_sheet"; //不要后缀.db！！
    public static final String WHEEL_NAME = "wheel"; //不要后缀.db！！

    public static String getDbPath(Context context, String dbName) {
        String dbDirPath = "/data/data/" + context.getPackageName() + "/databases";
        File dirFile = new File(dbDirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dbDirPath + "/" + dbName + ".db";
    }

    public static void copyDB(Context context, String dbName) {
        String dbPath = getDbPath(context, dbName);

        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getResources().openRawResource(MyTool.getResIdByName(dbName, R.raw.class));//因为这里不能有后缀
                FileOutputStream fos = new FileOutputStream(dbPath);
                byte[] buffer = new byte[400000];
                int count;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}