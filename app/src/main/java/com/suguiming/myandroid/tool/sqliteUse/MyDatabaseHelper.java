package com.suguiming.myandroid.tool.sqliteUse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suguiming on 15/11/28.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static MyDatabaseHelper helper;
    private static final String DB_NAME = "myAndroid.db";

    public MyDatabaseHelper(Context context){
        super(context, DB_NAME, null, 2);//2 数据库version,修改数据库用
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_dog);
        db.execSQL(create_cat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新表才用,这里假设在 版本2 中要添加 cat 表,注意：switch 不要 break !!
        switch (newVersion){
            case 2:
                db.execSQL(create_cat);
            default:
        }
    }
    //获取 helper 单例
    public static synchronized MyDatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (helper == null) {
            synchronized (MyDatabaseHelper.class) {
                if (helper == null)
                    helper = new MyDatabaseHelper(context);
            }
        }
        return helper;
    }

    //-------------sql----------------------------
        public static final String create_dog = "create table dog ("
                +"id integer primary key autoincrement,"
                +"name text,"
                +"age integer,"
                +"weight real)";

        public static final String create_cat = "create table cat ("
                +"id integer primary key autoincrement,"
                +"name text,"
                +"age integer,"
                +"weight real)";
}
