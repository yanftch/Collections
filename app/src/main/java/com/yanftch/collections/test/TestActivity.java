package com.yanftch.collections.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanftch.collections.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "dah_TestActivity";
    private XRecyclerView xrecycler_view;
    private List<String> datas;
    private MyRecyclerSwipeAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fbcListener();
    }
    private void fbcListener() {
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item"+i);
        }
        mMyAdapter = new MyRecyclerSwipeAdapter(this,datas);
        xrecycler_view = (XRecyclerView) findViewById(R.id.xrecycler_view_test);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecycler_view.setLayoutManager(layoutManager);
        xrecycler_view.setAdapter(mMyAdapter);
        //xrecycler_view.setPullRefreshEnabled(false);//设置禁止下拉刷新
        //xrecycler_view.setLoadingMoreEnabled(false);//设置禁止上拉加载
        xrecycler_view.setPullRefreshEnabled(true);//下拉刷新-可以
        xrecycler_view.setLoadingMoreEnabled(true);//上拉加载-可以
        xrecycler_view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//设置下拉刷新的样式
        listener();
    }
    private void listener() {
        /**
         * 上拉、下拉监听
         */
        xrecycler_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int size = datas.size();
                        datas.clear();
                        for (int i = 0; i < 20; i++) {
                            datas.add("newItem" + i);
                        }
                        mMyAdapter.notifyDataSetChanged();
                        xrecycler_view.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            datas.add("moreItem" + i);
                        }
                        mMyAdapter.notifyDataSetChanged();
                        xrecycler_view.loadMoreComplete();
                    }
                }, 2000);
            }
        });

    }
}
