package com.yanftch.collections.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
        this.context = context;
    }

    private void setPosition(int position) {
        mPosition = position;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(context, parent, layoutId, position);
            viewHolder.setPosition(position);
            return viewHolder;
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setPosition(position);
        return viewHolder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * 针对include标签的view
     *
     * @param includeId include标签ID
     * @param viewId    include标签内viewID
     * @return
     */
    public <T extends View> T getView4Include(int includeId, int viewId) {
        View includeView = mViews.get(includeId);
        if (includeView == null) {
            includeView = mConvertView.findViewById(includeId);
            mViews.put(includeId, includeView);
        }
        if (null != includeView)
            return (T) includeView.findViewById(viewId);
        return null;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setTag("");
        return this;
    }
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setTag("");
        return this;
    }
    /**
     * 为TextView设置字符串
     *
     * @param incluedId include标签ID
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int incluedId, int viewId, String text) {
        TextView view = getView4Include(incluedId, viewId);
        view.setText(text);
        view.setTag("");
        return this;
    }


    /**
     * 设置view是否可见
     *
     * @param viewId
     * @param visiblity
     * @return
     */
    public ViewHolder setVisiblity(int viewId, int visiblity) {
        View view = getView(viewId);
        view.setVisibility(visiblity);
        return this;
    }

    /**
     * 设置view是否可见
     *
     * @param includeId include 标签ID
     * @param viewId    include标签内viewID
     * @param visiblity
     * @return
     */
    public ViewHolder setVisiblity(int includeId, int viewId, int visiblity) {
        if (viewId != 0) {
            View view = getView4Include(includeId, viewId);
            if (null != view)
                view.setVisibility(visiblity);
        } else {
            View view = getView(includeId);
            view.setVisibility(visiblity);
        }
        return this;
    }


    /**
     * 为TextView设置tag内容
     *
     * @param viewId
     * @param tag
     * @return
     */
    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    public int getPosition() {
        return mPosition;

    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
