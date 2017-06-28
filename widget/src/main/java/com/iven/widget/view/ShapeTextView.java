package com.iven.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.iven.widget.R;

/**
 * Author : yanftch
 * Date   : 2017/6/28
 * Time   : 09:05
 * Desc   : 自带shape的TextView
 */
public class ShapeTextView extends TextView {
    private static final String TAG = "dah_ShapeTextView";
    /**
     * 默认
     */
    private static final int BACKGROUND_COLOR = Color.parseColor("#FFFFFF");//默认白色背景色
    private static final int BORDER_COLOR = Color.parseColor("#00000000");//默认边框颜色为透明的(xml不设置的话)
    private static final int BORDER_WIDTH = 1;
    private static final int CORNERRADIUS = 10;

    /**
     * 背景颜色
     */
    private int bgColor;
    /**
     * 外边框颜色
     */
    private int borderColor;
    /**
     * 外边框宽度
     */
    private float borderWidth = 5;//边框的宽度
    /**
     * 圆角半径
     */
    private float cornerRadius = 30;//弧度

    /**
     * 外边框画笔
     */
    private Paint borderPaint;
    /**
     * 背景画笔
     */
    private Paint bgPaint;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
        this.setBackground(null);
        RectF rectF = new RectF(borderWidth / 2, borderWidth / 2, getMeasuredWidth() - borderWidth / 2, getMeasuredHeight() - borderWidth / 2);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, borderPaint);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, bgPaint);
        super.onDraw(canvas);
    }

    private void init() {
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.FILL_AND_STROKE);//描边
        borderPaint.setStrokeWidth(borderWidth);

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);//填充
    }

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, @Nullable AttributeSet attrs) {
        // 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public ShapeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 背景色
         * 边框色
         * 边框宽度
         * 圆角半径
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        bgColor = ta.getColor(R.styleable.ShapeTextView_backgroundColor, BACKGROUND_COLOR);
        borderColor = ta.getColor(R.styleable.ShapeTextView_borderColor, BORDER_COLOR);
        borderWidth = ta.getDimension(R.styleable.ShapeTextView_borderWidth, BORDER_WIDTH);
        cornerRadius = ta.getDimension(R.styleable.ShapeTextView_cornerRadius, CORNERRADIUS);
        ta.recycle();
        init();
    }
}
