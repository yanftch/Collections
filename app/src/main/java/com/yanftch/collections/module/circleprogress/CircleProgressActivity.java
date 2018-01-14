package com.yanftch.collections.module.circleprogress;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.iven.widget.view.circleboundview.CircleProgressView;
import com.yanftch.collections.R;

public class CircleProgressActivity extends AppCompatActivity {
    private static final String TAG = "dah_CircleProgressActivity";
    private CircleProgressView circle_view;
    private int p = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        circle_view = (CircleProgressView) findViewById(R.id.circle_view);
        countDownTimer = new CountDownTimer(100000000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
//                Log.e("pppppp", "onTick: "+p);
                circle_view.setProgress(p);
                p += 1;
                int currentPercent = circle_view.getCurrentPercent();
                Log.e("pppppp", "onTick: " + currentPercent);
                if (100 == currentPercent) {
                    Toast.makeText(CircleProgressActivity.this, "绘制完毕", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
