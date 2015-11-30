package com.suguiming.myandroid.tool.sqliteUse;

/**
 * Created by suguiming on 15/11/28.
 */
public class Dog {

    private String name;
    private int age;
    private float weight;

    public Dog(String name,int age,float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Dog(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
