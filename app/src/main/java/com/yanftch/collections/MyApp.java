package com.yanftch.collections;

import android.app.Application;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 15:01
 * Desc :
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.application = this;
        GlobalContext.context = getApplicationContext();
    }
}
