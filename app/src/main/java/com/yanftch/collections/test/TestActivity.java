package com.yanftch.collections.test;

import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.yanftch.collections.R;
import com.yanftch.collections.base.BaseActivity;

public class TestActivity extends BaseActivity {
    private static final String TAG = "dah_TestActivity";

    @Override
    public int setLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void setTitle() {
            mBaseTitleBarView.setTitleText("Test");
            mBaseTitleBarView.setLeftDrawable(-1);
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onTitleLeftPressed() {
        super.onTitleLeftPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
