package com.example.user.helloworld.rest;

import java.util.List;

/**
 * Created by user on 2015-11-21.
 */
public class CafeBean {

    public List<CafeBean> getListCafe() {
        return ListCafe;
    }

    public void setListCafe(List<CafeBean> listCafe) {
        ListCafe = listCafe;
    }

    private List<CafeBean> ListCafe;

    private String imgList;
    private String kcNo;
    private String kcPhone;
    private String kcHomePage;
    private String kcIntro;
    private String kcTheme;
    private String kcGPSX;
    private String kcGPSY;
    private String reCommend;
    private String kciNo;
    private String imgUrl;
    private String kciOrder;
    private String kcAddr;
    private String kcName;

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getKcNo() {
        return kcNo;
    }

    public void setKcNo(String kcNo) {
        this.kcNo = kcNo;
    }

    public String getKcPhone() {
        return kcPhone;
    }

    public void setKcPhone(String kcPhone) {
        this.kcPhone = kcPhone;
    }

    public String getKcHomePage() {
        return kcHomePage;
    }

    public void setKcHomePage(String kcHomePage) {
        this.kcHomePage = kcHomePage;
    }

    public String getKcIntro() {
        return kcIntro;
    }

    public void setKcIntro(String kcIntro) {
        this.kcIntro = kcIntro;
    }

    public String getKcTheme() {
        return kcTheme;
    }

    public void setKcTheme(String kcTheme) {
        this.kcTheme = kcTheme;
    }

    public String getKcGPSX() {
        return kcGPSX;
    }

    public void setKcGPSX(String kcGPSX) {
        this.kcGPSX = kcGPSX;
    }

    public String getKcGPSY() {
        return kcGPSY;
    }

    public void setKcGPSY(String kcGPSY) {
        this.kcGPSY = kcGPSY;
    }

    public String getReCommend() {
        return reCommend;
    }

    public void setReCommend(String reCommend) {
        this.reCommend = reCommend;
    }

    public String getKciNo() {
        return kciNo;
    }

    public void setKciNo(String kciNo) {
        this.kciNo = kciNo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKciOrder() {
        return kciOrder;
    }

    public void setKciOrder(String kciOrder) {
        this.kciOrder = kciOrder;
    }

    public String getKcAddr() {
        return kcAddr;
    }

    public void setKcAddr(String kcAddr) {
        this.kcAddr = kcAddr;
    }

    public String getKcName() {
        return kcName;
    }

    public void setKcName(String kcName) {
        this.kcName = kcName;
    }
}
