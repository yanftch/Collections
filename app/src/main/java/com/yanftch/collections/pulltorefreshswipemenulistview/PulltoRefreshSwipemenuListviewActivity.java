package com.yanftch.collections.pulltorefreshswipemenulistview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.iven.widget.pulltorefreshswipemenulistview.PullToRefreshSwipeMenuListView;
import com.iven.widget.pulltorefreshswipemenulistview.pulltorefresh.interfaces.IXListViewListener;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.bean.SwipeMenu;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.bean.SwipeMenuItem;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.interfaces.OnMenuItemClickListener;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.interfaces.OnSwipeListener;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.interfaces.SwipeMenuCreator;
import com.iven.widget.pulltorefreshswipemenulistview.util.RefreshTime;
import com.yanftch.collections.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author : yanftch
 * Date   : 2017/6/12
 * Time   : 15:00
 * Desc   : 自带上拉加载、下拉刷新、侧滑菜单的ListView
 */

public class PulltoRefreshSwipemenuListviewActivity extends AppCompatActivity implements IXListViewListener {

    private List<String> mAppList;
    private AppAdapter mAdapter;
    private PullToRefreshSwipeMenuListView mListView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullto_refresh_swipemenu_listview);
        mAppList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mAppList.add("item" + i);
        }

        mListView = (PullToRefreshSwipeMenuListView) findViewById(R.id.listView);
        mAdapter = new AppAdapter(getApplicationContext(), getPackageManager(), mAppList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mHandler = new Handler();

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = mAppList.get(position);
                switch (index) {
                    case 0:
                        Toast.makeText(PulltoRefreshSwipemenuListviewActivity.this, "open  position=" + position + "     " + item, Toast.LENGTH_SHORT).show();
                        // open
//                        open(item);
                        break;
                    case 1:
                        Toast.makeText(PulltoRefreshSwipemenuListviewActivity.this, "delete  position" + position + "      " + item, Toast.LENGTH_SHORT).show();
                        // delete
                        // delete(item);
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
        // listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PulltoRefreshSwipemenuListviewActivity.this, "position" + (position - 1) + "     " + mAppList.get(position - 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();

    }

    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                RefreshTime.setRefreshTime(getApplicationContext(), df.format(new Date()));
//                onLoad();
                mAppList.clear();
                for (int i = 0; i < 10; i++) {
                    mAppList.add("refresh  item" + i);
                }
                mListView.stopRefresh();
                mAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mAppList.add("more.  item" + i);
                }
                mListView.stopLoadMore();
                mAdapter.notifyDataSetChanged();
//                onLoad();
            }
        }, 2000);
    }

    private void delete(ApplicationInfo item) {
        // delete app
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", item.packageName, null));
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    private void open(ApplicationInfo item) {
        // open app
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
