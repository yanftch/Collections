package com.yanftch.collections.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.yanftch.collections.R;

import java.util.List;

/**
 * User : yanftch
 * Date : 2017/5/27
 * Time : 10:41
 * Desc :
 */

public class MyRecyclerSwipeAdapter extends RecyclerView.Adapter<MyRecyclerSwipeAdapter.ViewHolder> {
    private onItemClickListener mOnItemClickListener;
    private List<String> datas;
    private Context mContext;
    private static final String TAG = "dah_MyRecyclerSwipeAdapter";
    private View v;

    public MyRecyclerSwipeAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public interface onItemClickListener {
        void onClickListener(int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_swipe, viewGroup, false);

        return new ViewHolder(v);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datas.get(position).toString());
        final SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(R.id.swipeLayout);
        final int childCount = swipeLayout.getChildCount();
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Log.e(TAG, "onDoubleClick: 双击");
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout childAt = (LinearLayout) swipeLayout.getChildAt(0);
        TextView tv_top = (TextView) childAt.getChildAt(0);
        tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: toppp1111");
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

//    @Override
//    public int getSwipeLayoutResourceId(int position) {
//        return R.id.swipeLayout;
//    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
