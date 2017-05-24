package com.yanftch.collections.swipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.yanftch.collections.R;
import com.yanftch.collections.common.Bean;
import com.yanftch.collections.common.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class SwipeMenuActivity extends AppCompatActivity {

    private ListView listview_swip;
    private MySwipeMenuAdapter adapter;
    private List<Bean.T1348648517839Bean> datas;
    private static final String TAG = "dah_SwipeMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu);
        init();
    }

    private void init() {
        listview_swip = (ListView) findViewById(R.id.listview_swip);
        datas = new ArrayList<>();
        datas.addAll(CommonUtils.getDataList());
        adapter = new MySwipeMenuAdapter(this,datas);
        listview_swip.setAdapter(adapter);
    }
}
