package com.example.user.helloworld;

import java.io.Serializable;

/**
 * Created by USER on 2015-10-31.
 */
public class ListBean implements Serializable {

    private int imgIcon;
    private String title;
    private String downInfo;
    private String price;

    //디폴트 생성자
    public ListBean() {

    }

    //생성자
    public ListBean(int imgIcon, String title, String downInfo, String price) {
        this.imgIcon = imgIcon;
        this.title = title;
        this.downInfo = downInfo;
        this.price = price;
    }


    public int getImgIcon() {
        return imgIcon;
    }
    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDownInfo() {
        return downInfo;
    }
    public void setDownInfo(String downInfo) {
        this.downInfo = downInfo;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
