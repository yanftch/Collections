package com.yanftch.collections.module.retrofit;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 15:13
 * Desc :
 */

public class TestBean extends BaseBean {

    private List<ActivityListBean> activityList = new ArrayList<>();

    public List<ActivityListBean> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityListBean> activityList) {
        this.activityList = activityList;
    }

    public static class ActivityListBean {
        /**
         * adsId : 92ebd08b3a0c4225a6e4f960898ade6e
         * adsTitle : “畅飞保”高额航空意外险
         * adsContent :
         * adsImageUrl : https://s3.cn-north-1.amazonaws.com.cn/uploadfiles/activityFile/b52294ae2afd45f391b6ecc8ed07615d.png
         * adsUrl : http://dat.dahuobaoxian.com/custserv/activity/item/carflyinsure/carflyinsure.html
         * adsStartUp : 1
         * adsShowType : null
         * adsState : 1
         * version : null
         * createTime : 1517811243000
         * startTime : 1517500800000
         * endTime : 1517932800000
         * adsType : 1
         * productNo :
         * sort : 30
         * activityId : null
         */

        private String adsId;
        private String adsTitle;
        private String adsContent;
        private String adsImageUrl;
        private String adsUrl;
        private String adsStartUp;
        private Object adsShowType;
        private String adsState;
        private Object version;
        private long createTime;
        private long startTime;
        private long endTime;
        private String adsType;
        private String productNo;
        private int sort;
        private Object activityId;

        public String getAdsId() {
            return adsId;
        }

        public void setAdsId(String adsId) {
            this.adsId = adsId;
        }

        public String getAdsTitle() {
            return adsTitle;
        }

        public void setAdsTitle(String adsTitle) {
            this.adsTitle = adsTitle;
        }

        public String getAdsContent() {
            return adsContent;
        }

        public void setAdsContent(String adsContent) {
            this.adsContent = adsContent;
        }

        public String getAdsImageUrl() {
            return adsImageUrl;
        }

        public void setAdsImageUrl(String adsImageUrl) {
            this.adsImageUrl = adsImageUrl;
        }

        public String getAdsUrl() {
            return adsUrl;
        }

        public void setAdsUrl(String adsUrl) {
            this.adsUrl = adsUrl;
        }

        public String getAdsStartUp() {
            return adsStartUp;
        }

        public void setAdsStartUp(String adsStartUp) {
            this.adsStartUp = adsStartUp;
        }

        public Object getAdsShowType() {
            return adsShowType;
        }

        public void setAdsShowType(Object adsShowType) {
            this.adsShowType = adsShowType;
        }

        public String getAdsState() {
            return adsState;
        }

        public void setAdsState(String adsState) {
            this.adsState = adsState;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getAdsType() {
            return adsType;
        }

        public void setAdsType(String adsType) {
            this.adsType = adsType;
        }

        public String getProductNo() {
            return productNo;
        }

        public void setProductNo(String productNo) {
            this.productNo = productNo;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getActivityId() {
            return activityId;
        }

        public void setActivityId(Object activityId) {
            this.activityId = activityId;
        }
    }
}
