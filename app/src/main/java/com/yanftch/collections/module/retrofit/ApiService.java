package com.yanftch.collections.module.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 14:49
 * Desc :
 */

public interface ApiService {
//    http://dat.dahuobaoxian.com/member/app/appAutoUpdate
//    http://dat.dahuobaoxian.com/member/app/getActivity
    @POST("getActivity")
    Call<TestBean> getActivity();

}
