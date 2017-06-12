package com.yanftch.collections.convenientbanner.threesteps;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.iven.widget.bigkoo.convenientbanner.ConvenientBanner;
import com.iven.widget.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.yanftch.collections.R;

import java.util.ArrayList;

public class ThreeStepsActivity extends AppCompatActivity {
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ArrayList<BannerBean> datas = new ArrayList<>();
    private static final String TAG = "dah_ThreeStepsActivity";
    //左右两个箭头
    private ImageView poster_left;
    private ImageView poster_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_steps);
        initViews();
        init();
        initLeftRightListener();
    }

    private void initLeftRightListener() {
        poster_left = (ImageView) findViewById(R.id.poster_left);
        poster_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() > 1) {
                    convenientBanner.leftTurn();
                }
            }
        });
        poster_right = (ImageView) findViewById(R.id.poster_right);
        poster_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() > 1) {
                    convenientBanner.rightTurn();
                }
            }
        });
    }

    private void initViews() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanners);
    }

    private void init() {
        loadTestDatas();
        convenientBanner.setCanLoop(false);//禁止无限轮播
        //本地图片例子
        convenientBanner.setPages(
                new CBViewHolderCreator<InflateViewViewHolder>() {
                    @Override
                    public InflateViewViewHolder createHolder() {
                        InflateViewViewHolder inflateViewViewHolder = new InflateViewViewHolder();

                        return inflateViewViewHolder;
                    }
                }, datas);
// TODO: 2017/5/31 是否可以手动翻页？
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: position === " + position);
                switch (position) {
                    case 0:
                        poster_left.setVisibility(View.GONE);
                        poster_right.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        poster_left.setVisibility(View.VISIBLE);
                        poster_right.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        poster_left.setVisibility(View.VISIBLE);
                        poster_right.setVisibility(View.GONE);
                        break;
                    default:
                        poster_left.setVisibility(View.VISIBLE);
                        poster_right.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*
    加入测试Views
    * */
    private void loadTestDatas() {
        //本地图片集合
        for (int i = 0; i < 3; i++) {
            BannerBean bean = new BannerBean("name" + i, i, "imgUrl" + i, "detailUrl" + i);
            datas.add(bean);
        }
    }


    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
//        convenientBanner.startTurning(2000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }


}

