package com.mskl.api.controller;

/**
 * Created by andy on 16/3/23.
 */
public class Person1 {
    private String  username;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username+"|"+sex;
    }
}
