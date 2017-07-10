package com.zhongying.zy.sharetrash.entity;

/**
 * 项目名称：EditSearch
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/4/25 17:30
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class User {
    private String name;
    private String details;
    public User(String name,String details) {
        this.name = name;
        this.details=details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
