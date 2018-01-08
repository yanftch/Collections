package com.yanftch.collections.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yanftch.collections.R;
import com.yanftch.collections.common.adapter.CommonAdapter;
import com.yanftch.collections.common.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PopupWindowActivity extends AppCompatActivity {
    private static final String TAG = "dah_PopupWindowActivity";
    private List<String> datas;
    private ListView mListView;
    private CommonAdapter mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initView();
    }

    private void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item " + i);
        }
        mListView = (ListView) findViewById(R.id.listViewPop);
        mCommonAdapter = new CommonAdapter<String>(this, datas, R.layout.item_pop_view) {
            @Override
            public void convert(final ViewHolder helper, String item) {
                helper.setText(R.id.itemTextView2, item);
                final TextView textView = helper.getView(R.id.itemTextView2);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = helper.getPosition();
                        showPopupwindow(textView, position);
                    }
                });
            }
        };
        mListView.setAdapter(mCommonAdapter);
    }

    /**
     * 弹出删除按钮
     *
     * @param anchorView 需要弹出在哪个控件的位置
     * @param position   当前点击的条目index
     */

    private void showPopupwindow(View anchorView, final int position) {
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
//        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);//设置动画效果
        popupWindow.setAnimationStyle(R.style.CirclePopAnim);//设置动画效果
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        View view = View.inflate(this, R.layout.layout_pop_delete, null);
        TextView tv_del = (TextView) view.findViewById(R.id.pop_tv_del);
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        int[] locations = new int[2];
        anchorView.getLocationOnScreen(locations);//获取当前控件在屏幕上的位置
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, locations[0] - 90, locations[1] - 10);
    }
}
