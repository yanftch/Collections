package com.iven.widget.view.circleboundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.iven.widget.R;

/**
 * User : yanftch
 * Date : 2017/7/20
 * Time : 08:31
 * Desc : 圆形进度View
 */

public class CircleProgressView extends View {
    //画笔
    private Paint mPaint;
    //圆环的颜色
    private int roundColor;
    //圆环的宽度
    private int roundWidth;
    //圆环进度条的宽度
    private int roundProgressWidth;
    private int roundProgressColor;
    //中间文本的字体大小
    private float textSize;
    //中间文本的字体颜色
    private int textColor;
    //最大进度值
    private int maxProgress;
    //当前进度值
    private int progress;
    //进度条开始点
    private int progressStartPoint;
    //宽高&中心点
    private int width, height, center;
    //是否显示中间进度
    private boolean isShowText;
    private int radius;//半径
    private int style;
    private final int STOCK = 0;//只绘制边框
    private final int FILL = 1;//填充中间
    private int percent;
    private int bgColor;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆环
        center = width / 2;
        radius = center - roundWidth / 2;
        canvas.drawCircle(center, center, radius, getBoundPaint());
        //绘制背景色
        Paint boundPaint = getBoundPaint();
        boundPaint.setColor(bgColor);
        boundPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center, center, center - roundWidth, boundPaint);
        //绘制中间的进度值
        percent = (int) (((float) progress / (float) maxProgress) * 100);//当前进度值
        float textWidth = mPaint.measureText(progress + "%");//文本宽度
        int tx = (int) (center);
        int ty = (int) (center + textSize / 2);
        if (isShowText) {
            canvas.drawText(percent + "%", tx, ty, getTextPaint());
        }
        //绘制进度环
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        int cp = (int) (360 * ((float) percent / (float) 100));
        switch (style) {
            case STOCK:
                Paint roundProgressPaint = getRoundProgressPaint();
                roundProgressPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF, -90, cp, false, roundProgressPaint);
                break;
            case FILL:
                Paint roundProgressPaint2 = getRoundProgressPaint();
                roundProgressPaint2.setStyle(Paint.Style.FILL);
                canvas.drawArc(rectF, -90, cp, true, roundProgressPaint2);
                break;
        }
    }

    /**
     * 圆环进度值画笔设置
     */
    private Paint getRoundProgressPaint() {
        mPaint.setStrokeWidth(roundProgressWidth);
        mPaint.setColor(roundProgressColor);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

    /**
     * 设置中间文字画笔
     */
    private Paint getTextPaint() {
        mPaint.setStrokeWidth(0);
        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(textSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.DEFAULT);//设置字体样式
        return mPaint;
    }

    /**
     * 圆环画笔设置
     */
    private Paint getBoundPaint() {
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setColor(roundColor);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(roundColor);
        mPaint.setStrokeWidth(roundWidth);
        // TODO: 2017/7/20  属性获取
        style = STOCK;
        roundColor = Color.RED;
        bgColor = Color.parseColor("#cccccc");
        roundProgressColor = Color.GREEN;
        roundWidth = roundProgressWidth = 10;
        textColor = Color.BLACK;
        textSize = 20;
        maxProgress = 360;
        isShowText = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressView);
        if (null != mTypedArray) {
            //获取自定义属性和默认值
            roundColor = mTypedArray.getColor(R.styleable.RoundProgressView_roundColor, Color.RED);
            roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressView_roundProgressColor, Color.GREEN);
            textColor = mTypedArray.getColor(R.styleable.RoundProgressView_textColor, Color.BLACK);
            textSize = mTypedArray.getDimension(R.styleable.RoundProgressView_textSize, 20);
            roundWidth = (int) mTypedArray.getDimension(R.styleable.RoundProgressView_roundWidth, 10);
            maxProgress = mTypedArray.getInteger(R.styleable.RoundProgressView_maxProgress, 360);
            isShowText = mTypedArray.getBoolean(R.styleable.RoundProgressView_isShowText, true);
            bgColor = mTypedArray.getColor(R.styleable.RoundProgressView_bgColor, Color.parseColor("#cccccc"));
            mTypedArray.recycle();
        } else {

        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public int getCurrentPercent() {
        return percent;
    }
}
