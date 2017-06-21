package com.yanftch.collections.countdown_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.iven.widget.view.countdown_view.CountDownView;
import com.yanftch.collections.R;

/**
 * Author : yanftch
 * Date   : 2017/6/20
 * Time   : 15:11
 * Desc   : 广告倒计时圆形控件
 */

public class CountDownViewActivity extends AppCompatActivity {

    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_view);
        countDownView = (CountDownView) findViewById(R.id.countDownView);
        countDownView.start();
        countDownView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
                Toast.makeText(CountDownViewActivity.this, "开始倒计时了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinishCount() {
                Toast.makeText(CountDownViewActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
