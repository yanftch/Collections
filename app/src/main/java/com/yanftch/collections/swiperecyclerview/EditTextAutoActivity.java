package com.yanftch.collections.swiperecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yanftch.collections.R;
import com.yanftch.collections.base.BaseActivity;

public class EditTextAutoActivity extends BaseActivity {
    private Button btn_auto_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view);
        btn_auto_1 = (Button) findViewById(R.id.btn_auto_1);
    }


    @Override
    public View[] filterViewByIds() {
        View[] views = {};
        return views;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_auto_1, R.id.et_auto_2, R.id.et_auto_3};
        return ids;
    }
}
