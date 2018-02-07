package com.yanftch.collections.module.retrofit;

import retrofit2.Retrofit;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 15:32
 * Desc :
 */

public abstract class RetrofitFactory<T> {
    public static <T> T get(String baseUrl, Class<T> clazz) {
        Retrofit retrofit = RetrofitService.createRetrofit(baseUrl);
        T t = retrofit.create(clazz);
        return t;
    }
}
