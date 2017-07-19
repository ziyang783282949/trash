package com.zhongying.zy.sharetrash.UserService;

import java.io.Serializable;

/**
 * Created by zy on 2017/6/29.
 */

public class UserInfo implements Serializable{
    private String username;
    private String password;
    //1为男
    private String sex;
    private String urlusericon;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrlusericon() {
        return urlusericon;
    }

    public void setUrlusericon(String urlusericon) {
        this.urlusericon = urlusericon;
    }

    public UserInfo() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserInfo(String username, String password, String sex, String urlusericon) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.urlusericon = urlusericon;
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
