package com.yanftch.collections;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yanftch.collections.circlemenu.CircleMenuLayoutActivity;
import com.yanftch.collections.convenientbanner.ConvenientBannerActivity;
import com.yanftch.collections.edittext.InputTypeLimitActivity;
import com.yanftch.collections.swipe.SwipeMenuActivity;
import com.yanftch.collections.swiperecyclerview.SwipeRecyclerViewActivity;
import com.yanftch.collections.test.TestActivity;
import com.yanftch.collections.xrecyclerview.XRecyclerViewActivity;

/**
 * Author : yanftch
 * Date   : 2017/5/23
 * Time   : 08:53
 * Desc   : 开发库集成、收集
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dah_MainActivity";
    int[] array = {12, 1, 3, 34, 121, 565};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFunction();
    }

    private void testFunction() {
    }

    public void btnTurnAct(View view) {
        switch (view.getId()) {
            case R.id.btn_01://ListView侧滑菜单效果
                startActivity(SwipeMenuActivity.class);
                break;
            case R.id.btn_02://集成的Recyclerview
                startActivity(XRecyclerViewActivity.class);
                break;
            case R.id.btn_03://
                startActivity(com.yan.imgpicker.camera.MainActivity.class);
                break;
            case R.id.btn_04://
                startActivity(InputTypeLimitActivity.class);
                break;
            case R.id.btn_05:
                startActivity(CircleMenuLayoutActivity.class);
                break;
            case R.id.btn_06:
                startActivity(ConvenientBannerActivity.class);
                break;
            case R.id.btn_07:
                startActivity(TestActivity.class);
                break;
            case R.id.btn_08:
                startActivity(SwipeRecyclerViewActivity.class);
                break;
            default:
                break;

        }
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
