package com.iven.widget.countdownview;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;

import com.iven.widget.R;

public class CountdownButton extends TextView {
    private int totalTime = 10 * 1000;
    private int interval = 1000;
    private boolean isRunning = false;//是否正在倒计时

    private String text = "获取验证码";
    private MyCount myCount;

    private void init(int totalTime) {
        this.totalTime = totalTime;

    }

    public void cancle() {
        if (myCount != null) {
            myCount.cancel();
        }
    }

    public void start() {
//        new CountDownTimer(totalTime, interval - 10) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                CountdownButton.this.setEnabled(false);
//                CountdownButton.this.setTextColor(getResources().getColor(R.color.color_cccccc));
//                CountdownButton.this.setText((millisUntilFinished + 15) / 1000 + "s");
//            }
//
//            @Override
//            public void onFinish() {
//                CountdownButton.this.setText(text);
//                CountdownButton.this.setEnabled(true);
//                CountdownButton.this.setTextColor(getResources().getColor(R.color.title_bar_bg));
//            }
//        }.start();
        if (myCount == null) {
            myCount = new MyCount(totalTime, interval - 10);
        }
        myCount.start();
    }

    class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownButton.this.setEnabled(false);
            CountdownButton.this.setTextColor(getResources().getColor(R.color.color_999999));
            CountdownButton.this.setText("剩余" + (millisUntilFinished + 15) / 1000 + "s");
        }

        @Override
        public void onFinish() {
            CountdownButton.this.setText(text);
            CountdownButton.this.setEnabled(true);
            CountdownButton.this.setTextColor(getResources().getColor(R.color.color_f80d0d));
        }
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}