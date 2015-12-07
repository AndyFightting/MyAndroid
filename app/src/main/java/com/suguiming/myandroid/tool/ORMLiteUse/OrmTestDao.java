package com.suguiming.myandroid.tool.ORMLiteUse;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by suguiming on 15/12/6.
 */
public class OrmTestDao {

    private OrmSqliteHelper helper;
    private Dao<OrmTestBean, Integer> dao;

    public OrmTestDao(Context context) {
        try {
            helper = OrmSqliteHelper.getHelper(context);
            dao = helper.getDao(OrmTestBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(OrmTestBean bean) {
        try {
            dao.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(OrmTestBean bean) {
        try {
            dao.update(bean);
            //----- 发个通知 告诉页面数据已经更新了---

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrmTestBean> findByName(String name) {
        try {
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq("name", name);
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrmTestBean> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeAll() {
        try {
            DeleteBuilder deleteBuilder = dao.deleteBuilder();
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
