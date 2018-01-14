package com.yanftch.collections.module.tab_viewpagerindicater;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanftch.collections.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User : yanftch
 * Date : 2017/6/6
 * Time : 18:39
 * Desc :   仿MIUI三角形的TAB切换指示器
 */

public class TriangleViewPagerIndicator extends LinearLayout {
    private static final String TAG = "dah_TriangleViewPagerIndica";
    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;//三角形宽度
    private int mTriangleHeight;//三角形高度
    private int with;
    private int height;
    //宽度比例
    private static final float RADIO = 1 / 6F;
    //偏移位置
    private int mInitTranslationX;
    //移动距离
    private int mTranslationX;
    //可见Tab数量
    private int mVisiableTabCount;
    //默认可见数量
    private int mDefaultVisianleTabCount = 4;


    public TriangleViewPagerIndicator(Context context) {
        super(context, null);
    }

    public TriangleViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TriangleViewPagerIndicator);
        mVisiableTabCount = array.getInt(R.styleable.TriangleViewPagerIndicator_visible_tab_count, 3);
        if (mVisiableTabCount < 0) {
            mVisiableTabCount = mDefaultVisianleTabCount;
        }
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(3);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    public TriangleViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / mVisiableTabCount * RADIO);
//        mTriangleHeight = mTriangleWidth / 2;
        mInitTranslationX = w / mVisiableTabCount / 2 - mTriangleWidth / 2;
        init();
    }

    @Override
    protected void onFinishInflate() {
        int cCount = getChildCount();
        if (cCount == 0) return;
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.weight = 0;
            layoutParams.width = getscreenWidth() / mVisiableTabCount;
            view.setLayoutParams(layoutParams);
            setItemClickEvent();
        }
        super.onFinishInflate();
    }

    private int getscreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void init() {
        mTriangleHeight = mTriangleWidth / 2;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    //绘制儿子的内容
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        Log.e(TAG, "dispatchDraw: " + (mInitTranslationX + mTranslationX));
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     * 指示器移动
     *
     * @param position position
     * @param offset   偏移量
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mVisiableTabCount;
        mTranslationX = (int) (tabWidth * offset + position * tabWidth);
        //一屏放不下tab，容器移动
        if (position >= (mVisiableTabCount - 2) && offset > 0 && getChildCount() > mVisiableTabCount) {
            if (mVisiableTabCount != 1) {
                this.scrollTo((position - (mVisiableTabCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }

        postInvalidate();
    }

    private List<String> titles = new ArrayList<>();
    private static final int text_normal_color = 0x77FFFFFF;
    private static final int text_highlight_color = Color.parseColor("#ffffff");

    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            this.titles = titles;
            for (String title : titles) {
                addView(geterateTextView(title));
            }
            setItemClickEvent();
        }
    }

    private void restTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(text_normal_color);
            }
        }
    }

    private void highLighTextView(int pos) {
        restTextViewColor();
        View view = getChildAt(pos);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(text_highlight_color);
        }
    }

    private View geterateTextView(String title) {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getscreenWidth() / mVisiableTabCount;
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(text_normal_color);
        textView.setLayoutParams(lp);
        return textView;
    }

    private ViewPager mViewPager;

    public interface PageOnChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public PageOnChangeListener mListener;

    public void setOnPageChangeListener(PageOnChangeListener listener) {
        this.mListener = listener;
    }

    public void setViewPager(ViewPager viewpager, final int pos) {
        mViewPager = viewpager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (null != mListener) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                highLighTextView(position);
                if (null != mListener) {
                    mListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (null != mListener) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(pos);
        highLighTextView(pos);
    }

    private void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int tep = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(tep);
                }
            });
        }
    }
}
