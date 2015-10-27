package com.example.user.helloworld;

import java.io.Serializable;

/**
 * Created by user on 2015-10-24.
 */
public class Data implements Serializable {
    private String name;
    private String id;
    private String userCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
}
