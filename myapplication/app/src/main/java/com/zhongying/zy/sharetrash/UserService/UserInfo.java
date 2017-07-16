package com.zhongying.zy.sharetrash.UserService;

import java.io.Serializable;

/**
 * Created by zy on 2017/6/29.
 */

public class UserInfo implements Serializable{
    private String username;
    private String password;

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

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
