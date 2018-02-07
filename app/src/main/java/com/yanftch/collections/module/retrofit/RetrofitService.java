package com.yanftch.collections.module.retrofit;

import android.os.Environment;

import com.yanftch.collections.GlobalContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 14:57
 * Desc :
 */

public class RetrofitService {
    public static Retrofit createRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient());
        return builder.build();
    }

    private static OkHttpClient getOkHttpClient() {
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //缓存路径
            File sdCardPath;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                sdCardPath = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "cacheData");
            } else {
                sdCardPath = new File(GlobalContext.context.getCacheDir(), "cacheData");
            }
            //缓存空间
            Cache cache = new Cache(sdCardPath, 10 * 1024 * 1024);

//        builder.cache(cache)
            //添加超时时间
            builder.connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new InterceptroManager.AuthInterceptor())
                    .addInterceptor(new InterceptroManager.HeaderInterceptor())
                    .addInterceptor(new InterceptroManager.ResponseInterceptor());
            return builder.build();
        }
    }
}
