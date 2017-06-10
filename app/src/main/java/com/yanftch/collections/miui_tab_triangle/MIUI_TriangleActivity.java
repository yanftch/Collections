package com.yanftch.collections.miui_tab_triangle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yanftch.collections.R;

import java.util.ArrayList;
import java.util.List;

public class MIUI_TriangleActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private ArrayList<TestFragment> fragmentList;
    private TestAdapter mAdapter;
    private TriangleViewPagerIndicator vp_indicator;
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miui__triangle);
        init();
    }

    private void init() {
        for (int i = 1; i < 7; i++) {
            titles.add("title" + i);
        }
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        vp_indicator = (TriangleViewPagerIndicator) findViewById(R.id.vp_indicator);
        vp_indicator.setTabItemTitles(titles);
        mAdapter = new TestAdapter(getSupportFragmentManager());
        fragmentList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            fragmentList.add(new TestFragment("name" + i));
        }
        viewpager.setAdapter(mAdapter);
        vp_indicator.setViewPager(viewpager,0);
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
