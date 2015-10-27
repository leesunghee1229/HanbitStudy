package com.example.user.helloworld;

import java.io.Serializable;

/**
 * Created by user on 2015-10-24.
 */
public class ListDataBean implements Serializable {
    private int imgSrc;
    private String title;
    private String contents;

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
