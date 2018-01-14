package com.yanftch.collections.module.design.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanftch.collections.R;

/**
 * Author : yanftch
 * Date : 2018/1/8
 * Time : 17:07
 * Desc :
 */

public class TabFragment extends Fragment {
    private static final String TAG = "dah_TabFragment";

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    private RecyclerView mRvList;

    private View rootView;

    private TestRvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_tab, container, false);
        initWidget();
        return rootView;
    }

    public void initWidget() {
        adapter = new TestRvAdapter(getActivity());
        mRvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setAdapter(adapter);
    }

    public RecyclerView getRvList() {
        return mRvList;
    }

    /**
     * -------------------适配器----------------
     */
    class TestRvAdapter extends RecyclerView.Adapter<TestRvAdapter.TestViewHolder> {

        private Context context;


        public TestRvAdapter(Context context) {
            this.context = context;
        }

        @Override
        public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tv_test, parent, false);
            return new TestViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TestViewHolder holder, int position) {
            holder.tv_test.setText("测试数据" + position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class TestViewHolder extends RecyclerView.ViewHolder {

            TextView tv_test;

            TestViewHolder(View itemView) {
                super(itemView);
                tv_test = (TextView) itemView.findViewById(R.id.tv_test);
            }
        }

    }
}
