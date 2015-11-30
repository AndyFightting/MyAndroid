package com.suguiming.myandroid.tool.sqliteUse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suguiming on 15/11/28.
 */
public class DogDao {
    private static final String TABLE_NAME = "dog";

    public static void add(Context context,Dog dog){
        SQLiteDatabase db =  MyDatabaseHelper.getHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",dog.getName());
        values.put("age",dog.getAge());
        values.put("weight",dog.getWeight());
        db.insert(TABLE_NAME, null, values);
    }

    public static void update(Context context,Dog dog){
        SQLiteDatabase db =  MyDatabaseHelper.getHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",dog.getName());
        values.put("age",dog.getAge());
        values.put("weight",dog.getWeight());
        db.update(TABLE_NAME, values, null, null);
    }

    public static void deleteAll(Context context){
        SQLiteDatabase db =  MyDatabaseHelper.getHelper(context).getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public static List<Dog> getAll(Context context){
        SQLiteDatabase db =  MyDatabaseHelper.getHelper(context).getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<Dog> dogList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Dog dog = new Dog();
                dog.setName(cursor.getString(cursor.getColumnIndex("name")));
                dog.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                dog.setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                dogList.add(dog);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dogList;
    }

    //事务，要么都成功，要么都失败。
    public static void transactionTest(Context context){
        SQLiteDatabase db =  MyDatabaseHelper.getHelper(context).getWritableDatabase();
        db.beginTransaction();
        try {
            //数据处理....

            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}
