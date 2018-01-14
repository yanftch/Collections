package com.yanftch.collections.module.shape;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yanftch.collections.R;

/**
 * Author : yanftch
 * Date   : 2017/6/28
 * Time   : 09:07
 * Desc   : 自带shape的TextView
 */

public class ShapeTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_text_view);
        findViewById(R.id.tv_sp_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShapeTextViewActivity.this, "点点点1", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_sp_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShapeTextViewActivity.this, "点点点2", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_sp_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShapeTextViewActivity.this, "点点点3", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
