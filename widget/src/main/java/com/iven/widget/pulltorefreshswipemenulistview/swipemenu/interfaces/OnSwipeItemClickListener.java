package com.iven.widget.pulltorefreshswipemenulistview.swipemenu.interfaces;


import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.bean.SwipeMenu;
import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.view.SwipeMenuView;

public interface OnSwipeItemClickListener {
    void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
}