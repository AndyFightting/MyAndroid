package com.suguiming.myandroid.bean;

/**
 * Created by suguiming on 15/11/18.
 */
public class User {

    private String name;
    private int headImgId;

    public User(String name,int headImgId){
        this.name = name;
        this.headImgId = headImgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(int headImgId) {
        this.headImgId = headImgId;
    }
}
