package com.iven.widget.pulltorefreshswipemenulistview.swipemenu.interfaces;


import com.iven.widget.pulltorefreshswipemenulistview.swipemenu.bean.SwipeMenu;

public interface OnMenuItemClickListener {
    void onMenuItemClick(int position, SwipeMenu menu, int index);
}