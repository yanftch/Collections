package com.yanftch.collections.module.swipe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iven.widget.daimajia.swipe.SimpleSwipeListener;
import com.iven.widget.daimajia.swipe.SwipeLayout;
import com.iven.widget.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.yanftch.collections.R;
import com.yanftch.collections.module.common.Bean;

import java.util.List;

/**
 * User : yanftch
 * Date : 2017/5/23
 * Time : 09:58
 * Desc :
 */

public class MySwipeMenuAdapter extends BaseSwipeAdapter {
    private static final String TAG = "dah_MySwipeMenuAdapter";
    private Context mContext;
    private List<Bean.T1348648517839Bean> datas;

    public MySwipeMenuAdapter(Context context, List<Bean.T1348648517839Bean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    //绑定Item的布局文件
    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_item_swipe, null);
        SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                Log.e(TAG, "onOpen: 打开了");
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.tv_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click top,,," + datas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.tv_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click collect..." + datas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        Bean.T1348648517839Bean t1348648517839Bean = datas.get(position);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        tv_title.setText(t1348648517839Bean.getTitle());
        TextView tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
        tv_desc.setText(t1348648517839Bean.getDigest());
        TextView tv_votecount = (TextView) convertView.findViewById(R.id.tv_votecount);
        tv_votecount.setText(String.format("阅读:%d", t1348648517839Bean.getVotecount()));
        TextView tv_replyCount = (TextView) convertView.findViewById(R.id.tv_replyCount);
        tv_replyCount.setText(String.format("回复:%d", t1348648517839Bean.getReplyCount()));
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
