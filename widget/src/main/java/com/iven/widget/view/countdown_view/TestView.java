package com.iven.widget.view.countdown_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * User : yanftch
 * Date : 2017/6/20
 * Time : 16:28
 * Desc :
 */

public class TestView extends TextView {
    private static final String TAG = "dah_TestView";
    private Paint mBgPaint;
    private Paint mCirclePaint;
    private float progress;
    private CountDownListener listener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mBgPaint);
        canvas.drawArc(0, 0, getMeasuredWidth(), getMeasuredHeight(), 0, 360, false, mBgPaint);//背景-_-
        canvas.drawArc(5, 5, getMeasuredWidth() - 5, getMeasuredHeight() - 5, -10, progress, false, mCirclePaint);//内边框
        super.onDraw(canvas);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void start() {
        if (null != listener){
            listener.onStartCount();
        }
        CountDownTimer countDownTimer = new CountDownTimer(3600, 36) {
            @Override
            public void onTick(long millisUntilFinished) {
                progress = ((3600 - millisUntilFinished) / 3600f) * 360;
                Log.d(TAG, "progress:" + progress);
                invalidate();
            }

            @Override
            public void onFinish() {
                progress = 360;
                invalidate();
                if (null != listener){
                    listener.onFinishCount();
                }
            }
        }.start();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(Color.parseColor("#cccccc"));
        mBgPaint.setStyle(Paint.Style.FILL);
        //----
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#FF0000"));
        mCirclePaint.setStrokeWidth(10);
        mCirclePaint.setStyle(Paint.Style.STROKE);
    }
    public void setCountDownListener(CountDownListener listener) {
        this.listener = listener;
    }
    public interface CountDownListener {

        void onStartCount();

        void onFinishCount();
    }
}
