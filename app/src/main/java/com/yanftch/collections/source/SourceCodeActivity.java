package com.yanftch.collections.source;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yanftch.collections.R;
import com.yanftch.collections.base.BaseActivity;

public class SourceCodeActivity extends BaseActivity {
    private static final String TAG = "dah_SourceCodeActivity";
    private RecyclerView mRecyclerView;

    @Override
    public int setLayout() {
        return R.layout.activity_source_code;
    }

    @Override
    public void setTitle() {
        mBaseTitleBarView.setTitleText("标题居中");
        mBaseTitleBarView.setRightTitleText("编辑");
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onTitleRightTextPressed() {
        super.onTitleRightTextPressed();
        Toast.makeText(this, "点击了编辑按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTitleLeftPressed() {
        super.onTitleLeftPressed();
        onBackPressed();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.sourceRecyclerView);
    }
}
