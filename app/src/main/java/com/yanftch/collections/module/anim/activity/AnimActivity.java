package com.yanftch.collections.module.anim.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yanftch.collections.R;
import com.yanftch.collections.base.BaseActivity;
import com.yanftch.collections.test.TestActivity;
/**
 *
 * Author : yanftch
 * Date   : 2018/1/16
 * Time   : 15:35
 * Desc   : https://juejin.im/post/5a5d89596fb9a01caa2087f6
 */

public class AnimActivity extends BaseActivity {
    private static final String TAG = "dah_AnimActivity";
    private Button btnBefore5;
    private Button btnAfter5;
    private ImageView ivAnchor;


    @Override
    public int setLayout() {
        return R.layout.activity_anim;
    }

    @Override
    public void setTitle() {
        mBaseTitleBarView.setTitleText("动画");
        mBaseTitleBarView.setLeftDrawable(-1);
    }

    @Override
    public void initWidget() {
        btnBefore5 = (Button) findViewById(R.id.btnBefore5);
        btnAfter5 = (Button) findViewById(R.id.btnAfter5);
        btnBefore5.setOnClickListener(this);
        btnAfter5.setOnClickListener(this);
        ivAnchor = findView(R.id.ivAnchor);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btnBefore5:
                /**
                 * 5.0之前，使用overridePendingTransition()来实现跳转动画的
                 *
                 */
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.btnAfter5:
                /**
                 * 5.0之后，使用共享动画
                 */
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(ivAnchor, ivAnchor.getWidth()/2, ivAnchor.getHeight()/2, 0, 0);
                ActivityCompat.startActivity(this, new Intent(this, TestActivity.class), compat.toBundle());
                break;
            default:
                break;
        }
    }

    @Override
    public void onTitleLeftPressed() {
        super.onTitleLeftPressed();
        onBackPressed();
    }
}
