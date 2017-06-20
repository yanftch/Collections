package com.yanftch.collections.test;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yanftch.collections.R;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "dah_TestActivity";
    private TextView tv_01, tv_02, tv_03;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fbcListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void fbcListener() {
        tv_01 = (TextView) findViewById(R.id.tv_01);
        tv_02 = (TextView) findViewById(R.id.tv_02);
        tv_03 = (TextView) findViewById(R.id.tv_03);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#000000"));//设置背景色
        drawable.setCornerRadius(20);//设置圆角半径
        drawable.setStroke(2,Color.parseColor("#ff0000"));//外边框

        tv_01.setBackground(drawable);
//        drawable.setColor(Color.parseColor("#00ff00"));
//        tv_02.setBackground(drawable);
//        drawable.setColor(Color.parseColor("#0000ff"));
//        tv_03.setBackground(drawable);
    }
}
