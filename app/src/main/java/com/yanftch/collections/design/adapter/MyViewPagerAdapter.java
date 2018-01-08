package com.yanftch.collections.design.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yanftch.collections.design.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : yanftch
 * Date : 2018/1/8
 * Time : 16:28
 * Desc :
 */

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "dah_MyViewPagerAdapter";
    private List<Fragment> fragments;
    public Context mContext;

    private String[] titles;

    public MyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        initFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


    private void initFragments() {
        titles = new String[]{"001", "002", "003", "004"};

        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Fragment fragment = TabFragment.newInstance();
            fragments.add(fragment);
        }
    }
}
