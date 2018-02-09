package com.yanftch.applibrary.net;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Author : yanftch
 * Date : 2018/2/9
 * Time : 11:03
 * Desc :
 */

public interface HttpService {
    @POST("member/app/getHome")
    Observable<BaseResponse<MyTestBean>> getFengHome();
//    Observable<MyTestBean.ContentBean> getFengHome();
//    phone=18310257489&secretPasswd=6b15bacf132e7f28054b78874799f9b1
    @POST("member/login/login")
    Observable<BaseResponse> getLoginApi(@Body LoginBody body);
//    Observable<BaseResponse> getLoginApi(@Query("phone")String phone, @Query("secretPasswd") String secretPasswd);

}
