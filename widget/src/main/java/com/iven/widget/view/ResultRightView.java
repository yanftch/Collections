package com.iven.widget.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.iven.widget.R;


/**
 * Author : yanftch
 * Date   : 2017/8/15
 * Time   : 16:47
 * Desc   : 自动绘制对勾
 */

public class ResultRightView extends View implements ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = "dah_ResultRightView";
    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private Path mPathDst;

    /**
     * Path管理
     */
    private PathMeasure mPathMeasure;
    /**
     * 动画
     */
    private ValueAnimator mValueAnimator;
    /**
     * 当前绘制进度占总Path长度百分比
     */
    private float mPercent;
    private int width, height;


    private int mLineWidth = 5;
    private int mLineColor;
    private Paint.Join mLineStrokeJoin;//拐角样式
    private Paint.Cap mLineStrokeCap;//线头样式
    /**
     * 默认值
     */
    private final int DEFAULT_LINE_WIDTH = 5;
    private final String DEFAULT_LINE_COLOR = "#000000";
    private final int DEFAULT_LINESTROKEJOIN = 2;//默认锐角
    private final int DEFAULT_LINESTROKECAP = 2;//默认锐角

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        //设置线头样式
        mPaint.setStrokeCap(mLineStrokeCap);//圆帽
        //设置拐角样式
        mPaint.setStrokeJoin(mLineStrokeJoin);//拐点-圆角形状
        initPath();
    }

    private void initPath() {
        mPath = new Path();
        mPathDst = new Path();
        mPathMeasure = new PathMeasure();

        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(2000);
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPercent == 1) {
            mPath.reset();
            mPathDst.reset();
            mValueAnimator.start();
        }
        drawRight(canvas);
    }

    //画对勾
    private void drawRight(Canvas canvas) {
        //起点
        mPath.moveTo(width / 4, height / 2);
        //拐点
        mPath.lineTo(width / 2, 3 * (height / 4));
        //终点
        mPath.lineTo(3 * (width / 4), height / 4);

        mPathMeasure.nextContour();
        mPathMeasure.setPath(mPath, false);
        mPathMeasure.getSegment(0, mPercent * mPathMeasure.getLength(), mPathDst, true);
        canvas.drawPath(mPathDst, mPaint);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mPercent = (float) animation.getAnimatedValue();
        invalidate();
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------------
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(dp2px(50), dp2px(50));
    }

    public ResultRightView(Context context) {
        this(context, null);
    }

    public ResultRightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ResultRightView);
        mLineColor = a.getInt(R.styleable.ResultRightView_lineColor, Color.parseColor(DEFAULT_LINE_COLOR));
        mLineWidth = a.getDimensionPixelSize(R.styleable.ResultRightView_lineWidth, DEFAULT_LINE_WIDTH);
        int aInt = a.getInt(R.styleable.ResultRightView_lineStrokeJoin, DEFAULT_LINESTROKEJOIN);//默认锐角
        if (aInt == 0) {
            mLineStrokeJoin = Paint.Join.ROUND;
        } else if (aInt == 1) {
            mLineStrokeJoin = Paint.Join.BEVEL;
        } else {
            mLineStrokeJoin = Paint.Join.MITER;
        }
        int cap = a.getInt(R.styleable.ResultRightView_lineStrokeCap, DEFAULT_LINESTROKECAP);
        if (cap == 0) {
            mLineStrokeCap = Paint.Cap.BUTT;
        } else if (cap == 1) {
            mLineStrokeCap = Paint.Cap.ROUND;
        } else {
            mLineStrokeCap = Paint.Cap.SQUARE;
        }
        a.recycle();
        init();
    }


    private int dp2px(int dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (scale * dp + 0.5f);
    }
}