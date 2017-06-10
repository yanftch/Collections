package com.yanftch.collections.tab_viewpagerindicater;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanftch.collections.R;

/**
 * User : yanftch
 * Date : 2017/6/6
 * Time : 18:44
 * Desc :
 */

public class TestFragment extends Fragment {
    private String name;

    public TestFragment(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_test_fragment_item, container, false);
        ((TextView) view.findViewById(R.id.tv_test_fragment)).setText(name);
        return view;
    }
}
