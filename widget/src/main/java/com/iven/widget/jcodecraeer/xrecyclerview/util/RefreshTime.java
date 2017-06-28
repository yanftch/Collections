package com.iven.widget.jcodecraeer.xrecyclerview.util;

import android.content.Context;
import android.content.SharedPreferences;

public class RefreshTime {
    final static String PRE_NAME = "refresh_time_r";
    final static String SET_FRESHTIME = "set_refresh_time_r";

    public static Long getRefreshTime(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        return preferences.getLong(SET_FRESHTIME, 0L);
    }

    public static void setRefreshTime(Context context, Long newPasswd) {
        SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(SET_FRESHTIME, newPasswd);
        editor.commit();
    }

}