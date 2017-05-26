package com.yanftch.collections;


/**
 * 活动模版详情
 *
 * @author xiaolei.li
 */

public class AdsInfoModel {
    public String adsId;
    public String adsTitle;//标题
    public String adsContent;//内容
    public String adsImageUrl;//图片地址
    public String adsUrl;//链接地址
    public String adsStartUp;//启动次数(1每次2第一次)
    public String adsShowType;//用户显示类型(0未登陆，1登陆 2合伙人 3 高级合伙人 4全部用户)
    public String adsState;//是否弹屏（0不弹出，1弹出）
    public String version;//版本号
    public String createTime;//创建时间
    public String startTime;//开始时间
    public String endTime;//结束时间
    public String adsType;//活动类型 0:产品详情页  1:专题H5页  2:邀请页
    public String productNo;//商品ID
}
