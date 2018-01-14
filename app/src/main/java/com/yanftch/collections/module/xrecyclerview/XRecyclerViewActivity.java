package com.yanftch.collections.module.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
    private GridItemDecoration mGridItemDecoration;
    private MyItemDecoration myItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
        mGridItemDecoration = new GridItemDecoration(XRecyclerViewActivity.this,true);
        myItemDecoration = new MyItemDecoration();

        fbcListener();
    }

    private void fbcListener() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item" + i);
        }
        xrecycler_view = (XRecyclerView) findViewById(R.id.xrecycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecycler_view.setLayoutManager(layoutManager);
        mMyAdapter = new MyAdapter(datas);
        xrecycler_view.setAdapter(mMyAdapter);
        //xrecycler_view.setPullRefreshEnabled(false);//设置禁止下拉刷新
        //xrecycler_view.setLoadingMoreEnabled(false);//设置禁止上拉加载
        xrecycler_view.setPullRefreshEnabled(true);//下拉刷新-可以
        xrecycler_view.setLoadingMoreEnabled(true);//上拉加载-可以
        xrecycler_view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//设置下拉刷新的样式
        //添加分割线
        xrecycler_view.addItemDecoration(myItemDecoration);
        //添加Head
        View view = LayoutInflater.from(this).inflate(R.layout.layout_test_header, xrecycler_view, false);
        xrecycler_view.addHeaderView(view);
        listener();
        final TextView textView = (TextView) view.findViewById(R.id.tv_head_test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.LayoutManager layoutManager1 = xrecycler_view.getLayoutManager();
                if (textView.getText().toString().equals("列表")) {
                    if (layoutManager1 instanceof LinearLayoutManager) {//线性
                        textView.setText("图表");
                        xrecycler_view.setLayoutManager(new GridLayoutManager(XRecyclerViewActivity.this, 2));
                        xrecycler_view.removeItemDecoration(myItemDecoration);
                        xrecycler_view.addItemDecoration(mGridItemDecoration);
                        mMyAdapter.notifyDataSetChanged();
                    }
                } else if (textView.getText().toString().equals("图表")) {
                    if (layoutManager1 instanceof GridLayoutManager) {//线性
                        textView.setText("列表");
                        xrecycler_view.setLayoutManager(new LinearLayoutManager(XRecyclerViewActivity.this));
                        xrecycler_view.removeItemDecoration(mGridItemDecoration);
                        xrecycler_view.addItemDecoration(myItemDecoration);
                        mMyAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
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
//        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                String s = datas.get(position);
//                Toast.makeText(XRecyclerViewActivity.this, "position=" + position + "          " + s, Toast.LENGTH_SHORT).show();
//            }
//        });
        mMyAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onClickListener(int position) {
                String s = datas.get(position);
                Toast.makeText(XRecyclerViewActivity.this, "position=" + position + "          " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
