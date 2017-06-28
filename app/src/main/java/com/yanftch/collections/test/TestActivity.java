package com.yanftch.collections.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yanftch.collections.R;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "dah_TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fbcListener();
    }

    private void fbcListener() {
    }
}
