package com.yanftch.collections.convenientbanner.threesteps;

/**
 * User : yanftch
 * Date : 2017/5/26
 * Time : 09:05
 * Desc :
 */

public class BannerBean {
    private String name;
    private int id;
    private String imgUrl;
    private String detailUrl;

    public String getName() {
        return name;
    }

    public BannerBean setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public BannerBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BannerBean setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public BannerBean setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
        return this;
    }

    public BannerBean(String name, int id, String imgUrl, String detailUrl) {
        this.name = name;
        this.id = id;
        this.imgUrl = imgUrl;
        this.detailUrl = detailUrl;
    }
}
