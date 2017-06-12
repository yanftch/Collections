package com.yanftch.collections.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iven.widget.jcodecraeer.xrecyclerview.ProgressStyle;
import com.iven.widget.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanftch.collections.R;

import java.util.ArrayList;

/**
 * 集成的Recyclerview实践
 * Author : yanftch
 * Date   : 2017/5/23
 * Time   : 14:26
 * <p>
 * Desc   : https://github.com/jianghejie/XRecyclerView
 */

public class XRecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "dah_XRecyclerViewActivity";

    private XRecyclerView xrecycler_view;
    private ArrayList<String> datas;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
        fbcListener();
    }

    private void fbcListener() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item" + i);
        }
        xrecycler_view = (XRecyclerView) findViewById(R.id.xrecycler_view);
        mMyAdapter = new MyAdapter(datas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecycler_view.setLayoutManager(layoutManager);
        xrecycler_view.setAdapter(mMyAdapter);
        //xrecycler_view.setPullRefreshEnabled(false);//设置禁止下拉刷新
        //xrecycler_view.setLoadingMoreEnabled(false);//设置禁止上拉加载
        xrecycler_view.setPullRefreshEnabled(true);//下拉刷新-可以
        xrecycler_view.setLoadingMoreEnabled(true);//上拉加载-可以
        xrecycler_view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//设置下拉刷新的样式
        //添加Head
        View view = LayoutInflater.from(this).inflate(R.layout.layout_test_header, xrecycler_view, false);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_test_header, (ViewGroup) findViewById(android.R.id.content), false);
        ((TextView) view2.findViewById(R.id.tv_head_test)).setText("我是Head---2");
        xrecycler_view.addHeaderView(view);
        xrecycler_view.addHeaderView(view2);
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
        /**
         * ItemClickListener
         */
        mMyAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onClickListener(int position) {
                String s = datas.get(position);
                Toast.makeText(XRecyclerViewActivity.this, "position="+position+"          "+s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
