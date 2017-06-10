package com.yanftch.collections.tab_viewpagerindicater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yanftch.collections.R;

/**
 * Author : yanftch
 * Date   : 2017/6/10
 * Time   : 15:54
 * Desc   : 各种各样的ViewPagerIndicator
 */

public class AllKindsOfViewPagerIndicaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kinds_of_view_pager_indicater);
    }

    public void btnGotoVPIndicator(View view) {
        switch (view.getId()) {
            case R.id.btn_ind_01:
                startActivity(new Intent(this, MIUI_TriangleActivity.class));
                break;
            case R.id.btn_ind_02:
                startActivity(new Intent(this, EqualsLengthActivity.class));
                break;
        }
    }
}
