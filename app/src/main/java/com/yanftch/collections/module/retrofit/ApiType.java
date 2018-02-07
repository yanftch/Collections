package com.yanftch.collections.module.retrofit;

import okhttp3.Request;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 16:41
 * Desc :
 */

public class ApiType {
    public static Request.Builder setType(Request.Builder builder) {
        builder.addHeader("", "")
                .addHeader("os", "android")
                .addHeader("token", "")
                .addHeader("appVersion", "1.2.9-debug")
                .addHeader("security", "862772032328875")
                .addHeader("roleType", "1")
                .addHeader("identity", "");
        return builder;
    }
}
