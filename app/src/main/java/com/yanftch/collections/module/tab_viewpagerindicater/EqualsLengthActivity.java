package com.yanftch.collections.module.tab_viewpagerindicater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yanftch.collections.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yanftch
 * Date   : 2017/6/10
 * Time   : 15:51
 * Desc   : 指示器长度与Tab的文本的长度一致
 */

public class EqualsLengthActivity extends AppCompatActivity {
    private static final String TAG = "dah_EqualsLengthActivity";
    private ViewPager viewpager;
    private ArrayList<TestFragment> fragmentList;
    private TestAdapter mAdapter;
    private ViewPagerIndicator vp_indicator;
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equals_length);
        init();
    }

    private void init() {
        for (int i = 1; i < 4; i++) {
            titles.add("title-" + i);
        }
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        vp_indicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        vp_indicator.setTabItemTitles(titles);
        mAdapter = new TestAdapter(getSupportFragmentManager());
        fragmentList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            fragmentList.add(new TestFragment("name-" + i));
        }
        viewpager.setAdapter(mAdapter);
        vp_indicator.setViewPager(viewpager, 0);
    }

    class TestAdapter extends FragmentPagerAdapter {

        public TestAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
