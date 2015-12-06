package com.suguiming.myandroid.tool.ORMLiteUse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suguiming on 15/12/6.
 */
public class OrmSqliteHelper extends OrmLiteSqliteOpenHelper {

   public static final String DB_NAME = "orm_test.db";
   public Map<String,Dao> daoMap = new HashMap<>();
   public Context mContext;
   public static OrmSqliteHelper helper;


   public OrmSqliteHelper(Context context){
       super(context,DB_NAME,null,1);// 1是数据库版本号
       mContext = context;
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // 所有的 Bean 这里都要一份，创建表
            TableUtils.createTable(connectionSource, OrmTestBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            switch (newVersion){
                // 在不同版本中数据库的变化， 不要 break;
                case 2:
                    Dao testBeanDao = getHelper(mContext).getDao(OrmTestBean.class);
                    testBeanDao.executeRaw("ALTER TABLE 'table_orm_test_bean' ADD COLUMN weight float");
                case 3:

                case 4:

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取 helper 单例
    public static synchronized OrmSqliteHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (helper == null) {
            synchronized (OrmSqliteHelper.class) {
                if (helper == null)
                    helper = new OrmSqliteHelper(context);
            }
        }
        return helper;
    }

    //获取 对应Bean 的 Dao
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daoMap.containsKey(className)) {
            dao = daoMap.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daoMap.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        daoMap.clear();
    }
}
