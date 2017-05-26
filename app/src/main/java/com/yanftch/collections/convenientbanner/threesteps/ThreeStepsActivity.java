package com.yanftch.collections.convenientbanner.threesteps;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.yanftch.collections.R;

import java.util.ArrayList;

public class ThreeStepsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ArrayList<BannerBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_steps);
        initViews();
        init();
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
                }, datas)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件

//        convenientBanner.setManualPageable(false);//设置不能手动影响

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


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "监听到翻到第" + position + "了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}

