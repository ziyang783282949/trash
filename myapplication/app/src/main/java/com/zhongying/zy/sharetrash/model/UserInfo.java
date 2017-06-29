package com.zhongying.zy.sharetrash.model;

/**
 * Created by zy on 2017/6/29.
 */

public class UserInfo {
    private String username;
    private String userpass;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public UserInfo(String username, String userpass) {
        this.username = username;
        this.userpass = userpass;
    }
    public UserInfo(){

    }
}
