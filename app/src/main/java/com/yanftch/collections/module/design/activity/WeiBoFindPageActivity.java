package com.yanftch.collections.module.design.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.yanftch.collections.R;
import com.yanftch.collections.module.design.adapter.MyViewPagerAdapter;

/**
 * Author : yanftch
 * Date   : 2018/1/8
 * Time   : 16:21
 * Desc   : 模仿微博发现页面
 */

public class WeiBoFindPageActivity extends AppCompatActivity {
    private static final String TAG = "dah_WeiBoFindPageActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mMyViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wei_bo_find_page);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mMyViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
