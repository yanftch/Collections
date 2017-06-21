package com.iven.widget.view.countdown_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * User : yanftch
 * Date : 2017/6/20
 * Time : 16:28
 * Desc :
 */

public class TestView extends View {
    private static final String TAG = "dah_TestView";
    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.e(TAG, "onDraw: width=" + width + "    height=" + height);
        int x = (width - height / 2) / 2;
        int y = height / 4;
        RectF rectF = new RectF(x, y, width - x, height - y);
        canvas.drawRect(rectF,mPaint);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStyle(Paint.Style.STROKE);

    }
}
